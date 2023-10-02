package com.tolgaozgun.gdscturkweb.entity;

import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "user_invites")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInviteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invite_id", nullable = false)
    private Long inviteId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean isValid;

    @Column(nullable = false)
    private String inviteCode;

    @Column(nullable = false)
    private UserType role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity invitedBy;

    @Column(nullable = false)
    private Date invitedAt;

    @Column(nullable = false)
    private Date validUntil;


}
