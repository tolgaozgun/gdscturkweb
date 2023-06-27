package com.tolgaozgun.gdscturkweb.service;


import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.mapper.LeadMapper;
import com.tolgaozgun.gdscturkweb.repository.user.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LeadService {

    private final LeadRepository leadRepository;
    private final LeadMapper leadMapper;

    public List<LeadDTO> getAllLeads() {
        try {
            List<LeadEntity> leadEntities = leadRepository.findAll();
            return leadMapper.toDTO(leadEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


}
