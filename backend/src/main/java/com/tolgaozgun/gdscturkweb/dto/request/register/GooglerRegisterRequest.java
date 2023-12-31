package com.tolgaozgun.gdscturkweb.dto.request.register;

import com.tolgaozgun.gdscturkweb.dto.user.register.GooglerRegister;
import com.tolgaozgun.gdscturkweb.dto.user.register.UserRegister;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GooglerRegisterRequest {

    @NotNull
    private UserRegister userRegister;

    @NotNull
    private GooglerRegister googlerRegister;


}
