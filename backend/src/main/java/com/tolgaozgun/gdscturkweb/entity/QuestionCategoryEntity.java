package com.tolgaozgun.gdscturkweb.entity;

import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "question_categories")
@AllArgsConstructor
@NoArgsConstructor
public class QuestionCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_category_id", nullable = false)
    private Long questionCategoryId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String shortUrl;
}
