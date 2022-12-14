package com.example.dgpayproject.services.impl;

import com.example.dgpayproject.exceptionhandlers.GlobalException;
import com.example.dgpayproject.models.entities.UserEntity;
import com.example.dgpayproject.repositories.UserRepository;
import com.example.dgpayproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            LOGGER.error("user not found in the database");
            throw new UsernameNotFoundException("user not found in the database");
        } else {
            LOGGER.info("user found in the database:{}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(
                new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(
                userEntity.getUsername(), userEntity.getPassword(), authorities);
    }

    @Transactional
    @Override
    public void saveUser(UserEntity userEntity) {
        LOGGER.info("saving new user{} to  db", userEntity.getLname());
        try {
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword().trim()));
            userRepository.save(userEntity);
        } catch (DuplicateKeyException ex) {
            LOGGER.error("username can not be duplicated",ex);
            throw new GlobalException("username.can.not.be.duplicated");
        }
    }

    @Transactional
    @Override
    public UserEntity getUser(String username) {
        LOGGER.info("fetching username from database:{}", username);
        if (username == null) throw new GlobalException("username.does.not.exist");
        return userRepository.findByUsername(username);
    }
}
