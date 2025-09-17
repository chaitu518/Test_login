package com.example.test_login.respository;

import com.example.test_login.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepo {
    public boolean userExists(String email);
    public User getUser(String email);
    public User addUser(User user);
}
