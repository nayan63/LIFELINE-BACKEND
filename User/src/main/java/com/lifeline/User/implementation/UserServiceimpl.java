package com.lifeline.User.implementation;

import com.lifeline.User.custom_excp.CustomException;
import com.lifeline.User.entity.User;
import com.lifeline.User.repository.UserRepository;
import com.lifeline.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceimpl implements UserService {
    @Autowired
    private UserRepository repository;
    @Override
    public User signUp(User user) throws CustomException {
        try
        {
            User user1 = repository.save(user);
            return user1;
        }
        catch (CustomException e)
        {
            throw new CustomException(e.getMessage());
        }
    }
}
