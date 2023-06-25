package com.tolgaozgun.gdscturkweb.service;


import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.mapper.UniversityMapper;
import com.tolgaozgun.gdscturkweb.model.University;
import com.tolgaozgun.gdscturkweb.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UniversityService {

    private final UniversityRepository universityRepository;
    private final UniversityMapper universityMapper;

    public List<University> getAllUniversities() {
        try {
            List<UniversityEntity> universityEntities = universityRepository.findAll();
            return universityMapper.toModel(universityEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


}
