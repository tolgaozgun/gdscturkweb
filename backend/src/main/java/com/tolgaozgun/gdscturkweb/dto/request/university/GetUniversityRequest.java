package com.tolgaozgun.gdscturkweb.dto.request.university;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUniversityRequest {

    @NotNull
    private Long universityId;

}
