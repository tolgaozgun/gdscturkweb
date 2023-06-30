package com.tolgaozgun.gdscturkweb.dto.user.profile;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class UpdateGooglerProfileByGoogler {

    @Nullable
    private Long cityId;

    @Nullable
    private Long countryId;


}
