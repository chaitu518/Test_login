package com.example.test_login.respository;


import com.example.test_login.models.User;
import com.example.test_login.respository.altDatabases.UserRepository;
import com.example.test_login.respository.altDatabases.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepoImp implements UserRepo {

    private UsersRepository usersRepository;
    @Autowired
    public void setUserRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    @Override
    public boolean userExists(String email) {
        return (usersRepository.findByEmail(email) != null);
    }

    @Override
    public User getUser(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public User addUser(User user) {
        return usersRepository.save(user);
    }
}
