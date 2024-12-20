package com.example.UserService.Service.Implementations;

import com.example.UserService.DTO.UserDTO;
import com.example.UserService.Model.User;
import com.example.UserService.Repository.UserRepository;
import com.example.UserService.Service.Interfaces.UserServiceInterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserDTO userDTO) {

        if (userRepository.existsByEmail(userDTO.email())) {
            throw new IllegalArgumentException("This email is already in use");
        }
        User user = new User();
        user.setEmail(userDTO.email());
        user.setName(userDTO.name());
        user.setPassword(passwordEncoder.encode(userDTO.password()));

        userRepository.save(user);
    }
}
