package com.tolgaozgun.gdscturkweb.repository;

import com.tolgaozgun.gdscturkweb.entity.CampaignEntity;
import com.tolgaozgun.gdscturkweb.entity.TopicEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<CampaignEntity, Long> {

    Optional<CampaignEntity> findById(@NonNull Long id);

    List<CampaignEntity> findAllByStartDateAfterAndEndDateBefore(Date startDate, Date endDate);

    List<CampaignEntity> findAll();


}