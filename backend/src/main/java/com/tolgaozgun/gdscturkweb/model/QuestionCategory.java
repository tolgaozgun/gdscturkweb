package com.tolgaozgun.gdscturkweb.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCategory {

    @Id
    private Long questionCategoryId;

    @NotNull
    private String name;

    @NotNull
    private String image;

    @NotNull
    private String shortUrl;
}
