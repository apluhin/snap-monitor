package com.controller;

import com.entity.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserRepository service;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginNewUser(String login, String password, HttpSession session) {
        User user = service.findByLoginAndPassword(login, password);
        session.setAttribute("user", user);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }


}