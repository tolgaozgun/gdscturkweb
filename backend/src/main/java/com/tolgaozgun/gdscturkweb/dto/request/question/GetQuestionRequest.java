package com.tolgaozgun.gdscturkweb.dto.request.question;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetQuestionRequest {

    @NotNull
    private Long questionId;

}
