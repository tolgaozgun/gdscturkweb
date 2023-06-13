package com.tolgaozgun.gdscturkweb.model;

import com.tolgaozgun.gdscturkweb.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private UserType userType;
}