package com.tolgaozgun.gdscturkweb.dto.user.profile;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGooglerProfileByStaff {

    @NotNull
    private Long googlerId;

    @Nullable
    private Long cityId;

    @Nullable
    private Long countryId;

}
