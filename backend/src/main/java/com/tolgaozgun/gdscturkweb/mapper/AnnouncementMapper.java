package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.dto.request.announcement.CreateAnnouncementRequest;
import com.tolgaozgun.gdscturkweb.entity.AnnouncementEntity;
import com.tolgaozgun.gdscturkweb.model.Announcement;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AnnouncementMapper {

    private final ModelMapper modelMapper;

    public AnnouncementEntity toEntity(Announcement announcement) {
        return modelMapper.map(announcement, AnnouncementEntity.class);
    }

    public AnnouncementEntity toEntity(CreateAnnouncementRequest announcementRequest) {
        return modelMapper.map(announcementRequest, AnnouncementEntity.class);
    }

    public Announcement toModel(AnnouncementEntity announcementEntity) {
        return modelMapper.map(announcementEntity, Announcement.class);
    }

    public List<Announcement> toModel(List<AnnouncementEntity> announcementEntities) {
        return announcementEntities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}