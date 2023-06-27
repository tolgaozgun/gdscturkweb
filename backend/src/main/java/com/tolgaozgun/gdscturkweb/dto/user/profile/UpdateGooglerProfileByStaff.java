package com.tolgaozgun.gdscturkweb.dto.user.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGooglerProfileByStaff {

    private Long googlerId;

    private Long userId;


}
