package com.lifeline.User.service;

import com.lifeline.User.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User signUp(User user);
}
