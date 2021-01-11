package com.xmc.config;

import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Autohr xiaomingcong
 * @date 2021/1/3 11:05 下午
 * Version 1.0
 */
@Component
public class TSysUserDao {

    public SysUser findByUserName(String userName){
        SysUser sysUser = new SysUser(userName);
        return sysUser;
    }
}
