package com.tolgaozgun.gdscturkweb.model.user;

import com.tolgaozgun.gdscturkweb.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private UserType userType;
    private String name;
    private String surname;
    private String email;
    private String profileImage;
    private String phoneNumber;
    private String biography;
    private Date lastLoginDate;
    private Date createdAt;
    private Date lastEditedAt;

    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.userType = user.getUserType();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.profileImage = user.getProfileImage();
        this.phoneNumber = user.getPhoneNumber();
        this.biography = user.getBiography();
    }
}