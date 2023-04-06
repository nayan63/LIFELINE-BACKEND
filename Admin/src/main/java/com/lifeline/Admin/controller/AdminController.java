package com.lifeline.Admin.controller;

import com.lifeline.Admin.entity.Admin;
import com.lifeline.Admin.entity.JwtResponse;
import com.lifeline.Admin.entity.LoginCred;
import com.lifeline.Admin.excphandle.CustomException;
import com.lifeline.Admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService service;
    @PostMapping("/sign-up")
    public ResponseEntity<Admin> signUp(@RequestBody() Admin admin)
    {
        Admin adm = service.createAdmin(admin);
        return new ResponseEntity<>(adm, HttpStatus.OK);
    }

    @PostMapping("/generate-token")
    public ResponseEntity<JwtResponse> gererateToken(@RequestBody LoginCred cred)
    {
        try
        {
            String token = service.generateToken(cred);
            String role = "ADMIN";
            JwtResponse jr = new JwtResponse(token,role);
            return new ResponseEntity<>(jr,HttpStatus.OK);
        }
        catch(Exception e)
        {
            throw new CustomException(e.getMessage());
        }
    }

    @GetMapping("/validate-token")
    public Boolean validate(@RequestParam String token)
    {
        try
        {
            service.validateToken(token);
            return true;
        }
        catch(Exception e)
        {
            throw new CustomException(e.getMessage());
        }
    }
}
