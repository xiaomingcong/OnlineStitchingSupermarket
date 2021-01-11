package com.xmc.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.stereotype.Component;

import java.security.Provider;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description: TODO
 * @Autohr xiaomingcong
 * @date 2020/12/13 3:52 下午
 * Version 1.0
 */
//@Component
public class MyProviderManager extends ProviderManager {

    private List<AuthenticationProvider> providers = Collections.emptyList();
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    private AuthenticationManager parent;

    MyProviderManager(List<AuthenticationProvider> providers){
        super(providers);
        this.providers = providers;
        this.parent = null;

    }

    MyProviderManager(AuthenticationProvider provider){
        super(provider);
        this.providers = Arrays.asList(provider);
        this.parent = null;

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("xxxxxxhello");
        return super.authenticate(authentication);
    }
}
