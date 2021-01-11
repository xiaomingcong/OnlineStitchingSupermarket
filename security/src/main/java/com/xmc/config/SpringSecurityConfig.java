package com.xmc.config;

import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.AssertionImpl;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//@Configuration
//@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

//    private AuthenticationManager authenticationManager;
//    private UserDetailsService userDetailsService;
//
//    public SpringSecurityConfig(AuthenticationManager authenticationManager,
//        UserDetailsService userDetailsService){
//        this.authenticationManager = authenticationManager;
//        this.userDetailsService = userDetailsService;
//    }

//    @Bean
    public UserDetailsService userDetailsService() {
        // The builder will ensure the passwords are encoded before saving in memory
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        UserDetails user = users
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//        UserDetails admin = users
//                .username("admin")
//                .password("password")
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
        return new MyUserDetailsService();

    }

//    @Bean
//    public ProviderManager myProviderManager(@Autowired MyJdbcAuthenticationProvider provider){
//        return new MyProviderManager(provider);
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);


//        SecurityBuilder builder = new SecurityBuilder() {
//            @Override
//            public Object build() throws Exception {
//                return new UsernamePasswordAuthenticationFilter();
//            }
//        };
//        web.addSecurityFilterChainBuilder(builder);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                会话管理
//                .sessionManagement()
//                控制最大连接数
//                .maximumSessions(1)
//        .and()
//                固定会话攻击策略
//                .sessionFixation()
//                .newSession()
//        .and()
//                .sessionManagement()
//                //会话失效策略
//                .invalidSessionStrategy(new InvalidSessionStrategy() {
//                    @Override
//                    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//
//                    }
//                })
//        .and()
                //记住我的策略
//                .rememberMe()
//                .userDetailsService(userDetailsService())
//                .tokenValiditySeconds(300)
//        .and()
//                //匿名认证
//                .anonymous()
//                .authorities("guest")
//        .and()
                .addFilter(casAuthenticationFilter())
//        .formLogin().loginPage("/login")
        ;
//                .addFilterBefore(exceptionTranslationFilter(), FilterSecurityInterceptor.class)
//                .formLogin().disable()


        http.exceptionHandling().authenticationEntryPoint(casAuthenticationEntryPoint());
        ;


//        super.configure(http);
    }

//    @Bean("serviceProperties")
    public ServiceProperties serviceProperties(){
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService("https://localhost:443");
        serviceProperties.setSendRenew(false);
        return serviceProperties;
    }

//    @Bean
    public CasAuthenticationEntryPoint casAuthenticationEntryPoint(){
        CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
        casAuthenticationEntryPoint.setLoginUrl("http://localhost:8080/cas/login");
        casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
        return casAuthenticationEntryPoint;
    }

//    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        AuthenticationManager authenticationManager  = super.authenticationManager();
        return authenticationManager;
    }

//    @Bean("casFilter")
    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
        CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
        casAuthenticationFilter.setAuthenticationManager(authenticationManager());
//        casAuthenticationFilter.setFilterProcessesUrl("/logout");
        RequestMatcher requestMatcher = new RequestMatcher() {
            @Override
            public boolean matches(HttpServletRequest request) {
                String url = request.getServletPath();
                if(url.equals("/hello")){
                    return true;
                }
                return false;
            }
        };
        casAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
//        casAuthenticationFilter.setRequiresAuthenticationRequestMatcher(requestMatcher);
        return  casAuthenticationFilter;
    }

//    @Bean
    public CasAuthenticationProvider casAuthenticationProvider(){
        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
        UserDetailsByNameServiceWrapper userDetailsByNameServiceWrapper = new UserDetailsByNameServiceWrapper(userDetailsService());
        casAuthenticationProvider.setAuthenticationUserDetailsService(userDetailsByNameServiceWrapper);
        casAuthenticationProvider.setServiceProperties(serviceProperties());
        Cas20ServiceTicketValidator cas20ServiceTicketValidator = new Cas20ServiceTicketValidator("http://localhost:8080/cas");
//        Cas30ServiceTicketValidator Cas30ServiceTicketValidator = new Cas30ServiceTicketValidator();
        casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator);
        casAuthenticationProvider.setKey("an_id_for_this_auth_provider_only");
        return casAuthenticationProvider;
    }

//    @Bean
    public ExceptionTranslationFilter exceptionTranslationFilter(){
        ExceptionTranslationFilter exceptionTranslationFilter = new ExceptionTranslationFilter(casAuthenticationEntryPoint());
        return exceptionTranslationFilter;
    }

//    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler();
        handler.setDefaultFailureUrl("/failure");
//        AuthenticationFailureHandler handler = new CustomAuthenticationFailureHandler();
        return handler;
    }

//    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                return ;
            }
        };
    }

    private class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{

        private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            String url = request.getRequestURL().toString();
            String path = request.getServletPath();
            String uri = request.getRequestURI();
            saveException(request, exception);
            redirectStrategy.sendRedirect(request, response, "/hello");
        }
    }

    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("123456").roles("Admin")
//                .and()
//                .withUser("user").password("user123").roles("user");
//    }
    /*
    springboot spring Sercuity 默认加载delegetingFilterProxy，委托给内部组合的Bean Filter来决定选择哪个FilterChain，
    FitlerChian是一个AbstractAuthenticationProcessingFilter实现类的集合，以责任链的形式对用户请求做过滤

     */
}
