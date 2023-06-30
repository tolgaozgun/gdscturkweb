package com.tolgaozgun.gdscturkweb.entity.user;

import com.tolgaozgun.gdscturkweb.entity.CityEntity;
import com.tolgaozgun.gdscturkweb.entity.CountryEntity;
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
    @JoinColumn(name = "city_id", nullable = true)
    private CityEntity city;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id", nullable = true)
    private CountryEntity country;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


}
