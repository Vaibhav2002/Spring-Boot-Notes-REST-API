package com.vaibhav.notes.service;

import com.vaibhav.notes.exceptions.AuthenticationException;
import com.vaibhav.notes.models.User;
import com.vaibhav.notes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    UserRepository userRepository;

    @Autowired
    public UserService(@Qualifier("userRepo") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loginUser(String email, String password) throws AuthenticationException {
        Optional<User> opt = userRepository.findByEmail(email);
        if (!opt.isPresent()) {
            throw new AuthenticationException("User does not exits");
        }
        if (!opt.get().getPassword().equals(password))
            throw new AuthenticationException("Incorrect password");
        return opt.get();
    }

    public User RegisterUser(User user) throws AuthenticationException {
        if (userRepository.exists(Example.of(user))) {
            throw new AuthenticationException("user already exits");
        }
        return userRepository.save(user);
    }
}
