package com.tolgaozgun.gdscturkweb.dto.user.profile;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCoreTeamProfileByStaff {

    @NotNull
    private Long coreTeamMemberId;

    @Nullable
    private Long universityId;

    @NotNull
    private Long userId;

}
