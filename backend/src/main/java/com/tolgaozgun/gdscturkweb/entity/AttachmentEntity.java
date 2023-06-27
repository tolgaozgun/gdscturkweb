package com.tolgaozgun.gdscturkweb.entity;

import com.tolgaozgun.gdscturkweb.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "attachments")
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id", nullable = false)
    private Long attachmentId;

    @Column(nullable = false)
    private String attachmentName;

    @Column(nullable = false)
    private String shortUrl;

    @ElementCollection
    @CollectionTable(name = "campaign_permitted_user_types", joinColumns = @JoinColumn(name = "campaign_id"))
    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private List<UserType> permittedUserTypes;

}
