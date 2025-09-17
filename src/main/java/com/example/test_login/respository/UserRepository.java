package com.example.test_login.respository;

import com.example.test_login.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserRepository {
    int id=1;
    List<User> users= new ArrayList<>();
    public boolean userExists(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    public User getUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
    public User addUser(User user) {
        user.setId(id);
        id++;
        users.add(user);
        return user;
    }
}
