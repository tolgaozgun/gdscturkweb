package com.tolgaozgun.gdscturkweb.dto;

import com.tolgaozgun.gdscturkweb.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String username;
    private String password;
}
