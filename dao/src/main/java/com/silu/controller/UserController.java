package com.silu.controller;

import com.silu.entity.User;
import com.silu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        User findOne = this.userRepository.findOne(id);
        return findOne;
    }

    @RequestMapping("/getAllUsers")
    public List<User> getAllUsers() {
        List<User> users = null;
        users = this.userRepository.findAll();
        return users;
    }
}
