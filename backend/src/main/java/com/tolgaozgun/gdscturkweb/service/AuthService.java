package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.dto.*;
import com.tolgaozgun.gdscturkweb.dto.request.LoginRequest;
import com.tolgaozgun.gdscturkweb.dto.request.VerifyUserRequest;
import com.tolgaozgun.gdscturkweb.dto.request.register.CoreTeamRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.request.register.FacilitatorRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.request.register.GooglerRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.request.register.LeadRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.response.LoginResponse;
import com.tolgaozgun.gdscturkweb.dto.response.UserWithRoleResponse;
import com.tolgaozgun.gdscturkweb.dto.user.register.*;
import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.user.*;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import com.tolgaozgun.gdscturkweb.exception.PasswordNotMatchException;
import com.tolgaozgun.gdscturkweb.exception.UserAlreadyExistsException;
import com.tolgaozgun.gdscturkweb.exception.UserNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.verification.UserAlreadyUnverifiedException;
import com.tolgaozgun.gdscturkweb.exception.verification.UserAlreadyVerifiedException;
import com.tolgaozgun.gdscturkweb.exception.verification.UserNotVerifiedException;
import com.tolgaozgun.gdscturkweb.mapper.*;
import com.tolgaozgun.gdscturkweb.model.Topic;
import com.tolgaozgun.gdscturkweb.model.user.CoreTeamMember;
import com.tolgaozgun.gdscturkweb.repository.BuddyTeamRepository;
import com.tolgaozgun.gdscturkweb.repository.UniversityRepository;
import com.tolgaozgun.gdscturkweb.repository.user.*;
import com.tolgaozgun.gdscturkweb.security.JWTUserService;
import com.tolgaozgun.gdscturkweb.security.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {
    public static int hashStrength = 10;

    // Utils

    @Autowired
    final BCryptPasswordEncoder bCryptPasswordEncoder;

    // Mappers

    private final UserMapper userMapper;
    private final LeadMapper leadMapper;
    private final UniversityMapper universityMapper;
    private final GooglerMapper googlerMapper;
    private final CoreTeamMapper coreTeamMapper;
    private final FacilitatorMapper facilitatorMapper;
    private final TopicMapper topicMapper;

    // JWT

    private final JWTUserService jwtUserService;

    // Repositories

    private final BuddyTeamRepository buddyTeamRepository;
    private final UniversityRepository universityRepository;
    private final CoreTeamMemberRepository coreTeamMemberRepository;
    private final UserRepository userRepository;
    private final LeadRepository leadRepository;
    private final FacilitatorRepository facilitatorRepository;
    private final GooglerRepository googlerRepository;

    @Autowired
    private final JWTUtils jwtUtils;

    public UserDTO getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toDTO(userEntity);
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

    public UserWithRoleResponse getCurrentUserWithRole() {
        try {
            UserEntity userEntity = getCurrentUserEntity();
            UserWithRoleResponse userWithRoleResponse = new UserWithRoleResponse();
            userWithRoleResponse.setUserDTO(userMapper.toDTO(userEntity));

            switch(userEntity.getUserType()) {
                case LEAD -> {
                    Optional<LeadEntity> optionalLeadEntity = leadRepository.findByUser(userEntity);
                    if (optionalLeadEntity.isEmpty()) {
                        throw new UserNotFoundException("Error while getting user details");
                    }
                    userWithRoleResponse.setExtra(leadMapper.toDTO(optionalLeadEntity.get()));
                }

                case FACILITATOR -> {
                    Optional<FacilitatorEntity> optionalFacilitatorEntity = facilitatorRepository.findByUser(userEntity);
                    if (optionalFacilitatorEntity.isEmpty()) {
                        throw new UserNotFoundException("Error while getting user details");
                    }
                    userWithRoleResponse.setExtra(facilitatorMapper.toDTO(optionalFacilitatorEntity.get()));
                }

                case GOOGLER ->  {
                    Optional<GooglerEntity> optionalGooglerEntity = googlerRepository.findByUser(userEntity);
                    if (optionalGooglerEntity.isEmpty()) {
                        throw new UserNotFoundException("Error while getting user details");
                    }
                    userWithRoleResponse.setExtra(googlerMapper.toDTO(optionalGooglerEntity.get()));
                }

                case CORE_TEAM_MEMBER -> {
                    Optional<CoreTeamMemberEntity> optionalCoreTeamMemberEntity = coreTeamMemberRepository.findByUser(userEntity);
                    if (optionalCoreTeamMemberEntity.isEmpty()) {
                        throw new UserNotFoundException("Error while getting user details");
                    }
                    userWithRoleResponse.setExtra(coreTeamMapper.toDTO(optionalCoreTeamMemberEntity.get()));
                }
            }
            return userWithRoleResponse;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public UserDTO getCurrentUser() {
        try {
            return userMapper.toDTO(getCurrentUserEntity());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public LoginResponse login(LoginRequest user) throws Exception {
        try {
//            Optional<UserEntity> usernameEntity = userRepository.findByUsername(user.getUsername());
            Optional<UserEntity> emailEntity = userRepository.findByEmail(user.getEmail());

            if (emailEntity.isEmpty()) {
                throw new UserNotFoundException("User is not found");
            }
            UserEntity dbUser = emailEntity.get();

            if (!dbUser.getIsVerified()) {
                throw new UserNotVerifiedException();
            }

//            System.out.println(user.getUsername());
//
//            if (usernameEntity.isEmpty()) {
//                if (emailEntity.isEmpty()) {
//                    throw new UserNotFoundException("user is not found");
//                } else {
//                    dbUser = emailEntity.get();
//                }
//            } else {
//                dbUser = usernameEntity.get();
//            }

            String hashedPassword = dbUser.getPassword();
            boolean passwordMatch = bCryptPasswordEncoder.matches(user.getPassword(), hashedPassword); /// change

            if (!passwordMatch) {
                throw new PasswordNotMatchException("passwords do not match");
            }

            System.out.println("passwords are matched");

            List<Topic> interests = topicMapper.toModel(dbUser.getInterests());

            final UserDetails userDetails = jwtUserService.loadUserByEmail(user.getEmail());
            final String accessToken = jwtUtils.createAccessToken(userDetails);
            final String refreshToken = jwtUtils.createRefreshToken(userDetails);
            return new LoginResponse(dbUser, interests, accessToken, refreshToken);
        } catch (Exception e) {
            System.out.println("login exception");
            e.printStackTrace();
            throw e;
        }
    }

    protected UserEntity checkAndRegisterUser(UserRegister userRegister, UserType userType) throws Exception {
        boolean userExist = userRepository.existsByUsername(userRegister.getUsername());

        if (userExist) {
            throw new UserAlreadyExistsException("This username already exists");
        }

        boolean emailExist = userRepository.existsByEmail(userRegister.getEmail());

        if (emailExist) {
            throw new UserAlreadyExistsException("This email already exists");
        }
        userRegister.setPassword(encodePassword(userRegister.getPassword()));

        UserEntity userEntity = new UserEntity(userRegister, userType);

        return userRepository.save(userEntity);

    }

    public UserDTO verifyUser(VerifyUserRequest verifyUserRequest) {
        try {

            // TODO: Add permissions
            Long userId = verifyUserRequest.getUserId();

            UserEntity userEntity = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));

            if (userEntity.getIsVerified()) {
                throw new UserAlreadyVerifiedException();
            }

            userEntity.setIsVerified(true);

            return userMapper.toDTO(userRepository.save(userEntity));

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public UserDTO unverifyUser(VerifyUserRequest verifyUserRequest) {
        try {

            // TODO: Add permissions

            Long userId = verifyUserRequest.getUserId();

            UserEntity userEntity = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));

            if (!userEntity.getIsVerified()) {
                throw new UserAlreadyUnverifiedException();
            }

            userEntity.setIsVerified(false);

            return userMapper.toDTO(userRepository.save(userEntity));

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String encodePassword(String plainPassword) {
        try {
            return bCryptPasswordEncoder.encode(plainPassword);
        } catch (Exception e) {
            throw e;
        }
    }

}