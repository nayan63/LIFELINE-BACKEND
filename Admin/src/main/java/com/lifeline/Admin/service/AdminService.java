package com.lifeline.Admin.service;

import com.lifeline.Admin.entity.Admin;
import com.lifeline.Admin.entity.LoginCred;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    Admin createAdmin(Admin admin);

    String generateToken(LoginCred cred);

    void validateToken(String token);
}
