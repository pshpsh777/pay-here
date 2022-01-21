package com.payhere.apiserver.controller;

import com.payhere.apiserver.repository.UserRepository;
import com.payhere.apiserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.payhere.apiserver.domain.User;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/api/join", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public User joinUser(@RequestBody User user){
        userService.join(user);
        return user;
    }

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public User loginUser(@RequestBody User user){
        userService.join(user);
        return user;
    }
    @RequestMapping(value = "/api/logout", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String logoutUser(){
        return "";
    }
}