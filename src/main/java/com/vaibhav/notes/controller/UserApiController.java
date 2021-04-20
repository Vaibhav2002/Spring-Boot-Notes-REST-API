package com.vaibhav.notes.controller;

import com.vaibhav.notes.models.User;
import com.vaibhav.notes.service.UserService;
import com.vaibhav.notes.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UserApiController {


    UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public User loginUser(@RequestHeader(name = "api_key") String apiKey,@RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            if (!apiKey.equals(Constants.api_key))
                throw Constants.authenticationException;
            return userService.loginUser(email, password);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), exception);
        }
    }

    @PostMapping("/register")
    public User registerUser(@RequestHeader(name = "api_key") String apiKey, @RequestBody User user) {
        try {
            if (!apiKey.equals(Constants.api_key))
                throw Constants.authenticationException;
            return userService.RegisterUser(user);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), exception);
        }
    }

}
