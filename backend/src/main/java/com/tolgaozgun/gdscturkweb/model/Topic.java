package com.tolgaozgun.gdscturkweb.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic {

    @Id
    private Long topicId;

    @NotNull
    private String name;

    @Nullable
    private String description;

    @NotNull
    private String icon;

    @Nullable
    private String color;
}