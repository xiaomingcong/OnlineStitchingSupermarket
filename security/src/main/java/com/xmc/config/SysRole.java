package com.xmc.config;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Description: TODO
 * @Autohr xiaomingcong
 * @date 2021/1/3 11:58 下午
 * Version 1.0
 */
public class SysRole implements GrantedAuthority {

        @Override
        public String getAuthority() {
            return "admin";
        }

        public String getRoleName(){
            return this.getAuthority().toString();
        }
}
