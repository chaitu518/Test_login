package com.example.test_login.service;

import com.example.test_login.dto.UserDTO;
import com.example.test_login.models.User;
import com.example.test_login.respository.UserRepo;
import com.example.test_login.respository.altDatabases.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class UserService {
    private UserRepo userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService  jwt;
    public UserService(@Qualifier("userRepoImp") UserRepo userRepository, PasswordEncoder passwordEncoder, JwtService jwt) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwt = jwt;
    }
    public String login(String email, String password) {
        if(userRepository.userExists(email)) {
            User user = userRepository.getUser(email);
            if(passwordEncoder.matches(password,user.getPassword())) {
                return jwt.generate(Map.of("id", user.getId(), "username", user.getUsername()), user.getEmail());
            }

        }
        throw new RuntimeException("Invalid credentials");
    }
    public UserDTO register(String username, String email, String password) {
        if(userRepository.userExists(email)) {
            User user = userRepository.getUser(email);
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            return  userDTO;
        }
        User  user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        User registeredUser = userRepository.addUser(user);
        UserDTO userDTO = new UserDTO();
        if(registeredUser != null) {
            userDTO.setId(registeredUser.getId());
            userDTO.setUsername(registeredUser.getUsername());
            userDTO.setEmail(registeredUser.getEmail());
        }
        return userDTO;
    }
}
