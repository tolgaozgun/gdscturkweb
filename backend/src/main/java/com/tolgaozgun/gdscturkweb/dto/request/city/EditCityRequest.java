package com.tolgaozgun.gdscturkweb.dto.request.city;


import jakarta.annotation.Nullable;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditCityRequest {

    @Nullable
    private String name;

    @Nullable
    private Long countryId;

    @Nullable
    private Double latitude;

    @Nullable
    private Double longitude;
}
