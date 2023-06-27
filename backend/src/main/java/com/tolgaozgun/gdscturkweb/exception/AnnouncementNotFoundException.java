package com.tolgaozgun.gdscturkweb.exception;

public class AnnouncementNotFoundException extends RuntimeException {
    public AnnouncementNotFoundException() {
        super("Announcement not found!");
    }

    public AnnouncementNotFoundException(Long campaignId) {
        super("Announcement with ID " + campaignId + "not found!");
    }
}
