package com.xmc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Autohr xiaomingcong
 * @date 2020/12/13 11:40 上午
 * Version 1.0
 */
//@Component
public class MyJdbcAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String name = (String)authentication.getPrincipal();
        Object details = authentication.getDetails();
        Object credentials = authentication.getCredentials();
        return myUserDetailsService.loadUserByUsername(username);
    }
}
