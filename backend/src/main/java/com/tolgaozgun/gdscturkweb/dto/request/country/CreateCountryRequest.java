package com.tolgaozgun.gdscturkweb.dto.request.country;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCountryRequest {

    @NotNull
    private String name;

    @NotNull
    private String flagImage;

}
