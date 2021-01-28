package com.xmc.config.cas;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Autohr xiaomingcong
 * @date 2021/1/4 12:01 上午
 * Version 1.0
 */
@Component
@Profile("cas")
public class TSysMenuDao {

    public SysMenu findMeneRoles(String url){
        SysMenu sysMenu = new SysMenu();

        return sysMenu;
    }
}
