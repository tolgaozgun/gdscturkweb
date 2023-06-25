package com.tolgaozgun.gdscturkweb.model;

import com.tolgaozgun.gdscturkweb.model.user.Facilitator;
import com.tolgaozgun.gdscturkweb.model.user.Lead;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuddyTeam {
    private Long id;
    private Facilitator facilitator;
    private List<Lead> leads;

}