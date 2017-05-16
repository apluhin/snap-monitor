package com.repository;

import com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    User findById(Long id);

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);
}
