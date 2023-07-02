package com.tolgaozgun.gdscturkweb.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    @Id
    private Long permissionId;

    @NotNull
    private String name;
}
