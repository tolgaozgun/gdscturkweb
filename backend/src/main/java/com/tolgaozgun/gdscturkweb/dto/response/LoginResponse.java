package com.tolgaozgun.gdscturkweb.dto.response;

import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import com.tolgaozgun.gdscturkweb.model.Topic;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
        @Id
        private Long id;
        private String username;
        private String name;
        private String surname;
        private String email;
        private String profileImage;
        private String phoneNumber;
        private String biography;
        private List<Topic> interests;
        private UserType userType;
        private String accessToken;
        private String refreshToken;


        public LoginResponse(UserEntity user, List<Topic> interests, String accessToken, String refreshToken) {
                this.id = user.getUserId();
                this.name = user.getName();
                this.surname = user.getSurname();
                this.username = user.getUsername();
                this.email = user.getEmail();
                this.userType = user.getUserType();
                this.profileImage = user.getProfileImage();
                this.phoneNumber = user.getPhoneNumber();
                this.biography = user.getBiography();
                this.interests = interests;
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
        }
}
