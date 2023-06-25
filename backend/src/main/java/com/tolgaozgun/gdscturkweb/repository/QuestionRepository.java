package com.tolgaozgun.gdscturkweb.repository;


import com.tolgaozgun.gdscturkweb.entity.QuestionEntity;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    Optional<QuestionEntity> findById(@NonNull Long id);

    List<QuestionEntity> findAll();

    List<QuestionEntity> findAllByAnsweredBy(@NonNull UserEntity answeredBy);

    List<QuestionEntity> findAllByAskedBy(@NonNull UserEntity askedBy);
    List<QuestionEntity> findAllByAskedByOrAnsweredBy(@NonNull UserEntity askedBy, @NonNull UserEntity answeredBy);


}