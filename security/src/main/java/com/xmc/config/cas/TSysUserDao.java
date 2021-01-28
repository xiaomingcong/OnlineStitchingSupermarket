package com.xmc.config.cas;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Autohr xiaomingcong
 * @date 2021/1/3 11:05 下午
 * Version 1.0
 */
@Component
@Profile("cas")
public class TSysUserDao {

    public SysUser findByUserName(String userName){
        SysUser sysUser = new SysUser(userName);
        return sysUser;
    }
}
