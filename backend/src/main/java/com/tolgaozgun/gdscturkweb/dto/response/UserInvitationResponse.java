package com.tolgaozgun.gdscturkweb.dto.response;

import com.tolgaozgun.gdscturkweb.enums.UserType;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInvitationResponse {

    private String email;

    private UserType role;


    private Date validUntil;

    @Nullable
    private Boolean isValid;

}
