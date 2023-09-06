package com.tolgaozgun.gdscturkweb.dto.request.buddyTeam;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tolgaozgun.gdscturkweb.model.user.Facilitator;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBuddyTeamRequest {

    @Nullable
    @JsonProperty("facilitator")
    private Long facilitatorId;

    @JsonProperty("leads")
    @Nullable
    private List<Long> leadIds;

}
