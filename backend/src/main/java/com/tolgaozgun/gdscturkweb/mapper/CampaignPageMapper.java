package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.entity.CampaignEntity;
import com.tolgaozgun.gdscturkweb.entity.CampaignPageEntity;
import com.tolgaozgun.gdscturkweb.model.Campaign;
import com.tolgaozgun.gdscturkweb.model.CampaignPage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CampaignPageMapper {

    private final ModelMapper modelMapper;

    public CampaignPageEntity toEntity(CampaignPage campaignPage) {
        CampaignPageEntity campaignPageEntity = modelMapper.map(campaignPage, CampaignPageEntity.class);
        return campaignPageEntity;
    }

    public List<CampaignPage> toModel(List<CampaignPageEntity> campaignPageEntities) {
        return campaignPageEntities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public CampaignPage toModel(CampaignPageEntity campaignPageEntity) {
        CampaignPage campaignPage = modelMapper.map(campaignPageEntity, CampaignPage.class);
        return campaignPage;
    }
}