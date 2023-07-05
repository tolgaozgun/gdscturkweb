package com.tolgaozgun.gdscturkweb.dto.request.topic;

import jakarta.annotation.Nullable;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditTopicRequest {

    @Nullable
    private String name;

    @Nullable
    private String description;

    @Nullable
    private String icon;

    @Nullable
    private String color;

}
