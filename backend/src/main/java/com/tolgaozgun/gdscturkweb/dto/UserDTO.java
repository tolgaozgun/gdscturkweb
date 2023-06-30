package com.tolgaozgun.gdscturkweb.dto;

import com.tolgaozgun.gdscturkweb.enums.UserType;
import com.tolgaozgun.gdscturkweb.model.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String username;
    private String name;
    private String surname;
    private String email;
    private UserType userType;
    private String profileImage;
    private String phoneNumber;
    private String biography;
    private List<Topic> interests;

}