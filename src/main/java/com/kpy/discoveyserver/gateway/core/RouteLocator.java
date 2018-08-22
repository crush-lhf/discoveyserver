package com.kpy.discoveyserver.gateway.core;

import com.kpy.discoveyserver.gateway.dao.ZullRouterDao;
import com.kpy.discoveyserver.gateway.entity.KPYZullRoute;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 2018/8/22 0022
 * Time:14:10
 * 路由加载器
 *
 * @author:lihf
 * @version:1.0
 */
public class RouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    private static final Logger LOGGER = Logger.getLogger(RouteLocator.class);

    @Autowired
    private ZullRouterDao zullRouterDao;
    private ZuulProperties properties;

    public RouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties;
        LOGGER.info("servletPath---" + servletPath);
    }

    @Override
    public void refresh() {
        doRefresh();
    }

    /**
     * 加载路由规则
     *
     * @return
     */
    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();
        //从application.properties中加载路由信息
        routesMap.putAll(locateRoutesFromDB());
        LinkedHashMap<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>();
        routesMap.forEach((k, v) -> {
            if (!k.startsWith("/")) {
                k = "/" + k;
            }
            if (StringUtils.hasText(this.properties.getPrefix())) {
                k = this.properties.getPrefix() + k;
                if (!k.startsWith("/")) {
                    k = "/" + k;
                }
            }
            values.put(k, v);
        });
        return values;


    }

    private Map<String, ZuulProperties.ZuulRoute> locateRoutesFromDB() {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        Map<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();
        //todo 可优化
        List<KPYZullRoute> kpyZullRoutes = zullRouterDao.getAllRoute();
        if (CollectionUtils.isEmpty(kpyZullRoutes)) {
            return routes;
        }
        kpyZullRoutes.forEach(router -> {
            String key = base64Encoder.encodeBuffer((router.getAction() + router.getVersion()).getBytes());
            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            zuulRoute.setRetryable(true);
            zuulRoute.setUrl(router.getUrl());
            zuulRoute.setId(router.getId());
            zuulRoute.setLocation(router.getLocation());
            zuulRoute.setServiceId(router.getServiceId());
            zuulRoute.setStripPrefix(true);
            zuulRoute.setPath(router.getPath());
            routes.put(key, zuulRoute);
        });
        return routes;
    }

    public static void main(String[] args) {
        System.out.println(""+new BASE64Encoder().encodeBuffer("sdfas".getBytes()));
    }

}
