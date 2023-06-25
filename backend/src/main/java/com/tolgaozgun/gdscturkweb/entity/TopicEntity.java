package com.tolgaozgun.gdscturkweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "topics")
@AllArgsConstructor
@NoArgsConstructor
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id", nullable = false)
    private Long topicId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private String icon;

    @Column(nullable = false)
    private String color;
}
