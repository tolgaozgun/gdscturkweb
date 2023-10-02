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
public class EventDate {
    @NotNull
    private Date startDate;

    @Nullable
    private Date endDate;
}