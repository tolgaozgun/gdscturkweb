package com.tolgaozgun.gdscturkweb.dto.request.country;

import jakarta.annotation.Nullable;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditCountryRequest {

    @Nullable
    private String name;

    @Nullable
    private String flagImage;

}
