package com.tolgaozgun.gdscturkweb.dto.user.profile;

import com.tolgaozgun.gdscturkweb.model.Topic;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserProfileByStaff {

    @NotNull
    private Long userId;

    @Nullable
    private String name;

    @Nullable
    private String surname;

    @Nullable
    private String email;

    @Nullable
    private String profileImage;

    @Nullable
    private String phoneNumber;

    @Nullable
    private String biography;

    @Nullable
    private List<Topic> interests;
}