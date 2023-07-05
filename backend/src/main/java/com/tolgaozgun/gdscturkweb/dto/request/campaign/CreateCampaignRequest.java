package com.tolgaozgun.gdscturkweb.dto.request.campaign;

import com.tolgaozgun.gdscturkweb.entity.AttachmentEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import com.tolgaozgun.gdscturkweb.model.CampaignPage;
import com.tolgaozgun.gdscturkweb.model.user.User;
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
public class CreateCampaignRequest {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @Nullable
    private List<Long> attachmentIds;

    @Nullable
    private List<Long> questionIds;

    @Nullable
    private List<UserType> permittedUserTypes;

    @Nullable
    private List<Long> campaignPageIds;

}
