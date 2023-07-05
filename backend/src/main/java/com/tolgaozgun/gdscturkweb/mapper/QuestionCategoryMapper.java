package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.dto.QuestionDTO;
import com.tolgaozgun.gdscturkweb.dto.request.question.AnswerQuestionRequest;
import com.tolgaozgun.gdscturkweb.dto.request.question.AskQuestionRequest;
import com.tolgaozgun.gdscturkweb.entity.QuestionCategoryEntity;
import com.tolgaozgun.gdscturkweb.entity.QuestionEntity;
import com.tolgaozgun.gdscturkweb.exception.QuestionNotFoundException;
import com.tolgaozgun.gdscturkweb.model.Question;
import com.tolgaozgun.gdscturkweb.model.QuestionCategory;
import com.tolgaozgun.gdscturkweb.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionCategoryMapper {

    private final ModelMapper modelMapper;

    public QuestionCategoryEntity toEntity(QuestionCategory questionCategory) {
        return modelMapper.map(questionCategory, QuestionCategoryEntity.class);
    }

    public QuestionCategory toModel(QuestionCategoryEntity questionCategoryEntity) {
        return modelMapper.map(questionCategoryEntity, QuestionCategory.class);
    }


    public List<QuestionCategory> toModel(List<QuestionCategoryEntity> questionCategoryEntities) {
        return questionCategoryEntities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}