package com.tolgaozgun.gdscturkweb.model;

import com.tolgaozgun.gdscturkweb.dto.UserDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailVerification {

    @NotNull
    private Long id;

    @NotNull
    private UserDTO user;

    @NotNull
    private String verificationCode;

    @NotNull
    private Boolean isUsed;

    @NotNull
    private Date validUntil;
}
