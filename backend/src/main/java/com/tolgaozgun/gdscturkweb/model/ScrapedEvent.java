package com.tolgaozgun.gdscturkweb.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrapedEvent {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String type;

    @NotNull
    private String link;

    @NotNull
    private String address;

    @NotNull
    private String displayDate;

    @Nullable
    private Date parsedDate;
}
