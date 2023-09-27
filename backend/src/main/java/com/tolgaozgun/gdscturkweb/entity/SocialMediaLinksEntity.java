package com.tolgaozgun.gdscturkweb.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "social_media_links")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialMediaLinksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "social_media_links_id", nullable = false)
    private Long socialMediaLinksId;

    private String facebook;

    private String instagram;

    private String twitter;

    private String youtube;

    private String linkedin;

    private String github;

    private String medium;

    private String discord;



}
