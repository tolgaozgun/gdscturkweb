package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.dto.LoginDTO;
import com.tolgaozgun.gdscturkweb.dto.LoginTokenDTO;
import com.tolgaozgun.gdscturkweb.dto.UserDTO;
import com.tolgaozgun.gdscturkweb.entity.UserEntity;
import com.tolgaozgun.gdscturkweb.exception.PasswordNotMatchException;
import com.tolgaozgun.gdscturkweb.exception.UserNotFoundException;
import com.tolgaozgun.gdscturkweb.repository.UserRepository;
import com.tolgaozgun.gdscturkweb.security.JWTUserService;
import com.tolgaozgun.gdscturkweb.security.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    public static int hashStrength = 10;

    @Autowired
    final BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private final JWTUserService jwtUserService;

    @Autowired
    private final JWTUtils jwtUtils;

    public UserDTO getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return modelMapper.map(userEntity, UserDTO.class);
    }


    public LoginTokenDTO login(LoginDTO user) throws Exception {
        try {
            Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(user.getUsername());

            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("user is not found");
            }

            UserEntity dbUser = optionalUserEntity.get();

            String hashedPassword = dbUser.getPassword();
            boolean passwordMatch = bCryptPasswordEncoder.matches(user.getPassword(), hashedPassword); /// change

            if (!passwordMatch) {
                throw new PasswordNotMatchException("passwords do not match");
            }

            System.out.println("passwords are matched");

            final UserDetails userDetails = jwtUserService.loadUserByUsername(user.getUsername());
            final String accessToken = jwtUtils.createAccessToken(userDetails);
            final String refreshToken = jwtUtils.createRefreshToken(userDetails);
            return new LoginTokenDTO(dbUser, accessToken, refreshToken);
        } catch (Exception e) {
            System.out.println("login exception");
            e.printStackTrace();
            throw e;
        }
    }

    // Other service methods
}