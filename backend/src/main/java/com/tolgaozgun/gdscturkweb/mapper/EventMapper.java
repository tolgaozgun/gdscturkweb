package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.entity.EventEntity;
import com.tolgaozgun.gdscturkweb.model.Event;
import com.tolgaozgun.gdscturkweb.model.EventDate;
import com.tolgaozgun.gdscturkweb.model.ScrapedEvent;
import com.tolgaozgun.gdscturkweb.util.EventDateParser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EventMapper {

    private final ModelMapper modelMapper;

    public EventEntity toEntity(Event event) {
        EventEntity eventEntity = modelMapper.map(event, EventEntity.class);
        return eventEntity;
    }

    public List<EventEntity> toEntity(List<Event> eventList) {
        return eventList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }


    public EventEntity parseEventEntity(ScrapedEvent scrapedEvent) {
        EventEntity event = new EventEntity();
        event.setTitle(scrapedEvent.getName());
        event.setTopics(List.of());
        event.setAddress(scrapedEvent.getAddress());

        Optional<EventDate> eventDate = EventDateParser.parse(scrapedEvent.getDisplayDate());
        if(eventDate.isPresent()) {
            event.setStartDate(eventDate.get().getStartDate());
            event.setEndDate(eventDate.get().getEndDate());
        } else {
            event.setStartDate(new Date());
        }
        event.setDescription(scrapedEvent.getDescription());
        event.setLink(scrapedEvent.getLink());

        return event;
    }

    public Event parseEvent(ScrapedEvent scrapedEvent) {
        Event event = new Event();
        event.setTitle(scrapedEvent.getName());
        event.setTopics(List.of());
        event.setAddress(scrapedEvent.getAddress());

        Optional<EventDate> eventDate = EventDateParser.parse(scrapedEvent.getDisplayDate());
        if(eventDate.isPresent()) {
            event.setStartDate(eventDate.get().getStartDate());
            event.setEndDate(eventDate.get().getEndDate());
        } else {
            event.setStartDate(new Date());
        }
        event.setDescription(scrapedEvent.getDescription());
        event.setLink(scrapedEvent.getLink());

        return event;
    }

    public List<Event> toModel(List<EventEntity> eventEntityList) {
        return eventEntityList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Event toModel(EventEntity universityEntity) {
        Event event = modelMapper.map(universityEntity, Event.class);
        return event;
    }
}