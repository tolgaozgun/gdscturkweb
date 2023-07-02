package com.tolgaozgun.gdscturkweb.entity;


import com.tolgaozgun.gdscturkweb.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permissions")
@AllArgsConstructor
@NoArgsConstructor
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id", nullable = false)
    private Long permissionId;

    @Column(nullable = false)
    private String name;


}
