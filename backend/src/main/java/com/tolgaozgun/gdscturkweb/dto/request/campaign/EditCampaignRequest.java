package com.tolgaozgun.gdscturkweb.dto.request.campaign;

import com.tolgaozgun.gdscturkweb.enums.UserType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditCampaignRequest {

    @NotNull
    private Long campaignId;

    @Nullable
    private String title;

    @Nullable
    private String description;

    @Nullable
    private Date startDate;

    @Nullable
    private Date endDate;

    @Nullable
    private List<Long> attachmentIds;

    @Nullable
    private List<Long> questionIds;

    @Nullable
    private List<UserType> permittedUserTypes;

}
