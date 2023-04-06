package com.lifeline.User.controller;

import com.lifeline.User.entity.User;
import com.lifeline.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(@RequestBody() User user)
    {
        User user1 = service.signUp(user);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }
    @GetMapping("/hii")
    public ResponseEntity<String> hii()
    {
        return new ResponseEntity<>("Hii", HttpStatus.OK);
    }
}
