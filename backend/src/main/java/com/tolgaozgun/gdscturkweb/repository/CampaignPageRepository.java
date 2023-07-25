package com.tolgaozgun.gdscturkweb.repository;

import com.tolgaozgun.gdscturkweb.entity.CampaignEntity;
import com.tolgaozgun.gdscturkweb.entity.CampaignPageEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampaignPageRepository extends JpaRepository<CampaignPageEntity, Long> {

    Optional<CampaignPageEntity> findById(@NonNull Long id);

    List<CampaignPageEntity> findAll();


}