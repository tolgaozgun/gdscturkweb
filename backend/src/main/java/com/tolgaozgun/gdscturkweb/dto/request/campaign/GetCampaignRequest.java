package com.tolgaozgun.gdscturkweb.dto.request.campaign;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCampaignRequest {

    @NotNull
    private Long campaignId;

}
