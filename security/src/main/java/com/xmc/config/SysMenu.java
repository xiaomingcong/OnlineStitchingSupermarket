package com.xmc.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Autohr xiaomingcong
 * @date 2021/1/3 11:55 下午
 * Version 1.0
 */
public class SysMenu {

    private List<SysRole> roles = new ArrayList<>();

    public String getRoles(){
        return "admin";
    }
}
