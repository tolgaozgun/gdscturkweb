package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.model.University;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UniversityMapper {

    private final ModelMapper modelMapper;

    public UniversityEntity toEntity(University university) {
        UniversityEntity universityEntity = modelMapper.map(university, UniversityEntity.class);
        return universityEntity;
    }

    public List<University> toModel(List<UniversityEntity> universityEntityList) {
        return universityEntityList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public University toModel(UniversityEntity universityEntity) {
        University university = modelMapper.map(universityEntity, University.class);
        return university;
    }
}