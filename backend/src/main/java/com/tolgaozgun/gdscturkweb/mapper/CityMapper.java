package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.dto.CoreTeamMemberDTO;
import com.tolgaozgun.gdscturkweb.dto.UserDTO;
import com.tolgaozgun.gdscturkweb.entity.CityEntity;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.entity.user.CoreTeamMemberEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.model.City;
import com.tolgaozgun.gdscturkweb.model.University;
import com.tolgaozgun.gdscturkweb.model.user.CoreTeamMember;
import com.tolgaozgun.gdscturkweb.repository.BuddyTeamRepository;
import com.tolgaozgun.gdscturkweb.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CityMapper {

    private final ModelMapper modelMapper;

    public CityEntity toEntity(City city) {
        CityEntity cityEntity = modelMapper.map(city, CityEntity.class);
        return cityEntity;
    }

    public List<City> toModel(List<CityEntity> cityEntities) {
        return cityEntities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public City toModel(CityEntity cityEntity) {
        City city = modelMapper.map(cityEntity, City.class);
        return city;
    }
}