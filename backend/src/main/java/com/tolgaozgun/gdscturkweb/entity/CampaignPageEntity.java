package com.tolgaozgun.gdscturkweb.entity;

import com.tolgaozgun.gdscturkweb.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name = "campaign_pages")
@AllArgsConstructor
@NoArgsConstructor
public class CampaignPageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_page_id", nullable = false)
    private Long campaignPageId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "campaign_id")
    private List<AttachmentEntity> attachments;

    @ElementCollection
    @CollectionTable(name = "campaign_page_permitted_user_types",
            joinColumns = @JoinColumn(name = "campaign_page_id"))
    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private List<UserType> permittedUserTypes;
}
