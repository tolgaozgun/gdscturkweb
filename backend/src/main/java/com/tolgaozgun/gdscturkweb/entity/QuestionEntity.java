package com.tolgaozgun.gdscturkweb.entity;

import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "questions")
@AllArgsConstructor
@NoArgsConstructor
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String question;

    @Column(nullable = true)
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asked_user_id", nullable = false)
    private UserEntity askedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answered_user_id", nullable = true)
    private UserEntity answeredBy;

    @Column(nullable = false)
    private Date askedDate;

    @Column(nullable = true)
    private Date answeredDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_category_id", nullable = false)
    private QuestionCategoryEntity category;
}
