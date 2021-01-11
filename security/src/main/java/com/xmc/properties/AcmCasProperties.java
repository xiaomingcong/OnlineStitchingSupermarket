package com.xmc.properties;

import com.xmc.utils.LocalIpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Autohr xiaomingcong
 * @date 2021/1/3 10:37 下午
 * Version 1.0
 */
@Component
public class AcmCasProperties {
    @Value("${cas.server.host.url}")
    private String casServerPrefix;

    @Value("${cas.server.host.login_url}")
    private String casServerLoginUrl;

    @Value("${cas.server.host.logout_url}")
    private String casServerLogoutUrl;

    @Value("${app.server.host.url}")
    private String appServicePrefix;

    @Value("${app.login.url}")
    private String appServiceLoginUrl;

    @Value("${app.logout.url}")
    private String appServiceLogoutUrl;

    public String getCasServerPrefix() {
//        return LocalIpUtil.replaceTrueIpIfLocalhost(casServerPrefix);
        return casServerPrefix;
    }

    public void setCasServerPrefix(String casServerPrefix) {
        this.casServerPrefix = casServerPrefix;
    }

    public String getCasServerLoginUrl() {
        return casServerLoginUrl;
    }

    public void setCasServerLoginUrl(String casServerLoginUrl) {
        this.casServerLoginUrl = casServerLoginUrl;
    }

    public String getCasServerLogoutUrl() {
        return casServerLogoutUrl;
    }

    public void setCasServerLogoutUrl(String casServerLogoutUrl) {
        this.casServerLogoutUrl = casServerLogoutUrl;
    }

    public String getAppServicePrefix() {
        return appServicePrefix;
//        return LocalIpUtil.replaceTrueIpIfLocalhost(appServicePrefix);
    }

    public void setAppServicePrefix(String appServicePrefix) {
        this.appServicePrefix = appServicePrefix;
    }

    public String getAppServiceLoginUrl() {
        return appServiceLoginUrl;
    }

    public void setAppServiceLoginUrl(String appServiceLoginUrl) {
        this.appServiceLoginUrl = appServiceLoginUrl;
    }

    public String getAppServiceLogoutUrl() {
        return appServiceLogoutUrl;
    }

    public void setAppServiceLogoutUrl(String appServiceLogoutUrl) {
        this.appServiceLogoutUrl = appServiceLogoutUrl;
    }
}
