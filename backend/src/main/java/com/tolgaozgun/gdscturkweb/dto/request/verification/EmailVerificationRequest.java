package com.tolgaozgun.gdscturkweb.dto.request.verification;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailVerificationRequest {

    @NotNull
    private String email;

    @NotNull
    private String verificationCode;
}
