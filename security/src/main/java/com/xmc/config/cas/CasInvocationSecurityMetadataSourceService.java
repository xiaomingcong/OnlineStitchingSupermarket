package com.xmc.config.cas;

import com.xmc.config.FilterStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: TODO
 * @Autohr xiaomingcong
 * @date 2021/1/3 11:20 下午
 * Version 1.0
 */
@Component
@Profile("cas")
public class CasInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    private final TSysMenuDao tSysMenuDao;
    private final HashSet<Pattern> patterns;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CasInvocationSecurityMetadataSourceService(TSysMenuDao tSysMenuDao, FilterStatic filterStatic) {
        this.tSysMenuDao = tSysMenuDao;
        patterns = new HashSet<>();
        //可通过配置过滤路径，这里就省略不写了，写法与AcmCasProperties一致
        for (String filter:filterStatic.getStaticFilters()){
            String regex= filter.replace("**","*").replace("*",".*");
            patterns.add(Pattern.compile(regex));
        }
    }

//    @Autowired
//    public CasInvocationSecurityMetadataSourceService(TSysMenuDao tSysMenuDao) {
//        this.tSysMenuDao = tSysMenuDao;
//        patterns = new HashSet<>();
//        //可通过配置过滤路径，这里就省略不写了，写法与AcmCasProperties一致
//        patterns.add(Pattern.compile("\\*"));
//    }



    /**
     * 查找url对应的角色
     */
    public Collection<ConfigAttribute> loadResourceDefine(String url){
        Collection<ConfigAttribute> array=new ArrayList<>();
        ConfigAttribute cfg;
        SysMenu permission = tSysMenuDao.findMeneRoles(url);
        if (permission !=null) {
            for (String role :permission.getRoles().split(",")){
                cfg = new SecurityConfig(role);
                //此处只添加了用户的名字，其实还可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。此处添加的信息将会作为CasAccessDecisionManager类的decide的第三个参数。
                array.add(cfg);
            }
            return array;
        }
        return null;

    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        String url = request.getRequestURI();
        url = url.replaceFirst(request.getContextPath(), "");
        logger.info(url);

        //将请求的url与配置文件中不需要访问控制的url进行匹配
        Iterator<Pattern> patternIterator=patterns.iterator();
        while (patternIterator.hasNext()){
            Pattern pattern = patternIterator.next();
            Matcher matcher=pattern.matcher(url);
            if (matcher.find())
                return null;
        }
        return loadResourceDefine(url);
    }


    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
