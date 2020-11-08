package com.xmc.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 权限表
 *
 * @author xiaomingcong
 */
@Entity
@Table
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToMany
    @JoinTable(name = "permission_role" ,joinColumns = @JoinColumn(name="permission_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;



}
