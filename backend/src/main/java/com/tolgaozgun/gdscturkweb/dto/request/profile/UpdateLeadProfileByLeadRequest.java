package com.tolgaozgun.gdscturkweb.dto.request.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tolgaozgun.gdscturkweb.dto.user.profile.UpdateLeadProfileByLead;
import com.tolgaozgun.gdscturkweb.dto.user.profile.UpdateUserProfileByUser;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLeadProfileByLeadRequest {

    @NotNull
    @JsonProperty("user")
    private UpdateUserProfileByUser updateUserProfile;

    @NotNull
    @JsonProperty("lead")
    private UpdateLeadProfileByLead updateLeadProfile;

}
