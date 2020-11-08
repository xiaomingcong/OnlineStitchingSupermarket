package com.xmc.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

/**
 * 用户表
 * @author xiaomingcong
 */
@Getter
@Setter
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name" , nullable = false ,length = 50)
    private String userName;

    @Column
    private String password;

    @Email
    @Column(name = "email" , length = 50)
    private String email;

    @Column(name = "telephone",nullable = true , length = 20)
    private Long telephone;

    @Column(name = "valid_flag")
    private Long validFlag;

    @ManyToMany(mappedBy = "users")
    private List<Role> roles;
}
