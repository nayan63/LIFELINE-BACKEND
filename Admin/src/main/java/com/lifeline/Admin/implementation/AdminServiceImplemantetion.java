package com.lifeline.Admin.implementation;

import com.lifeline.Admin.entity.Admin;
import com.lifeline.Admin.entity.LoginCred;
import com.lifeline.Admin.excphandle.CustomException;
import com.lifeline.Admin.repository.AdminRepository;
import com.lifeline.Admin.service.AdminService;
import com.lifeline.Admin.service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceImplemantetion implements AdminService {

    @Autowired
    private AdminRepository repository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtil util;
    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public Admin createAdmin(Admin admin) {
        try
        {
            String encPass = encoder.encode(admin.getPassword());
            admin.setPassword(encPass);
            Admin adm = repository.save(admin);
            return adm;
        }
        catch (CustomException e)
        {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public String generateToken(LoginCred cred) {
        Authentication authenticate = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(cred.getUsername(), cred.getPassword())
                );

        if(authenticate.isAuthenticated()) {
            return util.generateToken(cred.getUsername());
        }
        else
        {
            throw new CustomException("Invalid Username or Password");
        }
    }

    @Override
    public void validateToken(String token) {
        util.validateToken(token);
    }
}
