package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.EventEntity;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.entity.user.CoreTeamMemberEntity;
import com.tolgaozgun.gdscturkweb.entity.user.FacilitatorEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.exception.BuddyTeamNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.ConnectionErrorException;
import com.tolgaozgun.gdscturkweb.exception.UniversityChapterLinkNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.UniversityNotFoundException;
import com.tolgaozgun.gdscturkweb.mapper.EventMapper;
import com.tolgaozgun.gdscturkweb.model.Event;
import com.tolgaozgun.gdscturkweb.model.ScrapedEvent;
import com.tolgaozgun.gdscturkweb.repository.EventRepository;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    private final UniversityService universityService;
    private final AuthService authService;
    private final LeadService leadService;
    private final FacilitatorService facilitatorService;
    private final CoreTeamMemberService coreTeamMemberService;
    private final BuddyTeamService buddyTeamService;

    public List<Event> getAllEvents() {
        try {
            return eventMapper.toModel(eventRepository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Event> getEventsByCurrentUserUniversity() throws Exception {
        try {
            UserEntity userEntity = authService.getCurrentUserEntity();
            UniversityEntity universityEntity;

            switch (userEntity.getUserType()) {
                case LEAD -> {
                    LeadEntity leadEntity = leadService.getLeadEntityFromUserEntity(userEntity);
                    universityEntity = leadEntity.getUniversity();
                }
                case CORE_TEAM_MEMBER -> {
                    CoreTeamMemberEntity coreTeamMemberEntity = coreTeamMemberService.getCoreTeamMemberEntity(userEntity);
                    universityEntity = coreTeamMemberEntity.getUniversity();
                }
                default -> throw new UniversityNotFoundException();
            }

            return eventMapper.toModel(eventRepository.findAllByOrganizersContaining(universityEntity));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Event> getEventsByUniversity(Long universityId) {
        try {
            UniversityEntity universityEntity = universityService.getUniversityEntityById(universityId);

            return getEventsByUniversity(universityEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Event> getEventsByUniversity(UniversityEntity universityEntity) {
        try {
            return eventMapper.toModel(eventRepository.findAllByOrganizersContaining(universityEntity));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

//    protected List<Event> getAllEventsByOrganizers(List<UniversityEntity> organizers) {
//        try {
//            return eventMapper.toModel(eventRepository.findAllByOrganizersContaining(organizers));
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }

    public List<Event> getEventsByCurrentBuddyTeam() {
        try {

            UserEntity userEntity = authService.getCurrentUserEntity();
            BuddyTeamEntity buddyTeamEntity;

            switch (userEntity.getUserType()) {
                case LEAD -> {
                    LeadEntity leadEntity = leadService.getLeadEntityFromUserEntity(userEntity);
                    buddyTeamEntity = leadEntity.getBuddyTeam();
                }
                case FACILITATOR -> {
                    buddyTeamEntity = buddyTeamService.getBuddyTeamEntityByFacilitator();
                }
                default -> throw new BuddyTeamNotFoundException();
            }

            return getEventsByBuddyTeam(buddyTeamEntity);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    protected List<Event> getEventsByBuddyTeam(BuddyTeamEntity buddyTeamEntity) {
        try {
            List<LeadEntity> leadEntities = buddyTeamEntity.getLeads();

            List<UniversityEntity> universityEntities = new ArrayList<>();
            for (LeadEntity leadEntity : leadEntities) {
                universityEntities.add(leadEntity.getUniversity());
            }

            List<EventEntity> eventEntities = eventRepository.findAllByOrganizersIn(universityEntities);

            return eventMapper.toModel(eventEntities);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Event> scrapeCurrentChapterEvents() throws Exception {
        try {
            UserEntity userEntity = authService.getCurrentUserEntity();
            UniversityEntity universityEntity;

            switch (userEntity.getUserType()) {
                case LEAD -> {
                    LeadEntity leadEntity = leadService.getLeadEntityFromUserEntity(userEntity);
                    universityEntity = leadEntity.getUniversity();
                }
                case CORE_TEAM_MEMBER -> {
                    CoreTeamMemberEntity coreTeamMemberEntity = coreTeamMemberService.getCoreTeamMemberEntity(userEntity);
                    universityEntity = coreTeamMemberEntity.getUniversity();
                }
                default -> throw new UniversityNotFoundException();
            }

            return scrapeChapterEvents(universityEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Event> scrapeChapterEventsByUniversityId(Long universityId) {
        try {

            UniversityEntity universityEntity = universityService.getUniversityEntityById(universityId);

            if (universityEntity.getChapterLink() == null) {
                throw new UniversityChapterLinkNotFoundException();
            }
            return scrapeChapterEvents(universityEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void saveEventsIfNotExists(List<EventEntity> eventEntities) {
        try {
            for (EventEntity eventEntity : eventEntities) {
                Optional<EventEntity> optionalEventEntity = eventRepository.findByLink(eventEntity.getLink());
                if (optionalEventEntity.isEmpty()) {
                    eventRepository.save(eventEntity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Event> scrapeChapterEvents(UniversityEntity universityEntity) {

        try {

        String url = universityEntity.getChapterLink();

//        String url = "https://gdsc.community.dev/" + chapterName + "/";
        List<EventEntity> eventInfos = new ArrayList<>();

            Document document = Jsoup.connect(url).get();
            Element upcomingEventsDiv = document.getElementById("upcoming-events");
            Element pastEventsDiv = document.getElementById("past-events");

            if (upcomingEventsDiv != null) {
                Element ulElement = upcomingEventsDiv.selectFirst("ul.event-list");

                if (ulElement != null) {
                    Elements eventElements = ulElement.select("li.row.event");

                    for (Element eventElement : eventElements) {
                        Element anchorElement = eventElement.selectFirst("a");

                        if (anchorElement != null) {
                            String eventUrl = anchorElement.attr("href");
                            eventUrl = "https://gdsc.community.dev" + eventUrl;
                            System.out.println("eventurl is: " + eventUrl);
                            ScrapedEvent eventInfo = scrapeEventDetails(eventUrl);
                            EventEntity event = eventMapper.parseEventEntity(eventInfo);
                            event.setOrganizers(List.of(universityEntity));
                            eventInfos.add(event);
                        }
                    }
                }
            }

            if (pastEventsDiv != null) {
                Element ulElement = pastEventsDiv.selectFirst("ul.past-event-list");

                if (ulElement != null) {

                    // Select all children of ul element which are a elements
                    Elements eventElements = ulElement.select("a");


                    for (Element anchorElement : eventElements) {

                        if (anchorElement != null) {
                            String eventUrl = anchorElement.attr("href");
                            eventUrl = "https://gdsc.community.dev" + eventUrl;
                            System.out.println("eventurl is: " + eventUrl);
                            ScrapedEvent eventInfo = scrapeEventDetails(eventUrl);
                            EventEntity event = eventMapper.parseEventEntity(eventInfo);
                            event.setOrganizers(List.of(universityEntity));
                            eventInfos.add(event);

                        }
                    }
                }
            }
            saveEventsIfNotExists(eventInfos);

            return eventMapper.toModel(eventInfos);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ConnectionErrorException();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    private ScrapedEvent scrapeEventDetails(String eventUrl) throws IOException {
        Document eventDocument = Jsoup.connect(eventUrl).get();
        ScrapedEvent eventInfo = new ScrapedEvent();

        Element bannerDiv = eventDocument.selectFirst("div.general-event-banner");
        assert bannerDiv != null;
        Element eventNameSpan = bannerDiv.selectFirst("span.font_banner2");
        Element addressDiv = eventDocument.selectFirst("div.event-header-address");
        Element eventDescription = eventDocument.selectFirst("div.event-description");

        eventInfo.setName(eventNameSpan != null ? eventNameSpan.text() : "Unknown Event");
        eventInfo.setType(addressDiv != null ? addressDiv.attr("data-event-audience-type") : "Unknown Type");
        eventInfo.setAddress(addressDiv != null ? addressDiv.attr("data-event-get-address") : "Unknown Address");
        eventInfo.setDisplayDate(addressDiv != null ? addressDiv.attr("data-event-display-date") : "Unknown Date");
        eventInfo.setDescription(eventDescription != null ? eventDescription.text() : "Unknown Description");
        eventInfo.setLink(eventUrl);
        return eventInfo;
    }

}
