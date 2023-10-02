package com.tolgaozgun.gdscturkweb.repository;

import com.tolgaozgun.gdscturkweb.entity.EventEntity;
import com.tolgaozgun.gdscturkweb.entity.TopicEntity;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    Optional<EventEntity> findById(@NonNull Long id);

    List<EventEntity> findAll();

    Optional<EventEntity> findByLink(@NonNull String link);

    List<EventEntity> findAllByOrganizersContaining(@NonNull UniversityEntity organizer);

    List<EventEntity> findAllByOrganizersIn(@NonNull List<UniversityEntity> organizers);


    List<EventEntity> findAllByStartDateAfter(@NonNull Date startDate);


}