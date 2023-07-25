package com.tolgaozgun.gdscturkweb.dto.request.announcement;

import com.tolgaozgun.gdscturkweb.enums.UserType;
import jakarta.annotation.Nullable;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditAnnouncementRequest {

    @Nullable
    private String title;

    @Nullable
    private String description;

    @Nullable
    private Date startDate;

    @Nullable
    private Date endDate;

    @Nullable
    private List<UserType> permittedUserTypes;
}
