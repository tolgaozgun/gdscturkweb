package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.dto.*;
import com.tolgaozgun.gdscturkweb.dto.request.LoginRequest;
import com.tolgaozgun.gdscturkweb.dto.request.VerifyUserRequest;
import com.tolgaozgun.gdscturkweb.dto.request.register.CoreTeamRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.request.register.FacilitatorRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.request.register.GooglerRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.request.register.LeadRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.response.LoginResponse;
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
import com.tolgaozgun.gdscturkweb.model.user.CoreTeamMember;
import com.tolgaozgun.gdscturkweb.repository.BuddyTeamRepository;
import com.tolgaozgun.gdscturkweb.repository.UniversityRepository;
import com.tolgaozgun.gdscturkweb.repository.user.*;
import com.tolgaozgun.gdscturkweb.security.JWTUserService;
import com.tolgaozgun.gdscturkweb.security.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

            final UserDetails userDetails = jwtUserService.loadUserByEmail(user.getEmail());
            final String accessToken = jwtUtils.createAccessToken(userDetails);
            final String refreshToken = jwtUtils.createRefreshToken(userDetails);
            return new LoginResponse(dbUser, accessToken, refreshToken);
        } catch (Exception e) {
            System.out.println("login exception");
            e.printStackTrace();
            throw e;
        }
    }

    private UserEntity checkAndRegisterUser(UserRegister userRegister, UserType userType) throws Exception {
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


    public LeadDTO registerLead(LeadRegisterRequest leadRegisterRequest) throws Exception {
        try {

            UserRegister userRegister = leadRegisterRequest.getUserRegister();
            LeadRegister leadRegister = leadRegisterRequest.getLeadRegister();

            UserEntity savedEntity = checkAndRegisterUser(userRegister, UserType.LEAD);

            UniversityEntity universityEntity = universityRepository.findById(leadRegister.getUniversityId())
                    .orElseThrow(() -> new Exception("University not found"));

            LeadEntity leadEntity = new LeadEntity(universityEntity, savedEntity);

            LeadEntity savedLeadEntity = leadRepository.save(leadEntity);

            return leadMapper.toDTO(savedLeadEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public CoreTeamMemberDTO registerCoreTeam(CoreTeamRegisterRequest coreTeamRegisterRequest) throws Exception {
        try {

            UserRegister userRegister = coreTeamRegisterRequest.getUserRegister();
            CoreTeamRegister coreTeamRegister = coreTeamRegisterRequest.getCoreTeamRegister();

            UserEntity savedEntity = checkAndRegisterUser(userRegister, UserType.CORE_TEAM_MEMBER);

            UniversityEntity universityEntity = universityRepository.findById(coreTeamRegister.getUniversityId())
                    .orElseThrow(() -> new Exception("University not found"));

            CoreTeamMemberEntity coreTeamMemberEntity = new CoreTeamMemberEntity(universityEntity, savedEntity);


            CoreTeamMemberEntity savedCoreTeamMemberEntity = coreTeamMemberRepository.save(coreTeamMemberEntity);

            return coreTeamMapper.toDTO(savedCoreTeamMemberEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public GooglerDTO registerGoogler(GooglerRegisterRequest googlerRegisterRequest) throws Exception {
        try {

            UserRegister userRegister = googlerRegisterRequest.getUserRegister();
            GooglerRegister googlerRegister = googlerRegisterRequest.getGooglerRegister();

            UserEntity savedEntity = checkAndRegisterUser(userRegister, UserType.CORE_TEAM_MEMBER);

            GooglerEntity googlerEntity = new GooglerEntity();

            googlerEntity.setUser(savedEntity);

            googlerEntity = googlerRepository.save(googlerEntity);

            return googlerMapper.toDTO(googlerEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public FacilitatorDTO registerFacilitator(FacilitatorRegisterRequest facilitatorRegisterRequest) throws Exception {
        try {

            UserRegister userRegister = facilitatorRegisterRequest.getUserRegister();
            FacilitatorRegister facilitatorRegister = facilitatorRegisterRequest.getFacilitatorRegister();

            UserEntity savedEntity = checkAndRegisterUser(userRegister, UserType.FACILITATOR);


            UniversityEntity universityEntity = universityRepository.findById(facilitatorRegister.getUniversityId())
                    .orElseThrow(() -> new Exception("University not found"));

            FacilitatorEntity facilitatorEntity = new FacilitatorEntity();
            facilitatorEntity.setUniversity(universityEntity);
            facilitatorEntity.setUser(savedEntity);
            facilitatorEntity = facilitatorRepository.save(facilitatorEntity);

            BuddyTeamEntity buddyTeamEntity = new BuddyTeamEntity();
            buddyTeamEntity.setFacilitator(facilitatorEntity);
            buddyTeamEntity.setLeads(List.of());

            buddyTeamRepository.save(buddyTeamEntity);

            return facilitatorMapper.toDTO(facilitatorEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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

    // Other service methods
}