package com.tolgaozgun.gdscturkweb.dto;

import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    @Id
    private Long questionId;

    @NotNull
    private String question;

    @Nullable
    private String answer;

    @NotNull
    private UserDTO askedBy;

    @Nullable
    private UserDTO answeredBy;

    @NotNull
    private Date askedDate;

    @Nullable
    private Date answeredDate;
}
