package com.tolgaozgun.gdscturkweb.dto.request.question;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetQuestionsAskedByRequest {
    private Long userId;
}
