package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.dto.FacilitatorDTO;
import com.tolgaozgun.gdscturkweb.dto.UserDTO;
import com.tolgaozgun.gdscturkweb.entity.CampaignEntity;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.entity.user.FacilitatorEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.model.Campaign;
import com.tolgaozgun.gdscturkweb.model.University;
import com.tolgaozgun.gdscturkweb.model.user.Facilitator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CampaignMapper {

    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final BuddyTeamMapper buddyTeamMapper;
    private final UniversityMapper universityMapper;

    public CampaignEntity toEntity(Campaign campaign) {
        CampaignEntity campaignEntity = modelMapper.map(campaign, CampaignEntity.class);
        return campaignEntity;
    }

    public List<Campaign> toModel(List<CampaignEntity> campaignEntities) {
        return campaignEntities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Campaign toModel(CampaignEntity campaignEntity) {
        Campaign campaign = modelMapper.map(campaignEntity, Campaign.class);
        return campaign;
    }
}