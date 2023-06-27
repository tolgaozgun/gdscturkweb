package com.tolgaozgun.gdscturkweb.service;


import com.tolgaozgun.gdscturkweb.dto.FacilitatorDTO;
import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.entity.user.FacilitatorEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.mapper.FacilitatorMapper;
import com.tolgaozgun.gdscturkweb.mapper.LeadMapper;
import com.tolgaozgun.gdscturkweb.repository.user.FacilitatorRepository;
import com.tolgaozgun.gdscturkweb.repository.user.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FacilitatorService {

    private final FacilitatorRepository facilitatorRepository;
    private final FacilitatorMapper facilitatorMapper;

    public List<FacilitatorDTO> getAllFacilitators() {
        try {
            List<FacilitatorEntity> facilitatorEntities = facilitatorRepository.findAll();
            return facilitatorMapper.toDTO(facilitatorEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


}
