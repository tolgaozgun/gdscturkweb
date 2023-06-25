package com.tolgaozgun.gdscturkweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", nullable = false)
    private Long cityId;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;

}
