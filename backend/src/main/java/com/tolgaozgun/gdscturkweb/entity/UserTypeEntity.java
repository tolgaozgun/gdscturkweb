package com.tolgaozgun.gdscturkweb.entity;


import com.tolgaozgun.gdscturkweb.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "user_types")
@AllArgsConstructor
@NoArgsConstructor
public class UserTypeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_type_id", nullable = false)
    private Long userTypeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType;

    @ManyToMany
    @JoinTable(
            name = "user_type_permissions",
            joinColumns = @JoinColumn(name = "user_type_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<PermissionEntity> permissions;

}
