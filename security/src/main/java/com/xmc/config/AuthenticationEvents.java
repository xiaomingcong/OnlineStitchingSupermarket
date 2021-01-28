package com.xmc.config;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Autohr xiaomingcong
 * @date 2021/1/19 9:36 下午
 * Version 1.0
 */
@Component
public class AuthenticationEvents {
    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        System.out.println("authenticated success!");
        // ...
    }

    @EventListener
    public  void onFailure(AbstractAuthenticationFailureEvent failures) {
        System.out.println("authenticated failure!");
        // ...
    }
}
