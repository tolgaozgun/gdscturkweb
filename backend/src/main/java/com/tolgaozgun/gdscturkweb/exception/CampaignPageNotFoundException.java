package com.tolgaozgun.gdscturkweb.exception;

public class CampaignPageNotFoundException extends RuntimeException {
    public CampaignPageNotFoundException() {
        super("Campaign page is not found!");
    }

    public CampaignPageNotFoundException(Long campaignId) {
        super("Campaign page with ID " + campaignId + " is not found!");
    }
}
