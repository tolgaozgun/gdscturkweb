package com.tolgaozgun.gdscturkweb.dto;

import com.tolgaozgun.gdscturkweb.entity.UserEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginTokenDTO {
        @Id
        private Long id;
        private String username;
        private String name;
        private String surname;
        private String email;
        private UserType userType;
        private String accessToken;
        private String refreshToken;


        public LoginTokenDTO(UserEntity user, String accessToken, String refreshToken) {
                this.id = user.getId();
                this.name = user.getName();
                this.surname = user.getSurname();
                this.username = user.getUsername();
                this.email = user.getEmail();
                this.userType = user.getUserType();
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
        }
}
