package com.tolgaozgun.gdscturkweb.exception;

public class AttachmentNotFoundException extends RuntimeException {

    public AttachmentNotFoundException() {
        super("Attachment not found!");
    }

    public AttachmentNotFoundException(Long attachmentId) {
        super("Attachment with ID " + attachmentId + " not found!");
    }
}
