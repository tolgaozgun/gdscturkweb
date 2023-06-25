package com.tolgaozgun.gdscturkweb.entity.user;

import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "facilitators")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacilitatorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facilitator_id", nullable = false)
    private Long facilitatorId;

    @OneToOne(mappedBy = "facilitator", cascade = CascadeType.ALL)
    private BuddyTeamEntity buddyTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private UniversityEntity universityEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


}