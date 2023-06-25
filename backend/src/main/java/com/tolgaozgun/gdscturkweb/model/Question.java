package com.tolgaozgun.gdscturkweb.model;

import com.tolgaozgun.gdscturkweb.model.user.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    private Long questionId;

    @NotNull
    private String question;

    @Nullable
    private String answer;

    @NotNull
    private User askedBy;

    @Nullable
    private User answeredBy;
}
