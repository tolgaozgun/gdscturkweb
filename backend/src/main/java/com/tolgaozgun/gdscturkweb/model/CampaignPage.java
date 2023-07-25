package com.tolgaozgun.gdscturkweb.model;

import com.tolgaozgun.gdscturkweb.enums.UserType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignPage {

    @Id
    private Long campaignPageId;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @Nullable
    private List<Attachment> attachments;

    @NotNull
    private List<UserType> permittedUserTypes;
}
