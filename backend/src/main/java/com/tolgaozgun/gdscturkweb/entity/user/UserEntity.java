package com.tolgaozgun.gdscturkweb.entity.user;


import com.tolgaozgun.gdscturkweb.dto.user.register.UserRegister;
import com.tolgaozgun.gdscturkweb.entity.PermissionEntity;
import com.tolgaozgun.gdscturkweb.entity.SocialMediaLinksEntity;
import com.tolgaozgun.gdscturkweb.entity.TopicEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
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

    @Column(nullable = true, columnDefinition = "TEXT")
    private String biography;

    @Column(nullable = false)
    private Boolean isVerified;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isEmailVerified;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isBlackListed;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    @Column
    private Date promotedAt;

    @ManyToMany
    @JoinTable(
            name = "user_topics",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private List<TopicEntity> interests;

    @ManyToMany
    @JoinTable(
            name = "additional_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<PermissionEntity> additionalPermissions;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date lastLoginDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date lastEditedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "social_media_links_id")
    private SocialMediaLinksEntity socialMediaLinksEntity;


    public UserEntity(UserRegister userRegister, UserType userType){
        this.username = userRegister.getUsername();
        this.password = userRegister.getPassword();
        this.name = userRegister.getName();
        this.surname = userRegister.getSurname();
        this.email = userRegister.getEmail();
        this.userType = userType;
        this.isVerified = false;
        this.interests = new ArrayList<>();
        this.additionalPermissions = new ArrayList<>();
    }

}