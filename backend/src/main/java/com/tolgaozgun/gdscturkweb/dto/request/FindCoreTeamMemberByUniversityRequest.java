package com.tolgaozgun.gdscturkweb.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindCoreTeamMemberByUniversityRequest {
    @NotNull
    private Long universityId;
}
