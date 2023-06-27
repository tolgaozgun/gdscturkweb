package com.tolgaozgun.gdscturkweb.dto.user.register;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacilitatorRegister {

    // Not needed during register
//    @Nullable
//    private Long buddyTeamId;

    @NotNull
    private Long universityId;

}
