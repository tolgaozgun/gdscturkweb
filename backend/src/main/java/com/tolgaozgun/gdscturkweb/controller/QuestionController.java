package com.tolgaozgun.gdscturkweb.controller;

import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.dto.request.city.GetCityRequest;
import com.tolgaozgun.gdscturkweb.dto.request.question.*;
import com.tolgaozgun.gdscturkweb.dto.response.Response;
import com.tolgaozgun.gdscturkweb.exception.ExceptionLogger;
import com.tolgaozgun.gdscturkweb.model.City;
import com.tolgaozgun.gdscturkweb.model.Question;
import com.tolgaozgun.gdscturkweb.service.LeadService;
import com.tolgaozgun.gdscturkweb.service.QuestionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "")
    public ResponseEntity<Object> getAllQuestions() {
        try {
            List<Question> questions = questionService.getAllQuestions();
            return Response.create("Successfully listed questions", HttpStatus.OK, questions);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "by-id")
    public ResponseEntity<Object> getQuestionById(@Valid @RequestBody GetQuestionRequest getQuestionRequest) {
        try {
            Question question = questionService.getQuestion(getQuestionRequest);
            return Response.create("Found the question", HttpStatus.OK, question);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "asked-by-me")
    public ResponseEntity<Object> getAllQuestionsAskedByCurrentUser() {
        try {
            List<Question> questions = questionService.getQuestionsAskedByCurrentUser();
            return Response.create("Successfully listed questions", HttpStatus.OK, questions);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "answered-by-me")
    public ResponseEntity<Object> getAllQuestionsAnsweredByCurrentUser() {
        try {
            List<Question> questions = questionService.getAnsweredQuestionsByCurrentUser();
            return Response.create("Successfully listed questions", HttpStatus.OK, questions);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "asked-answered-by-me")
    public ResponseEntity<Object> getAllQuestionsAskedOrAnsweredByCurrentUser() {
        try {
            List<Question> questions = questionService.getQuestionsAskedOrAnsweredByCurrentUser();
            return Response.create("Successfully listed questions", HttpStatus.OK, questions);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "asked-by-user")
    public ResponseEntity<Object> getAllQuestionsAskedByUser(GetQuestionsAskedByRequest getQuestionsAskedByRequest) {
        try {
            List<Question> questions = questionService.getQuestionsAskedByUser(getQuestionsAskedByRequest);
            return Response.create("Successfully listed questions", HttpStatus.OK, questions);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "answered-by-user")
    public ResponseEntity<Object> getAllQuestionsAnsweredByUser(GetQuestionsAnsweredByRequest getQuestionsAnsweredByRequest) {
        try {
            List<Question> questions = questionService.getAnsweredQuestionsByUser(getQuestionsAnsweredByRequest);
            return Response.create("Successfully listed questions", HttpStatus.OK, questions);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "asked-answered-by-user")
    public ResponseEntity<Object> getAllQuestionsAskedOrAnsweredByUser(GetQuestionsAskedAnsweredByRequest getQuestionsAskedAnsweredByRequest) {
        try {
            List<Question> questions = questionService.getQuestionsAskedOrAnsweredByUser(getQuestionsAskedAnsweredByRequest);
            return Response.create("Successfully listed questions", HttpStatus.OK, questions);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "ask")
    public ResponseEntity<Object> askQuestion(@Valid @RequestBody AskQuestionRequest askQuestionRequest) {
        try {
            Question question = questionService.askQuestion(askQuestionRequest);
            return Response.create("Successfully asked question", HttpStatus.OK, question);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "answer")
    public ResponseEntity<Object> answerQuestion(@Valid @RequestBody AnswerQuestionRequest answerQuestionRequest) {
        try {
            Question question = questionService.answerQuestion(answerQuestionRequest);
            return Response.create("Successfully answered question", HttpStatus.OK, question);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
