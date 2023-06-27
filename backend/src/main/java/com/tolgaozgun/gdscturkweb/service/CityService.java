package com.tolgaozgun.gdscturkweb.service;


import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.dto.request.city.CreateCityRequest;
import com.tolgaozgun.gdscturkweb.dto.request.city.EditCityRequest;
import com.tolgaozgun.gdscturkweb.dto.request.city.GetCityRequest;
import com.tolgaozgun.gdscturkweb.dto.request.country.CreateCountryRequest;
import com.tolgaozgun.gdscturkweb.dto.request.country.EditCountryRequest;
import com.tolgaozgun.gdscturkweb.dto.request.country.GetCountryRequest;
import com.tolgaozgun.gdscturkweb.entity.CityEntity;
import com.tolgaozgun.gdscturkweb.entity.CountryEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.exception.CountryNotFoundException;
import com.tolgaozgun.gdscturkweb.mapper.CityMapper;
import com.tolgaozgun.gdscturkweb.mapper.LeadMapper;
import com.tolgaozgun.gdscturkweb.model.City;
import com.tolgaozgun.gdscturkweb.model.Country;
import com.tolgaozgun.gdscturkweb.repository.CityRepository;
import com.tolgaozgun.gdscturkweb.repository.CountryRepository;
import com.tolgaozgun.gdscturkweb.repository.user.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final CityMapper cityMapper;

    public List<City> getAllCities() {
        try {
            List<CityEntity> cityEntities = cityRepository.findAll();
            return cityMapper.toModel(cityEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    public City getCity(GetCityRequest getCityRequest) {
        try {

            Long cityId = getCityRequest.getCityId();
            Optional<CityEntity> optionalCityEntity = cityRepository.findById(cityId);

            if (optionalCityEntity.isEmpty()) {
                throw new CountryNotFoundException();
            }

            CityEntity cityEntity = optionalCityEntity.get();
            return cityMapper.toModel(cityEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public City createCity(CreateCityRequest createCityRequest) {
        try {
            Long countryId = createCityRequest.getCountryId();

            Optional<CountryEntity> optionalCountryEntity = countryRepository.findById(countryId);

            if (optionalCountryEntity.isEmpty()) {
                throw new CountryNotFoundException();
            }

            CountryEntity countryEntity = optionalCountryEntity.get();
            String name = createCityRequest.getName();
            Double latitude = createCityRequest.getLatitude();
            Double longitude = createCityRequest.getLongitude();

            CityEntity cityEntity = new CityEntity();
            cityEntity.setName(name);
            cityEntity.setCountry(countryEntity);
            cityEntity.setLatitude(latitude);
            cityEntity.setLongitude(longitude);

            cityEntity = cityRepository.save(cityEntity);
            return cityMapper.toModel(cityEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public City editCity(EditCityRequest editCityRequest) {
        try {
            Long cityId = editCityRequest.getCityId();
            Optional<CityEntity> optionalCityEntity = cityRepository.findById(cityId);

            if (optionalCityEntity.isEmpty()) {
                throw new CountryNotFoundException();
            }

            CityEntity cityEntity = optionalCityEntity.get();

            if (editCityRequest.getCountryId() != null) {

                Long countryId = editCityRequest.getCountryId();

                Optional<CountryEntity> optionalCountryEntity = countryRepository.findById(countryId);

                if (optionalCountryEntity.isEmpty()) {
                    throw new CountryNotFoundException();
                }

                CountryEntity countryEntity = optionalCountryEntity.get();
                cityEntity.setCountry(countryEntity);
            }

            if (editCityRequest.getName() != null) {
                cityEntity.setName(editCityRequest.getName());
            }

            if (editCityRequest.getLatitude() != null) {
                cityEntity.setLatitude(editCityRequest.getLatitude());
            }

            if (editCityRequest.getLongitude() != null) {
                cityEntity.setLongitude(editCityRequest.getLongitude());
            }
            return cityMapper.toModel(cityRepository.save(cityEntity));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


}
