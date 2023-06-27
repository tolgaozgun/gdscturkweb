package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.dto.request.question.AnswerQuestionRequest;
import com.tolgaozgun.gdscturkweb.dto.request.question.AskQuestionRequest;
import com.tolgaozgun.gdscturkweb.entity.QuestionEntity;
import com.tolgaozgun.gdscturkweb.exception.QuestionNotFoundException;
import com.tolgaozgun.gdscturkweb.model.Question;
import com.tolgaozgun.gdscturkweb.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionMapper {

    private final ModelMapper modelMapper;
    private final QuestionRepository questionRepository;

    public QuestionEntity toEntity(Question question) {
        return modelMapper.map(question, QuestionEntity.class);
    }

    public QuestionEntity toEntity(AnswerQuestionRequest answerQuestionRequest) {
        try {
            Long questionId = answerQuestionRequest.getQuestionId();
            Optional<QuestionEntity> optionalQuestionEntity = questionRepository.findById(questionId);

            if (optionalQuestionEntity.isEmpty()) {
                throw new QuestionNotFoundException();
            }

            QuestionEntity questionEntity = optionalQuestionEntity.get();
            questionEntity.setAnswer(answerQuestionRequest.getAnswer());

            return questionEntity;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    public QuestionEntity toEntity(AskQuestionRequest askQuestionRequest) {
        try {
            QuestionEntity questionEntity = new QuestionEntity();
            questionEntity.setQuestion(askQuestionRequest.getQuestion());

            return questionEntity;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public Question toModel(QuestionEntity announcementEntity) {
        return modelMapper.map(announcementEntity, Question.class);
    }

    public List<Question> toModel(List<QuestionEntity> questionEntities) {
        return questionEntities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}