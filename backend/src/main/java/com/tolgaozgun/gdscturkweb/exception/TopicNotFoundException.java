package com.tolgaozgun.gdscturkweb.exception;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException() {
        super("Topic not found!");
    }
    public TopicNotFoundException(Long topicId) {
        super("Topic with ID " + topicId + " not found!");
    }
}
