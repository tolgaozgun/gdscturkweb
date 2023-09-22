package com.tolgaozgun.gdscturkweb.dto.request.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tolgaozgun.gdscturkweb.dto.user.profile.UpdateCoreTeamProfileByCoreTeam;
import com.tolgaozgun.gdscturkweb.dto.user.profile.UpdateCoreTeamProfileByStaff;
import com.tolgaozgun.gdscturkweb.dto.user.profile.UpdateUserProfileByStaff;
import com.tolgaozgun.gdscturkweb.dto.user.profile.UpdateUserProfileByUser;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCoreTeamMemberProfileByStaffRequest {


    @NotNull
    @JsonProperty("core-team")
    private UpdateCoreTeamProfileByStaff updateCoreTeamProfile;
}
