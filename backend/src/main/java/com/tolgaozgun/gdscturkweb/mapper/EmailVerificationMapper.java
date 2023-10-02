package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.entity.CountryEntity;
import com.tolgaozgun.gdscturkweb.entity.EmailVerificationEntity;
import com.tolgaozgun.gdscturkweb.model.Country;
import com.tolgaozgun.gdscturkweb.model.EmailVerification;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmailVerificationMapper {

    private final ModelMapper modelMapper;

    public EmailVerificationEntity toEntity(EmailVerification emailVerification) {
        return modelMapper.map(emailVerification, EmailVerificationEntity.class);
    }

    public List<EmailVerification> toModel(List<EmailVerificationEntity> emailVerificationEntities) {
        return emailVerificationEntities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public EmailVerification toModel(EmailVerificationEntity emailVerificationEntity) {
        return modelMapper.map(emailVerificationEntity, EmailVerification.class);
    }
}