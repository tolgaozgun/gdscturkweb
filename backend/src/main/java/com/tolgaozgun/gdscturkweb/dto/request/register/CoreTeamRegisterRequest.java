package com.tolgaozgun.gdscturkweb.dto.request.register;

import com.tolgaozgun.gdscturkweb.dto.user.CoreTeamRegister;
import com.tolgaozgun.gdscturkweb.dto.user.FacilitatorRegister;
import com.tolgaozgun.gdscturkweb.dto.user.UserRegister;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoreTeamRegisterRequest {

    @NotNull
    private UserRegister userRegister;

    @NotNull
    private CoreTeamRegister coreTeamRegister;
}
