package com.tolgaozgun.gdscturkweb.service;


import com.tolgaozgun.gdscturkweb.dto.GooglerDTO;
import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.entity.user.GooglerEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.mapper.GooglerMapper;
import com.tolgaozgun.gdscturkweb.mapper.LeadMapper;
import com.tolgaozgun.gdscturkweb.model.user.Googler;
import com.tolgaozgun.gdscturkweb.repository.user.GooglerRepository;
import com.tolgaozgun.gdscturkweb.repository.user.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GooglerService {

    private final GooglerRepository googlerRepository;
    private final GooglerMapper googlerMapper;

    public List<GooglerDTO> getAllGooglers() {
        try {
            List<GooglerEntity> googlerEntities = googlerRepository.findAll();
            return googlerMapper.toDTO(googlerEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


}
