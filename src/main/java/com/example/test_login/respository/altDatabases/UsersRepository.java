package com.example.test_login.respository.altDatabases;

import com.example.test_login.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersRepository extends JpaRepository<User,Integer> {
    public User findByEmail(String email);
    public List<User> findAllByEmail(String email);
    public User save(User user);
}
