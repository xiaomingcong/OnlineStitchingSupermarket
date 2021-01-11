package com.xmc.config;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Autohr xiaomingcong
 * @date 2021/1/4 12:15 上午
 * Version 1.0
 */
@Component
public class FilterStatic {

    public List<String> getStaticFilters(){
        List<String> paths = new ArrayList<>();
        paths.add("**.image");
        return paths;
    }
}
