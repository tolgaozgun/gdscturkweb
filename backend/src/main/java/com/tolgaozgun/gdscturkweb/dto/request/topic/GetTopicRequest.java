package com.tolgaozgun.gdscturkweb.dto.request.topic;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTopicRequest {

    @NotNull
    private Long topicId;

}
