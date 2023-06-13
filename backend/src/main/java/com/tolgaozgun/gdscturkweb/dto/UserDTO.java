package com.tolgaozgun.gdscturkweb.dto;

import com.tolgaozgun.gdscturkweb.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private UserType userType;

    // Constructors, getters, setters
}