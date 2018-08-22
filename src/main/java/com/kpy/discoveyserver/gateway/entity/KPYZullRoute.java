package com.kpy.discoveyserver.gateway.entity;

import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

import java.util.Objects;

/**
 * Date: 2018/8/22 0022
 * Time:14:04
 * 路由规则表
 * @author:lihf
 * @version:1.0
 */
public class KPYZullRoute extends ZuulProperties.ZuulRoute {


    private String action;

    private String version;


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()){ return false;}
        KPYZullRoute that = (KPYZullRoute) o;
        return
                Objects.equals(action, that.action) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {

        return Objects.hash( action, version);
    }
}
