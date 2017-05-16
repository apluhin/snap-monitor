package com.login;

import com.entity.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class LoginService {

    @Autowired
    UserRepository repository;


    public void saveUser(User user) {
        User byLogin = repository.findByLogin(user.getLogin());
        if (byLogin != null) {
            throw new RuntimeException("Register yet");
        }
        repository.save(user);
    }


    public User findById(Long id) {
        return repository.findById(id);
    }


    public User findByLogin(String login) {
        return repository.findByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User byLoginAndPassword = repository.findByLoginAndPassword(login, password);
        if (byLoginAndPassword == null) {
            throw new RuntimeException("Not found");
        }
        return byLoginAndPassword;
    }

    @PostConstruct
    public void save() {
        User user = new User("admin", "admin");
        saveUser(user);
    }

}
