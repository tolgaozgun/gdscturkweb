package com.tolgaozgun.gdscturkweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class University {

    @Id
    private Long universityId;

    @NotNull
    private Long latitude;

    @NotNull
    private Long longitude;

    @NotNull
    private String name;

    @NotNull
    private City city;

    @NotNull
    private Country country;




}
