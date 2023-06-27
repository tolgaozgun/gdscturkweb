package com.tolgaozgun.gdscturkweb.dto.request.announcement;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAnnouncementRequest {

    @NotNull
    private Long announcementId;

}
