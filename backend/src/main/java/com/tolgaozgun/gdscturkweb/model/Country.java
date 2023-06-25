package com.tolgaozgun.gdscturkweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    private Long countryId;

    @NotNull
    private String name;

    @NotNull
    private String flagImage;

}
