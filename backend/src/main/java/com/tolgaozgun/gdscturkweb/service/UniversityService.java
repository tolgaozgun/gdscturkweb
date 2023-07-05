package com.tolgaozgun.gdscturkweb.service;


import com.tolgaozgun.gdscturkweb.dto.request.university.CreateUniversityRequest;
import com.tolgaozgun.gdscturkweb.dto.request.university.EditUniversityRequest;
import com.tolgaozgun.gdscturkweb.entity.CityEntity;
import com.tolgaozgun.gdscturkweb.entity.CountryEntity;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.exception.CityNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.CountryNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.UniversityNotFoundException;
import com.tolgaozgun.gdscturkweb.mapper.UniversityMapper;
import com.tolgaozgun.gdscturkweb.model.University;
import com.tolgaozgun.gdscturkweb.repository.CityRepository;
import com.tolgaozgun.gdscturkweb.repository.CountryRepository;
import com.tolgaozgun.gdscturkweb.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UniversityService {

    private final UniversityRepository universityRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

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

    public University getUniversityById(Long universityId){
        try {
            Optional<UniversityEntity> optionalUniversityEntity = universityRepository.findById(universityId);

            if (optionalUniversityEntity.isEmpty()) {
                throw new UniversityNotFoundException();
            }

            UniversityEntity universityEntity = optionalUniversityEntity.get();
            return universityMapper.toModel(universityEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public University createUniversity(CreateUniversityRequest createUniversityRequest) {
        try {
            Long countryId = createUniversityRequest.getCountryId();

            Optional<CountryEntity> optionalCountryEntity = countryRepository.findById(countryId);

            if (optionalCountryEntity.isEmpty()) {
                throw new CountryNotFoundException();
            }

            CountryEntity countryEntity = optionalCountryEntity.get();

            Long cityId = createUniversityRequest.getCountryId();

            Optional<CityEntity> optionalCityEntity = cityRepository.findById(cityId);

            if (optionalCityEntity.isEmpty()) {
                throw new CityNotFoundException();
            }

            CityEntity cityEntity = optionalCityEntity.get();


            String name = createUniversityRequest.getName();
            Double latitude = createUniversityRequest.getLatitude();
            Double longitude = createUniversityRequest.getLongitude();

            UniversityEntity universityEntity = new UniversityEntity();
            universityEntity.setName(name);
            universityEntity.setCountry(countryEntity);
            universityEntity.setLatitude(latitude);
            universityEntity.setLongitude(longitude);
            universityEntity.setCity(cityEntity);
            universityEntity.setCountry(countryEntity);

            universityEntity = universityRepository.save(universityEntity);
            return universityMapper.toModel(universityEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public University editUniversity(Long universityId, EditUniversityRequest editUniversityRequest) {
        try {
            Optional<UniversityEntity> optionalUniversityEntity = universityRepository.findById(universityId);

            if (optionalUniversityEntity.isEmpty()) {
                throw new UniversityNotFoundException();
            }

            UniversityEntity universityEntity = optionalUniversityEntity.get();


            if (editUniversityRequest.getCityId() != null) {
                Long cityId = editUniversityRequest.getCityId();
                Optional<CityEntity> optionalCityEntity = cityRepository.findById(cityId);

                if (optionalCityEntity.isEmpty()) {
                    throw new CountryNotFoundException();
                }

                CityEntity cityEntity = optionalCityEntity.get();
                universityEntity.setCity(cityEntity);
            }

            if (editUniversityRequest.getCountryId() != null) {

                Long countryId = editUniversityRequest.getCountryId();

                Optional<CountryEntity> optionalCountryEntity = countryRepository.findById(countryId);

                if (optionalCountryEntity.isEmpty()) {
                    throw new CountryNotFoundException();
                }

                CountryEntity countryEntity = optionalCountryEntity.get();
                universityEntity.setCountry(countryEntity);
            }

            if (editUniversityRequest.getName() != null) {
                universityEntity.setName(editUniversityRequest.getName());
            }

            if (editUniversityRequest.getLatitude() != null) {
                universityEntity.setLatitude(editUniversityRequest.getLatitude());
            }

            if (editUniversityRequest.getLongitude() != null) {
                universityEntity.setLongitude(editUniversityRequest.getLongitude());
            }
            return universityMapper.toModel(universityRepository.save(universityEntity));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

}
