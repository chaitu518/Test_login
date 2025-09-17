package com.example.test_login.respository;

import com.example.test_login.models.User;
import com.example.test_login.respository.altDatabases.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersRepoImp implements UserRepo {
    private UserRepository userRepository;
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public boolean userExists(String email) {
        return userRepository.userExists(email);
    }

    @Override
    public User getUser(String email) {
        return userRepository.getUser(email);
    }

    @Override
    public User addUser(User user) {
        return userRepository.addUser(user);
    }
}
