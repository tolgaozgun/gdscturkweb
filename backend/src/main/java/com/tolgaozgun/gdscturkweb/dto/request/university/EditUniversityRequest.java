package com.tolgaozgun.gdscturkweb.dto.request.university;

import jakarta.annotation.Nullable;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditUniversityRequest {

    @Id
    private Long universityId;

    @Nullable
    private Double latitude;

    @Nullable
    private Double longitude;

    @Nullable
    private String name;

    @Nullable
    private Long cityId;

    @Nullable
    private Long countryId;

}
