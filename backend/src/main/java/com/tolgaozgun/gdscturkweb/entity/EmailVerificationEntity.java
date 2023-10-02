package com.tolgaozgun.gdscturkweb.entity;

import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "email_verifications")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailVerificationEntity {

    private final static long VALID_TIME_IN_HOURS = 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_verification_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private String verificationCode;

    @Column(nullable = false)
    private Boolean isUsed;

    @Column(nullable = false)
    private Date validUntil;

    public EmailVerificationEntity(UserEntity user) {
        this.user = user;
        this.verificationCode = UUID.randomUUID().toString();
        this.isUsed = false;
        this.validUntil = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * VALID_TIME_IN_HOURS);
    }



}
