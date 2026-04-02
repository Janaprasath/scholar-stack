package com.janaprasath.scholarstack.service;

import com.janaprasath.scholarstack.dto.LoginDTO;
import com.janaprasath.scholarstack.dto.UserRegistrationDTO;
import com.janaprasath.scholarstack.entity.User;
import com.janaprasath.scholarstack.repository.UserRepository;
import com.janaprasath.scholarstack.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public String registerUser(UserRegistrationDTO registrationDto) {
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            return "Error: Email is already in use!";
        }
        User newUser = new User();
        newUser.setUsername(registrationDto.getUsername());
        newUser.setEmail(registrationDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        newUser.setRole("ROLE_STUDENT");
        userRepository.save(newUser);
        return "User registered successfully!";
    }


    public String loginUser(LoginDTO loginDto) {
        Optional<User> userOptional = userRepository.findByEmail(loginDto.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                return jwtUtils.generateToken(user.getEmail());
            }
            return "Error: Invalid Password";
        }
        return "Error: Email not found";
    }
}