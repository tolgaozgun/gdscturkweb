package com.tolgaozgun.gdscturkweb.dto.request.city;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCityRequest {

    @NotNull
    private Long cityId;

}
