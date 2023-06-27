package com.tolgaozgun.gdscturkweb.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "googlers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GooglerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "googler_id", nullable = false)
    private Long googlerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


}
