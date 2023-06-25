package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.model.University;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UniversityMapper {

    private final ModelMapper modelMapper;

    public UniversityEntity toEntity(University university) {
        UniversityEntity universityEntity = modelMapper.map(university, UniversityEntity.class);
        return universityEntity;
    }

    public List<University> toModel(List<UniversityEntity> universityEntityList) {
        List<University> universityList = new ArrayList<>();
        for (UniversityEntity universityEntity : universityEntityList) {
            universityList.add(toModel(universityEntity));
        }
        return universityList;
    }

    public University toModel(UniversityEntity universityEntity) {
        University university = modelMapper.map(universityEntity, University.class);
        return university;
    }
}