package com.tolgaozgun.gdscturkweb.model;

import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.entity.TopicEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    private Long eventId;

    @NotNull
    private List<UniversityEntity> organizers;

    @NotNull
    private String title;

    @Nullable
    private String description;

    @NotNull
    private List<TopicEntity> topics;
}
