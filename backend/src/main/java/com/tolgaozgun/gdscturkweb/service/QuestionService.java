package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.dto.QuestionDTO;
import com.tolgaozgun.gdscturkweb.dto.request.question.*;
import com.tolgaozgun.gdscturkweb.entity.QuestionCategoryEntity;
import com.tolgaozgun.gdscturkweb.entity.QuestionEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.exception.QuestionCategoryNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.QuestionNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.UserNotFoundException;
import com.tolgaozgun.gdscturkweb.mapper.QuestionCategoryMapper;
import com.tolgaozgun.gdscturkweb.mapper.QuestionMapper;
import com.tolgaozgun.gdscturkweb.model.QuestionCategory;
import com.tolgaozgun.gdscturkweb.repository.QuestionCategoryRepository;
import com.tolgaozgun.gdscturkweb.repository.QuestionRepository;
import com.tolgaozgun.gdscturkweb.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionCategoryRepository questionCategoryRepository;
    private final UserRepository userRepository;

    private final QuestionMapper questionMapper;
    private final QuestionCategoryMapper questionCategoryMapper;

    private final AuthService authService;

    public List<QuestionDTO> getAllQuestions() {
        try {
            List<QuestionEntity> questionEntities = questionRepository.findAll();
            return questionMapper.toDTO(questionEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<QuestionCategory> getAllQuestionCategories() {
        try {
            List<QuestionCategoryEntity> questionCategoryEntities = questionCategoryRepository.findAll();
            return questionCategoryMapper.toModel(questionCategoryEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<QuestionDTO> getAllQuestionsByCategory(Long categoryId) {
        try {
            Optional<QuestionCategoryEntity> optionalQuestionCategoryEntity = questionCategoryRepository.findById(categoryId);

            if (optionalQuestionCategoryEntity.isEmpty()) {
                throw new QuestionCategoryNotFoundException(categoryId);
            }

            QuestionCategoryEntity questionCategoryEntity = optionalQuestionCategoryEntity.get();
            List<QuestionEntity> questionEntities = questionRepository.findAllByCategory(questionCategoryEntity);
            return questionMapper.toDTO(questionEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public QuestionCategory getQuestionCategoryById(Long categoryId) {
        try {
            Optional<QuestionCategoryEntity> optionalQuestionCategoryEntity = questionCategoryRepository.findById(categoryId);

            if (optionalQuestionCategoryEntity.isEmpty()) {
                throw new QuestionCategoryNotFoundException(categoryId);
            }

            QuestionCategoryEntity questionCategoryEntity = optionalQuestionCategoryEntity.get();

            return questionCategoryMapper.toModel(questionCategoryEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public QuestionDTO getQuestion(Long questionId) {
        try {
            Optional<QuestionEntity> optionalQuestionEntity = questionRepository.findById(questionId);

            if (optionalQuestionEntity.isEmpty()) {
                throw new QuestionNotFoundException(questionId);
            }

            QuestionEntity questionEntity = optionalQuestionEntity.get();
            return questionMapper.toDTO(questionEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<QuestionDTO> getAnsweredQuestionsByUser(Long userId) {
        try {
            Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("Error while getting user details");
            }

            UserEntity userEntity = optionalUserEntity.get();
            List<QuestionEntity> questionEntities = questionRepository.findAllByAnsweredBy(userEntity);
            return questionMapper.toDTO(questionEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<QuestionDTO> getQuestionsAskedByCurrentUser() {
        try {
            UserEntity userEntity = authService.getCurrentUserEntity();

            List<QuestionEntity> questionEntities = questionRepository.findAllByAskedBy(userEntity);
            return questionMapper.toDTO(questionEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    public List<QuestionDTO> getQuestionsAskedOrAnsweredByCurrentUser() {
        try {
            UserEntity userEntity = authService.getCurrentUserEntity();

            List<QuestionEntity> questionEntities = questionRepository.findAllByAskedByOrAnsweredBy(userEntity, userEntity);
            return questionMapper.toDTO(questionEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<QuestionDTO> getAnsweredQuestionsByCurrentUser() {
        try {
            UserEntity userEntity = authService.getCurrentUserEntity();

            List<QuestionEntity> questionEntities = questionRepository.findAllByAnsweredBy(userEntity);
            return questionMapper.toDTO(questionEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<QuestionDTO> getQuestionsAskedByUser(Long userId) {
        try {
            Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("Error while getting user details");
            }

            UserEntity userEntity = optionalUserEntity.get();
            List<QuestionEntity> questionEntities = questionRepository.findAllByAskedBy(userEntity);
            return questionMapper.toDTO(questionEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    public List<QuestionDTO> getQuestionsAskedOrAnsweredByUser(Long userId) {
        try {
            Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("Error while getting user details");
            }

            UserEntity userEntity = optionalUserEntity.get();
            List<QuestionEntity> questionEntities = questionRepository.findAllByAskedByOrAnsweredBy(userEntity, userEntity);
            return questionMapper.toDTO(questionEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    public QuestionDTO askQuestion(AskQuestionRequest askQuestionRequest) {
        try {
            QuestionEntity questionEntity = questionMapper.toEntity(askQuestionRequest);

            UserEntity userEntity = authService.getCurrentUserEntity();
            questionEntity.setAskedBy(userEntity);
            questionEntity.setAskedDate(new Date());

            questionEntity = questionRepository.save(questionEntity);
            return questionMapper.toDTO(questionEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    public QuestionDTO answerQuestion(AnswerQuestionRequest answerQuestionRequest) {
        try {
            QuestionEntity questionEntity = questionMapper.toEntity(answerQuestionRequest);

            UserEntity userEntity = authService.getCurrentUserEntity();
            questionEntity.setAnsweredBy(userEntity);
            questionEntity.setAnsweredDate(new Date());

            questionEntity = questionRepository.save(questionEntity);
            return questionMapper.toDTO(questionEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


}
