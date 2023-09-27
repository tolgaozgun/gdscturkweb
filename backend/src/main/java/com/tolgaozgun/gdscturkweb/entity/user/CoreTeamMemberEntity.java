package com.tolgaozgun.gdscturkweb.entity.user;


import com.tolgaozgun.gdscturkweb.entity.CoreTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "core_team_members")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoreTeamMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "core_team_member_id", nullable = false)
    private Long coreTeamMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private UniversityEntity university;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "core_team_id")
    private CoreTeamEntity coreTeam;


    public CoreTeamMemberEntity(UniversityEntity university, UserEntity user){
        this.university = university;
        this.user = user;
    }

}
