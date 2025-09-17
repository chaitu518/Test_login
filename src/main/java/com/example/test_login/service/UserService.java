package com.example.test_login.service;

import com.example.test_login.dto.UserDTO;
import com.example.test_login.models.User;
import com.example.test_login.respository.UserRepo;
import com.example.test_login.respository.altDatabases.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private UserRepo userRepository;
    public UserService(@Qualifier("usersRepoImp") UserRepo userRepository) {
        this.userRepository = userRepository;
    }
    public String login(String email, String password) {
        if(userRepository.userExists(email)) {
            User user = userRepository.getUser(email);
            if(user.getPassword().equals(password)) {
                return "success";
            }

        }
        return "fail";
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
        user.setPassword(password);
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
