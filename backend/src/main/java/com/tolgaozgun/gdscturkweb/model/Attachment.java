package com.tolgaozgun.gdscturkweb.model;

import com.tolgaozgun.gdscturkweb.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {


    @Id
    private Long attachmentId;

    @NotNull
    private String attachmentName;

    @NotNull
    private String shortUrl;

    @NotNull
    private List<UserType> permittedUserTypes;
}
