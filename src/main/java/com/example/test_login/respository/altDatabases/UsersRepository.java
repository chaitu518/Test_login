package com.example.test_login.respository.altDatabases;

import com.example.test_login.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User,Integer> {
    public Optional<User> findByEmail(String email);
    public List<User> findAllByEmail(String email);
    public User save(User user);
}
