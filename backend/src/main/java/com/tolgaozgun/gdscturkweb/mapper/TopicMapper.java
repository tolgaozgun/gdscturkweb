package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.entity.TopicEntity;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.model.Topic;
import com.tolgaozgun.gdscturkweb.model.University;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TopicMapper {

    private final ModelMapper modelMapper;

    public TopicEntity toEntity(Topic topic) {
        TopicEntity topicEntity = modelMapper.map(topic, TopicEntity.class);
        return topicEntity;
    }

    public List<TopicEntity> toEntity(List<Topic> topicList) {
        return topicList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<Topic> toModel(List<TopicEntity> topicEntityList) {
        return topicEntityList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Topic toModel(TopicEntity universityEntity) {
        Topic topic = modelMapper.map(universityEntity, Topic.class);
        return topic;
    }
}