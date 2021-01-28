package com.xmc.config.cas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Autohr xiaomingcong
 * @date 2021/1/3 10:50 下午
 * Version 1.0
 */
@Component
@Profile("cas")
public class AcmCasUserDetailService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

    private static final Logger USER_SERVICE_LOGGER = LoggerFactory.getLogger(AcmCasUserDetailService.class);

    @Resource
    private TSysUserDao tsysUserDAO;

    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
        USER_SERVICE_LOGGER.info("校验成功的登录名为: " + token.getName());
        //此处涉及到数据库操作然后读取权限集合，读者可自行实现
        SysUser sysUser = tsysUserDAO.findByUserName(token.getName());
        if (null == sysUser) {
            throw new UsernameNotFoundException("username isn't exsited in log-cms");
        }
        return sysUser;
    }
}
