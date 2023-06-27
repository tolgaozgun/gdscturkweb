package com.tolgaozgun.gdscturkweb.dto.user.profile;

import com.tolgaozgun.gdscturkweb.model.Topic;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserProfileByUser {

    @Nullable
    private String name;

    @Nullable
    private String surname;

    @Nullable
    private String profileImage;

    @Nullable
    private String phoneNumber;

    @Nullable
    private String biograpghy;

    @Nullable
    private List<Topic> interests;
}
