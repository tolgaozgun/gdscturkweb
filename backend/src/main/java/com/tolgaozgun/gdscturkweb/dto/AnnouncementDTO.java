package com.tolgaozgun.gdscturkweb.dto;

import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDTO {

    @Id
    private Long announcementId;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    private UserDTO sentBy;

    @NotNull
    private List<UserType> permittedUserTypes;


}
