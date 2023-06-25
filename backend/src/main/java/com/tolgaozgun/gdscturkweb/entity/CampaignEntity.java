package com.tolgaozgun.gdscturkweb.entity;

import com.tolgaozgun.gdscturkweb.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "campaigns")
@AllArgsConstructor
@NoArgsConstructor
public class CampaignEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id", nullable = false)
    private Long campaignId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "campaign_id")
    private List<AttachmentEntity> attachments;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "campaign_id")
    private List<QuestionEntity> questions;

    @ElementCollection
    @CollectionTable(name = "campaign_permitted_user_types", joinColumns = @JoinColumn(name = "campaign_id"))
    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private List<UserType> permittedUserTypes;
}