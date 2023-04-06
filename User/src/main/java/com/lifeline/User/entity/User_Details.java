package com.lifeline.User.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User_Details {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String village;
    private String po;
    private String ps;
    private String dist;
    private String state;
    private String nationality;
    private String profile_photo;
    private String last_login_ip;
    private String last_login_time;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
    @OneToOne(mappedBy = "details")
    private User user;


}
