package com.tolgaozgun.gdscturkweb.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "core_team_members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoreTeamMemberEntity extends UserEntity {
}
