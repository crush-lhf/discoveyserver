package com.kpy.discoveyserver;

import com.kpy.discoveyserver.gateway.core.ZuulController;
import com.kpy.discoveyserver.gateway.filter.RouteFiler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableZuulProxy
@ComponentScan(basePackages = {"com.kpy.discoveyserver.gateway"})
@MapperScan(basePackages = {"com.kpy.discoveyserver.gateway"})
@ServletComponentScan(basePackages = {"com.kpy.discoveyserver.gateway"})
public class DiscoveyserverApplication {


    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/allService")
    public Object allService() {
        return discoveryClient.getServices();
    }

    @Bean
    public RouteFiler routeFiler() {
        return new RouteFiler();
    }


    public static void main(String[] args) {
        SpringApplication.run(DiscoveyserverApplication.class, args);
    }
}
