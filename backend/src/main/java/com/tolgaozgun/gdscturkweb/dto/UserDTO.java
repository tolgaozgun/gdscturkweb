package com.tolgaozgun.gdscturkweb.dto;

import com.tolgaozgun.gdscturkweb.enums.UserType;
import com.tolgaozgun.gdscturkweb.model.user.User;
import jakarta.persistence.Column;
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
    private String profileImage;
    private String phoneNumber;
    private String biography;

    public UserDTO(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.username = userDTO.getUsername();
        this.name = userDTO.getName();
        this.surname = userDTO.getSurname();
        this.userType = userDTO.getUserType();
        this.profileImage = userDTO.getProfileImage();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.biography = userDTO.getBiography();
    }

}