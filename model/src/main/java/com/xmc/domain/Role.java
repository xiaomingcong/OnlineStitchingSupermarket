package com.xmc.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 角色表
 * @author xiaomingcong
 */
@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    /**
     * 一个角色可对应多个权限
     */
    @ManyToMany(mappedBy = "roles")
    private List<Permission> permissions;

    @ManyToMany
    @JoinTable(name = "role_user" ,joinColumns = @JoinColumn(name = "role_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

}
