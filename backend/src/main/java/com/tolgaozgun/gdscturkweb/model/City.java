package com.tolgaozgun.gdscturkweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    private Long cityId;

    @NotNull
    private String name;

    @NotNull
    private Country country;

    @NotNull
    private String latitude;

    @NotNull
    private String longitude;

}
