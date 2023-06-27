package com.tolgaozgun.gdscturkweb.entity.user;


import com.tolgaozgun.gdscturkweb.dto.user.register.UserRegister;
import com.tolgaozgun.gdscturkweb.entity.TopicEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private String profileImage;

    @Column(nullable = true)
    private String phoneNumber;

    @Column(nullable = true)
    private String biography;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    @ManyToMany
    @JoinTable(
            name = "user_topics",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private List<TopicEntity> interests;

    public UserEntity(UserRegister userRegister, UserType userType){
        this.username = userRegister.getUsername();
        this.password = userRegister.getPassword();
        this.name = userRegister.getName();
        this.surname = userRegister.getSurname();
        this.email = userRegister.getEmail();
        this.userType = userType;
    }

}