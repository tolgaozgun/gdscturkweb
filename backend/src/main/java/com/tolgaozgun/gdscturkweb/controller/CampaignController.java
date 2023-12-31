package com.tolgaozgun.gdscturkweb.controller;

import com.tolgaozgun.gdscturkweb.dto.request.campaign.CreateCampaignRequest;
import com.tolgaozgun.gdscturkweb.dto.request.campaign.EditCampaignRequest;
import com.tolgaozgun.gdscturkweb.dto.response.Response;
import com.tolgaozgun.gdscturkweb.exception.ExceptionLogger;
import com.tolgaozgun.gdscturkweb.model.Campaign;
import com.tolgaozgun.gdscturkweb.model.CampaignPage;
import com.tolgaozgun.gdscturkweb.service.CampaignService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping( path = "")
    public ResponseEntity<Object> getAllCampaigns() {
        try {
            List<Campaign> campaignList = campaignService.getAllCampaigns();
            return Response.create("Gathered all campaigns", HttpStatus.OK, campaignList);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }


    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping( path = "current")
    public ResponseEntity<Object> getCurrentCampaigns() {
        try {
            List<Campaign> campaignList = campaignService.getCurrentCampaigns();
            return Response.create("Gathered current campaigns", HttpStatus.OK, campaignList);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }


    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "{campaignId}")
    public ResponseEntity<Object> getCampaignById(@PathVariable Long campaignId) {
        try {
            Campaign campaign = campaignService.getCampaign(campaignId);
            return Response.create("Found the campaign", HttpStatus.OK, campaign);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "/pages/by-campaign/{campaignId}")
    public ResponseEntity<Object> getCampaignPagesByCampaign(@PathVariable Long campaignId) {
        try {
            List<CampaignPage> campaignPages = campaignService.getCampaignPagesByCampaign(campaignId);
            return Response.create("Found the campaign pages", HttpStatus.OK, campaignPages);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "/pages/{campaignPageId}")
    public ResponseEntity<Object> getCampaignPageById(@PathVariable Long campaignPageId) {
        try {
            CampaignPage campaignPage = campaignService.getCampaignPageById(campaignPageId);
            return Response.create("Found the campaign page", HttpStatus.OK, campaignPage);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "create")
    public ResponseEntity<Object> createCampaign(@Valid @RequestBody CreateCampaignRequest createCampaignRequest) {
        try {
            Campaign campaign = campaignService.createCampaign(createCampaignRequest);
            return Response.create("Campaign created successfully", HttpStatus.OK, campaign);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }


    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "edit/{campaignId}")
    public ResponseEntity<Object> editCampaign(@PathVariable Long campaignId, @Valid @RequestBody EditCampaignRequest editCampaignRequest) {
        try {
            Campaign campaign = campaignService.editCampaign(campaignId, editCampaignRequest);
            return Response.create("Campaign edited successfully", HttpStatus.OK, campaign);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }


}
