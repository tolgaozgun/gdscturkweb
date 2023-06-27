package com.tolgaozgun.gdscturkweb.model.user;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Googler{

    @Id
    private Long googlerId;

    @NotNull
    private User user;


}
