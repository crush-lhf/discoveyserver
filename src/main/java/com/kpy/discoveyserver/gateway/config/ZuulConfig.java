package com.kpy.discoveyserver.gateway.config;

import com.kpy.discoveyserver.gateway.core.RouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class ZuulConfig {

    @Autowired
    private ZuulProperties zuulProperties;
    @Autowired
    private ServerProperties server;

    @Bean
    public RouteLocator routeLocator() {
        RouteLocator routeLocator = new RouteLocator(this.server.getServletPrefix(), this.zuulProperties);
        return routeLocator;
    }

}
