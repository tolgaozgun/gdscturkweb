package com.tolgaozgun.gdscturkweb.service;


import com.tolgaozgun.gdscturkweb.dto.request.city.CreateCityRequest;
import com.tolgaozgun.gdscturkweb.dto.request.city.EditCityRequest;
import com.tolgaozgun.gdscturkweb.dto.request.topic.CreateTopicRequest;
import com.tolgaozgun.gdscturkweb.dto.request.topic.EditTopicRequest;
import com.tolgaozgun.gdscturkweb.dto.request.topic.GetTopicRequest;
import com.tolgaozgun.gdscturkweb.entity.CityEntity;
import com.tolgaozgun.gdscturkweb.entity.CountryEntity;
import com.tolgaozgun.gdscturkweb.entity.TopicEntity;
import com.tolgaozgun.gdscturkweb.exception.CountryNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.TopicNotFoundException;
import com.tolgaozgun.gdscturkweb.mapper.CityMapper;
import com.tolgaozgun.gdscturkweb.mapper.TopicMapper;
import com.tolgaozgun.gdscturkweb.model.City;
import com.tolgaozgun.gdscturkweb.model.Topic;
import com.tolgaozgun.gdscturkweb.repository.CityRepository;
import com.tolgaozgun.gdscturkweb.repository.CountryRepository;
import com.tolgaozgun.gdscturkweb.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public List<Topic> getAllTopics() {
        try {
            List<TopicEntity> topicEntities = topicRepository.findAll();
            return topicMapper.toModel(topicEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public Topic getTopic(GetTopicRequest getTopicRequest) {
        try {
            Long topicId = getTopicRequest.getTopicId();
            Optional<TopicEntity> optionalTopicEntity = topicRepository.findById(topicId);

            if (optionalTopicEntity.isEmpty()) {
                throw new TopicNotFoundException(topicId);
            }

            TopicEntity topicEntity = optionalTopicEntity.get();
            return topicMapper.toModel(topicEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public Topic createTopic(CreateTopicRequest createTopicRequest) {
        try {
            String name = createTopicRequest.getName();
            String description = createTopicRequest.getDescription();
            String icon = createTopicRequest.getIcon();
            String color = createTopicRequest.getColor();

            TopicEntity topicEntity = new TopicEntity();
            topicEntity.setName(name);
            topicEntity.setDescription(description);
            topicEntity.setIcon(icon);
            topicEntity.setColor(color);

            topicEntity = topicRepository.save(topicEntity);
            return topicMapper.toModel(topicEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public Topic editTopic(EditTopicRequest editTopicRequest) {
        try {
            Long topicId = editTopicRequest.getTopicId();
            Optional<TopicEntity> optionalTopicEntity = topicRepository.findById(topicId);

            if (optionalTopicEntity.isEmpty()) {
                throw new TopicNotFoundException();
            }

            TopicEntity topicEntity = optionalTopicEntity.get();

            if (editTopicRequest.getDescription() != null) {
                String description = editTopicRequest.getDescription();
                topicEntity.setDescription(description);
            }

            if (editTopicRequest.getName() != null) {
                topicEntity.setName(editTopicRequest.getName());
            }

            if (editTopicRequest.getIcon() != null) {
                topicEntity.setIcon(editTopicRequest.getIcon());
            }
            return topicMapper.toModel(topicRepository.save(topicEntity));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


}
