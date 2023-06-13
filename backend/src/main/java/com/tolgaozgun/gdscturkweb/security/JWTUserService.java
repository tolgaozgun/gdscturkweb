package com.tolgaozgun.gdscturkweb.security;

import java.util.Collections;
import java.util.Optional;

import com.tolgaozgun.gdscturkweb.entity.UserEntity;
import com.tolgaozgun.gdscturkweb.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UserDetailsService;


// import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JWTUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    // @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<UserEntity> optionalUser = userRepository.findByUsername(username); // ! here username is mail

            if (optionalUser.isEmpty()) {
                throw new UsernameNotFoundException("User not found");
            }

            UserEntity appUser = optionalUser.get();


            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                                        username, 
                                        appUser.getPassword(),
                                        Collections.singleton(new SimpleGrantedAuthority(appUser.getUserType().toString()))
                                        // null
                                        // Collections.singleton(new SimpleGrantedAuthority(appUser.getUserType().toString()))
                                        );
            return userDetails; 
        } catch (Exception e) {
            throw e;
        }
    }

    // @Override
    public UserDetails loadUserByUsername(UserEntity appUser) throws UsernameNotFoundException {
        try {
        
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                                        appUser.getUsername(),
                                        appUser.getPassword()
                                        // Collections.singleton(new SimpleGrantedAuthority(appUser.getUserType().toString()))
                                        , null
                                        );
            return userDetails; 
        } catch (Exception e) {
            throw e;
        }
    }    
}
