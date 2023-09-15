package com.tolgaozgun.gdscturkweb.service;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DashboardScrapeService {
    public class EventInfo {
        String eventName;
        String eventType;
        String eventAddress;
        String displayDate;
    }

    public List<EventInfo> scrapeChapterEvents(String chapterName) {
        String url = "https://gdsc.community.dev/" + chapterName + "/";
        List<EventInfo> eventInfos = new ArrayList<>();

        try {
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
                            EventInfo eventInfo = scrapeEventDetails(eventUrl);
                            eventInfos.add(eventInfo);
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
                            EventInfo eventInfo = scrapeEventDetails(eventUrl);
                            eventInfos.add(eventInfo);
                        }
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return eventInfos;
    }

    private EventInfo scrapeEventDetails(String eventUrl) throws IOException {
        Document eventDocument = Jsoup.connect(eventUrl).get();
        EventInfo eventInfo = new EventInfo();

        Element bannerDiv = eventDocument.selectFirst("div.general-event-banner");
        assert bannerDiv != null;
        Element eventNameSpan = bannerDiv.selectFirst("span.font_banner2");
        Element addressDiv = eventDocument.selectFirst("div.event-header-address");

        eventInfo.eventName = eventNameSpan != null ? eventNameSpan.text() : "Unknown Event";
        eventInfo.eventType = addressDiv != null ? addressDiv.attr("data-event-audience-type") : "Unknown Type";
        eventInfo.eventAddress = addressDiv != null ? addressDiv.attr("data-event-get-address") : "Unknown Address";
        eventInfo.displayDate = addressDiv != null ? addressDiv.attr("data-event-display-date") : "Unknown Date";

        return eventInfo;
    }

    public void scrapeEvents(String chapterName) {
        List<EventInfo> eventInfos = scrapeChapterEvents(chapterName);

        if (eventInfos.isEmpty()) {
            System.out.println("No upcoming events for this chapter.");
        } else {
            System.out.println("Upcoming events:");
            for (EventInfo eventInfo : eventInfos) {
                System.out.println("Event Name: " + eventInfo.eventName);
                System.out.println("Event Type: " + eventInfo.eventType);
                System.out.println("Event Address: " + eventInfo.eventAddress);
                System.out.println("Display Date: " + eventInfo.displayDate);
                try {

                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy, h:mm a (z)");
                    Date date = parseDate(dateFormat, eventInfo.displayDate);
                    System.out.println("Java date: " + date);
                } catch (ParseException e) {
                    System.out.println("Could not parse date: " + eventInfo.displayDate);
                }
                System.out.println();
            }
        }
    }

    private Date parseDate(SimpleDateFormat dateFormat, String dateString) throws ParseException {
        // Attempt to parse the date range format
        if (dateString.contains(" - ")) {
            String[] dateParts = dateString.split(" - ");
            dateFormat = new SimpleDateFormat("MMM d - d, yyyy, h:mm a (z)");
            if (dateParts.length == 2) {
                String startDateStr = dateParts[0];
                String endDateStr = dateParts[1];
                Date startDate = dateFormat.parse(startDateStr);
                Date endDate = dateFormat.parse(endDateStr);
                // Assuming the time is the same for both start and end dates in this format
                return startDate;
            }
        }

        // If not a date range format, parse it as a single date
        return dateFormat.parse(dateString);
    }

}
