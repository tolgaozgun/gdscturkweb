package com.tolgaozgun.gdscturkweb.exception;

public class QuestionCategoryNotFoundException extends RuntimeException {
    public QuestionCategoryNotFoundException() {
        super("Question category not found!");
    }

    public QuestionCategoryNotFoundException(Long categoryId) {
        super("Question cateogry with ID " + categoryId + " not found!");
    }

}
