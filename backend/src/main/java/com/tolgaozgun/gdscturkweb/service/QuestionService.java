package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.dto.request.question.*;
import com.tolgaozgun.gdscturkweb.entity.QuestionEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.exception.QuestionNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.UserNotFoundException;
import com.tolgaozgun.gdscturkweb.mapper.QuestionMapper;
import com.tolgaozgun.gdscturkweb.model.Question;
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
    private final UserRepository userRepository;
    private final QuestionMapper questionMapper;

    public List<Question> getAllQuestions() {
        try {
            List<QuestionEntity> questionEntities = questionRepository.findAll();
            return questionMapper.toModel(questionEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public Question getQuestion(GetQuestionRequest getQuestionRequest) {
        try {
            Long questionId = getQuestionRequest.getQuestionId();
            Optional<QuestionEntity> optionalQuestionEntity = questionRepository.findById(questionId);

            if (optionalQuestionEntity.isEmpty()) {
                throw new QuestionNotFoundException(questionId);
            }

            QuestionEntity questionEntity = optionalQuestionEntity.get();
            return questionMapper.toModel(questionEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<Question> getAnsweredQuestionsByUser(GetQuestionsAnsweredByRequest getQuestionsAnsweredByRequest) {
        try {
            Long userId = getQuestionsAnsweredByRequest.getUserId();

            Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("Error while getting user details");
            }

            UserEntity userEntity = optionalUserEntity.get();
            List<QuestionEntity> questionEntities = questionRepository.findAllByAnsweredBy(userEntity);
            return questionMapper.toModel(questionEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<Question> getQuestionsAskedByCurrentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userName);

            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("Error while getting user details");
            }

            UserEntity userEntity = optionalUserEntity.get();

            List<QuestionEntity> questionEntities = questionRepository.findAllByAskedBy(userEntity);
            return questionMapper.toModel(questionEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    public List<Question> getQuestionsAskedOrAnsweredByCurrentUser() {
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userName);

            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("Error while getting user details");
            }

            UserEntity userEntity = optionalUserEntity.get();

            List<QuestionEntity> questionEntities = questionRepository.findAllByAskedByOrAnsweredBy(userEntity, userEntity);
            return questionMapper.toModel(questionEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<Question> getAnsweredQuestionsByCurrentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userName);

            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("Error while getting user details");
            }

            UserEntity userEntity = optionalUserEntity.get();

            List<QuestionEntity> questionEntities = questionRepository.findAllByAnsweredBy(userEntity);
            return questionMapper.toModel(questionEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<Question> getQuestionsAskedByUser(GetQuestionsAskedByRequest getQuestionsAskedByRequest) {
        try {
            Long userId = getQuestionsAskedByRequest.getUserId();

            Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("Error while getting user details");
            }

            UserEntity userEntity = optionalUserEntity.get();
            List<QuestionEntity> questionEntities = questionRepository.findAllByAskedBy(userEntity);
            return questionMapper.toModel(questionEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    public List<Question> getQuestionsAskedOrAnsweredByUser(GetQuestionsAskedAnsweredByRequest getQuestionsAskedAnsweredByRequest) {
        try {
            Long userId = getQuestionsAskedAnsweredByRequest.getUserId();

            Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("Error while getting user details");
            }

            UserEntity userEntity = optionalUserEntity.get();
            List<QuestionEntity> questionEntities = questionRepository.findAllByAskedByOrAnsweredBy(userEntity, userEntity);
            return questionMapper.toModel(questionEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public Question askQuestion(AskQuestionRequest askQuestionRequest) {
        try {
            QuestionEntity questionEntity = questionMapper.toEntity(askQuestionRequest);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userName);

            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("Error while getting user details");
            }

            UserEntity userEntity = optionalUserEntity.get();
            questionEntity.setAskedBy(userEntity);
            questionEntity.setAskedDate(new Date());

            questionEntity = questionRepository.save(questionEntity);
            return questionMapper.toModel(questionEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    public Question answerQuestion(AnswerQuestionRequest answerQuestionRequest) {
        try {
            QuestionEntity questionEntity = questionMapper.toEntity(answerQuestionRequest);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userName);

            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("Error while getting user details");
            }

            UserEntity userEntity = optionalUserEntity.get();
            questionEntity.setAnsweredBy(userEntity);
            questionEntity.setAnsweredDate(new Date());

            questionEntity = questionRepository.save(questionEntity);
            return questionMapper.toModel(questionEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


}
