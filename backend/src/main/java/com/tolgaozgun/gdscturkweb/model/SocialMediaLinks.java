package com.tolgaozgun.gdscturkweb.model;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialMediaLinks {

    @Nullable
    private String facebook;

    @Nullable
    private String instagram;

    @Nullable
    private String twitter;

    @Nullable
    private String youtube;

    @Nullable
    private String linkedin;

    @Nullable
    private String github;

    @Nullable
    private String medium;

    @Nullable
    private String discord;

    @Nullable
    private List<String> others;

}
