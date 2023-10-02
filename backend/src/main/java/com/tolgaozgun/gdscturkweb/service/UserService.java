package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.exception.UserNotFoundException;
import com.tolgaozgun.gdscturkweb.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    protected UserEntity getUserEntityFromEmail(String email) {
        try {
            Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(email);
            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("User not found");
            }
            return optionalUserEntity.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    protected boolean userExists(String email) {
        try {
            Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(email);
            return optionalUserEntity.isPresent();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    protected UserEntity getCurrentUserEntity() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            String userName = authentication.getName();

            Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userName);

            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("Error while getting user details");
            }

            return optionalUserEntity.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    protected UserEntity save(UserEntity userEntity) {
        try {
            return userRepository.save(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
