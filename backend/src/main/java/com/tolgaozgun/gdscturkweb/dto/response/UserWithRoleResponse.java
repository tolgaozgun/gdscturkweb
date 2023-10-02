package com.tolgaozgun.gdscturkweb.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tolgaozgun.gdscturkweb.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWithRoleResponse {

    @JsonProperty("user")
    private UserDTO userDTO;

    @JsonProperty("extra")
    private Object extra;
}
