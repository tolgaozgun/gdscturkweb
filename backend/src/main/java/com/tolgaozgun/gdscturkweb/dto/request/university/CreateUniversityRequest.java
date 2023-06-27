package com.tolgaozgun.gdscturkweb.dto.request.university;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUniversityRequest {

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private String name;

    @NotNull
    private Long cityId;

    @NotNull
    private Long countryId;

}
