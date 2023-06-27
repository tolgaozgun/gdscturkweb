package com.tolgaozgun.gdscturkweb.model;

import com.tolgaozgun.gdscturkweb.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {

    @Id
    private Long campaignId;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    private List<Attachment> attachments;

    @NotNull
    private List<Question> questions;

    @NotNull
    private List<UserType> permittedUserTypes;

}
