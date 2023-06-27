package com.tolgaozgun.gdscturkweb.dto.request.city;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCityRequest {

    @NotNull
    private String name;

    @NotNull
    private Long countryId;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;
}
