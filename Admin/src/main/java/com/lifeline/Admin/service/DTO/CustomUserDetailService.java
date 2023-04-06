package com.lifeline.Admin.service.DTO;

import com.lifeline.Admin.entity.Admin;
import com.lifeline.Admin.excphandle.CustomException;
import com.lifeline.Admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private AdminRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> credential = repository.findByEmail(username);
        try
        {
            if(!credential.isEmpty())
            {
                Admin admin = credential.get();
                UserDetails user = User.withUsername(username).password(admin.getPassword()).authorities("ADMIN").build();
                return user;
            }
            else
            {
                throw new CustomException("Invalid User details");
            }
        }
        catch (Exception e)
        {
            throw new CustomException(e.getMessage());
        }
    }
}
