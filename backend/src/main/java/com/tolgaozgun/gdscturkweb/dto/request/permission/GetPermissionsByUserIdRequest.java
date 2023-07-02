package com.tolgaozgun.gdscturkweb.dto.request.permission;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPermissionsByUserIdRequest {

    @NotNull
    private Long userId;
}
