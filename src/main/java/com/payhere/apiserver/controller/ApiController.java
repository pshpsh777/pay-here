package com.payhere.apiserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.payhere.apiserver.model.User;


@RestController
public class ApiController {
    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String getUsers() {
        return "{\"result\":\"get users\"}";
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String addUser(@RequestBody User user){
        return "{\"result\":\"add user "+user.toString()+"\"}";
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public String updateUser(@RequestBody User user, @PathVariable int id){
        return "{\"result\":\"update user ("+id+") "+user.toString()+"\"}";
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public String updateUser(@PathVariable int id){
        return "{\"result\":\"delete user "+id+"\"}";
    }
}
