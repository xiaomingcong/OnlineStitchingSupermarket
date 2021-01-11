package com.xmc.config;

import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Description: TODO
 * @Autohr xiaomingcong
 * @date 2020/12/18 12:00 上午
 * Version 1.0
 */
//@Configuration
public class CasConfig {

//    @Bean("serviceProperties")
//    public ServiceProperties serviceProperties(){
//        ServiceProperties serviceProperties = new ServiceProperties();
//        serviceProperties.setService("http://localhost:8080/cas");
//        serviceProperties.setSendRenew(false);
//        return serviceProperties;
//    }
//
//    @Bean
//    public CasAuthenticationEntryPoint casAuthenticationEntryPoint(@Autowired ServiceProperties serviceProperties){
//        CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
//        casAuthenticationEntryPoint.setLoginUrl("http://localhist:8080/cas/login");
//        casAuthenticationEntryPoint.setServiceProperties(serviceProperties);
//        return casAuthenticationEntryPoint;
//    }
//
//    @Bean("casFilter")
//    public CasAuthenticationFilter casAuthenticationFilter(@Autowired AuthenticationManager authenticationManager){
//        CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
//        casAuthenticationFilter.setAuthenticationManager(authenticationManager);
//        return  casAuthenticationFilter;
//    }
//
//    @Bean
//    public CasAuthenticationProvider casAuthenticationProvider(@Autowired UserDetailsService userService
//            , @Autowired ServiceProperties serviceProperties){
//        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
//        UserDetailsByNameServiceWrapper userDetailsByNameServiceWrapper = new UserDetailsByNameServiceWrapper(userService);
//        casAuthenticationProvider.setAuthenticationUserDetailsService(userDetailsByNameServiceWrapper);
//        casAuthenticationProvider.setServiceProperties(serviceProperties);
//        Cas20ServiceTicketValidator cas20ServiceTicketValidator = new Cas20ServiceTicketValidator("http://localhist:8080/cas");
////        Cas30ServiceTicketValidator Cas30ServiceTicketValidator = new Cas30ServiceTicketValidator();
//        casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator);
//        casAuthenticationProvider.setKey("an_id_for_this_auth_provider_only");
//        return casAuthenticationProvider;
//    }




}
