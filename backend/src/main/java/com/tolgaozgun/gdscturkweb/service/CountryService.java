package com.tolgaozgun.gdscturkweb.service;


import com.tolgaozgun.gdscturkweb.dto.request.country.CreateCountryRequest;
import com.tolgaozgun.gdscturkweb.dto.request.country.EditCountryRequest;
import com.tolgaozgun.gdscturkweb.entity.CountryEntity;
import com.tolgaozgun.gdscturkweb.exception.CountryNotFoundException;
import com.tolgaozgun.gdscturkweb.mapper.CountryMapper;
import com.tolgaozgun.gdscturkweb.model.Country;
import com.tolgaozgun.gdscturkweb.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public List<Country> getAllCountries() {
        try {
            List<CountryEntity> countryEntities = countryRepository.findAll();
            return countryMapper.toModel(countryEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public Country getCountry(Long countryId) {
        try {
            Optional<CountryEntity> optionalCountryEntity = countryRepository.findById(countryId);

            if (optionalCountryEntity.isEmpty()) {
                throw new CountryNotFoundException();
            }

            CountryEntity countryEntity = optionalCountryEntity.get();
            return countryMapper.toModel(countryEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public Country createCountry(CreateCountryRequest createCountryRequest) {
        try {
            String name = createCountryRequest.getName();
            String flagImage = createCountryRequest.getFlagImage();

            CountryEntity countryEntity = new CountryEntity();
            countryEntity.setName(name);
            countryEntity.setFlagImage(flagImage);

            countryEntity = countryRepository.save(countryEntity);
            return countryMapper.toModel(countryEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public Country editCountry(Long countryId, EditCountryRequest editCountryRequest) {
        try {
            Optional<CountryEntity> optionalCountryEntity = countryRepository.findById(countryId);

            if (optionalCountryEntity.isEmpty()) {
                throw new CountryNotFoundException();
            }

            CountryEntity countryEntity = optionalCountryEntity.get();

            if (editCountryRequest.getName() != null) {
                countryEntity.setName(editCountryRequest.getName());
            }

            if (editCountryRequest.getFlagImage() != null) {
                countryEntity.setFlagImage(editCountryRequest.getFlagImage());
            }

            return countryMapper.toModel(countryRepository.save(countryEntity));


        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


}
