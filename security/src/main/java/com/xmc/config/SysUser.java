package com.xmc.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description: TODO
 * @Autohr xiaomingcong
 * @date 2021/1/3 10:51 下午
 * Version 1.0
 */
public class SysUser implements UserDetails {

    private String username;

    public SysUser(String username){
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        //获取用户对应的角色集合
        List<SysRole> roles = this.getSysRoles();
        for (SysRole role : roles) {
            //手动加上ROLE_前缀
            auths.add(new SimpleGrantedAuthority(SercurityConstants.prefix+role.getRoleName()));
        }
        return auths;
    }

    private List<SysRole> getSysRoles() {
        List<SysRole> roles = new ArrayList<>();
        SysRole role = new SysRole();
        roles.add(role);
        return roles;

    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
