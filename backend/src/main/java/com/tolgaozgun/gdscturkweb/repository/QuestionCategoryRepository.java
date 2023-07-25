package com.tolgaozgun.gdscturkweb.repository;


import com.tolgaozgun.gdscturkweb.entity.QuestionCategoryEntity;
import com.tolgaozgun.gdscturkweb.entity.QuestionEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionCategoryRepository extends JpaRepository<QuestionCategoryEntity, Long> {

    Optional<QuestionCategoryEntity> findById(@NonNull Long id);

    List<QuestionCategoryEntity> findAll();

}