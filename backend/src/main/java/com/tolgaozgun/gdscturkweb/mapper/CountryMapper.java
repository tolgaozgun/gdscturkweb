package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.entity.CityEntity;
import com.tolgaozgun.gdscturkweb.entity.CountryEntity;
import com.tolgaozgun.gdscturkweb.model.City;
import com.tolgaozgun.gdscturkweb.model.Country;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CountryMapper {

    private final ModelMapper modelMapper;

    public CountryEntity toEntity(Country city) {
        CountryEntity countryEntity = modelMapper.map(city, CountryEntity.class);
        return countryEntity;
    }

    public List<Country> toModel(List<CountryEntity> countryEntities) {
        return countryEntities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Country toModel(CountryEntity cityEntity) {
        Country country = modelMapper.map(cityEntity, Country.class);
        return country;
    }
}