package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.entity.EventEntity;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.exception.ConnectionErrorException;
import com.tolgaozgun.gdscturkweb.exception.UniversityChapterLinkNotFoundException;
import com.tolgaozgun.gdscturkweb.mapper.EventMapper;
import com.tolgaozgun.gdscturkweb.model.Event;
import com.tolgaozgun.gdscturkweb.model.ScrapedEvent;
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
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventMapper eventMapper;
    private final UniversityService universityService;

    public List<Event> scrapeChapterEvents(Long universityId) {

        try {
        UniversityEntity universityEntity = universityService.getUniversityEntityById(universityId);

        if (universityEntity.getChapterLink() == null) {
            throw new UniversityChapterLinkNotFoundException();
        }

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
