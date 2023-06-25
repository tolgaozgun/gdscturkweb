package com.tolgaozgun.gdscturkweb.dto.request;

import com.tolgaozgun.gdscturkweb.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementRequest {
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private List<UserType> permittedUserTypes;
}
