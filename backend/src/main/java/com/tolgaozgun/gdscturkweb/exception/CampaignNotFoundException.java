package com.tolgaozgun.gdscturkweb.exception;

public class CampaignNotFoundException extends RuntimeException {
    public CampaignNotFoundException() {
        super("Campaign is not found!");
    }

    public CampaignNotFoundException(Long campaignId) {
        super("Campaign with ID " + campaignId + " is not found!");
    }
}
