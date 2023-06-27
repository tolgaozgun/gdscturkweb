package com.tolgaozgun.gdscturkweb.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuddyTeamByFacilitatorRequest {
    private Long facilitatorId;
}
