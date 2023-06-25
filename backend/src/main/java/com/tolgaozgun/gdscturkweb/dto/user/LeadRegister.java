package com.tolgaozgun.gdscturkweb.dto.user;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadRegister {

    @NotNull
    private Long universityId;

    // Not needed during register
//    @Nullable
//    private Long buddyTeamId;

}
