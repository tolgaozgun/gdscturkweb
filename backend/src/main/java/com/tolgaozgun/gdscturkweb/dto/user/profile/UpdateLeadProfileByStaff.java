package com.tolgaozgun.gdscturkweb.dto.user.profile;

import com.tolgaozgun.gdscturkweb.dto.UserDTO;
import com.tolgaozgun.gdscturkweb.model.user.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLeadProfileByStaff {

    @Nullable
    private Long universityId;

    @Nullable
    private Long buddyTeamId;

}
