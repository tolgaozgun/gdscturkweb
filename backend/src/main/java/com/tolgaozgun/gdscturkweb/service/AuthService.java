package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.dto.request.LoginRequest;
import com.tolgaozgun.gdscturkweb.dto.request.register.CoreTeamRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.request.register.LeadRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.response.LoginResponse;
import com.tolgaozgun.gdscturkweb.dto.UserDTO;
import com.tolgaozgun.gdscturkweb.dto.user.CoreTeamRegister;
import com.tolgaozgun.gdscturkweb.dto.user.LeadRegister;
import com.tolgaozgun.gdscturkweb.dto.user.UserRegister;
import com.tolgaozgun.gdscturkweb.entity.user.CoreTeamMemberEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import com.tolgaozgun.gdscturkweb.exception.PasswordNotMatchException;
import com.tolgaozgun.gdscturkweb.exception.UserAlreadyExistsException;
import com.tolgaozgun.gdscturkweb.exception.UserNotFoundException;
import com.tolgaozgun.gdscturkweb.mapper.CoreTeamMapper;
import com.tolgaozgun.gdscturkweb.mapper.LeadMapper;
import com.tolgaozgun.gdscturkweb.mapper.UniversityMapper;
import com.tolgaozgun.gdscturkweb.mapper.UserMapper;
import com.tolgaozgun.gdscturkweb.model.user.CoreTeamMember;
import com.tolgaozgun.gdscturkweb.repository.BuddyTeamRepository;
import com.tolgaozgun.gdscturkweb.repository.UniversityRepository;
import com.tolgaozgun.gdscturkweb.repository.user.CoreTeamMemberRepository;
import com.tolgaozgun.gdscturkweb.repository.user.LeadRepository;
import com.tolgaozgun.gdscturkweb.repository.user.UserRepository;
import com.tolgaozgun.gdscturkweb.security.JWTUserService;
import com.tolgaozgun.gdscturkweb.security.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    private final CoreTeamMapper coreTeamMapper;

    // JWT

    private final JWTUserService jwtUserService;

    // Repositories

    private final BuddyTeamRepository buddyTeamRepository;
    private final UniversityRepository universityRepository;
    private final CoreTeamMemberRepository coreTeamMemberRepository;
    private final UserRepository userRepository;
    private final LeadRepository leadRepository;


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
                throw new UserNotFoundException("user is not found");
            }
            UserEntity dbUser = emailEntity.get();

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

    private UserEntity checkAndRegisterUser(UserRegister userRegister) throws Exception {
        boolean userExist = userRepository.existsByUsername(userRegister.getUsername());

        if (userExist) {
            throw new UserAlreadyExistsException("This username already exists");
        }

        boolean emailExist = userRepository.existsByEmail(userRegister.getEmail());

        if (emailExist) {
            throw new UserAlreadyExistsException("This email already exists");
        }
        userRegister.setPassword(encodePassword(userRegister.getPassword()));

        UserEntity userEntity = new UserEntity(userRegister, UserType.LEAD);

        return userRepository.save(userEntity);

    }


    public LeadDTO registerLead(LeadRegisterRequest leadRegisterRequest) throws Exception {
        try {

            UserRegister userRegister = leadRegisterRequest.getUserRegister();
            LeadRegister leadRegister = leadRegisterRequest.getLeadRegister();

            UserEntity savedEntity = checkAndRegisterUser(userRegister);

            // generate uuid and hash password if user does not exist in the system
            // user.setId(UUID.randomUUID());

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
    public CoreTeamMember registerCoreTeam(CoreTeamRegisterRequest coreTeamRegisterRequest) throws Exception {
        try {

            UserRegister userRegister = coreTeamRegisterRequest.getUserRegister();
            CoreTeamRegister coreTeamRegister = coreTeamRegisterRequest.getCoreTeamRegister();

            boolean userExist = userRepository.existsByUsername(userRegister.getUsername());

            if (userExist) {
                throw new UserAlreadyExistsException("This username already exists");
            }

            userRegister.setPassword(encodePassword(userRegister.getPassword()));

            UserEntity userEntity = new UserEntity(userRegister, UserType.CORE_TEAM_MEMBER);

            UniversityEntity universityEntity = universityRepository.findById(coreTeamRegister.getUniversityId())
                    .orElseThrow(() -> new Exception("University not found"));

            CoreTeamMemberEntity coreTeamMemberEntity = new CoreTeamMemberEntity(universityEntity, userEntity);


            CoreTeamMemberEntity savedCoreTeamMemberEntity = coreTeamMemberRepository.save(coreTeamMemberEntity);

            return coreTeamMapper.toModel(coreTeamMemberEntity);
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