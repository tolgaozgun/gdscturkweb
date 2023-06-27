package com.tolgaozgun.gdscturkweb.dto.request.topic;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTopicRequest {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String icon;

    @Nullable
    private String color;

}
