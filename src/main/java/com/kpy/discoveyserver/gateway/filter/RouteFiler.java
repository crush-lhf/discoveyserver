package com.kpy.discoveyserver.gateway.filter;

import com.kpy.discoveyserver.gateway.config.RouteException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;

/**
 * Date: 2018/8/22 0022
 * Time:15:10
 * 路由过滤
 *
 * @author:lihf
 * @version:1.0
 */
@Component
public class RouteFiler extends ZuulFilter {
    /**
     * to classify a filter by type. Standard types in Zuul are "pre" for pre-routing filtering,
     * "route" for routing to an origin, "post" for post-routing filters, "error" for error handling.
     * We also support a "static" type for static responses see  StaticResponseFilter.
     * Any filterType made be created or added and run by calling FilterProcessor.runFilters(type)
     *
     * @return A String representing that type
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * filterOrder() must also be defined for a filter. Filters may have the same  filterOrder if precedence is not
     * important for a filter. filterOrders do not need to be sequential.
     *
     * @return the int order of a filter
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * a "true" return from this method means that the run() method should be invoked
     *
     * @return true if the run() method should be invoked. false will not invoke the run() method
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * if shouldFilter() is true, this method will be invoked. this method is the core method of a ZuulFilter
     *
     * @return Some arbitrary artifact may be returned. Current implementation ignores it.
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ZuulProperties.ZuulRoute zuulRoute = macth(ctx);
        if (zuulRoute == null) {
            throw new RouteException("没有该路劲");
        }
        ctx.set("serviceId", zuulRoute.getServiceId());
        ctx.set("requestURI", getUrl(ctx, zuulRoute.getUrl()));
        return ctx;
    }

    private Object getUrl(RequestContext ctx, String url) {
        return null;
    }

    private ZuulProperties.ZuulRoute macth(RequestContext ctx) {
        String action = (String) ctx.get("action");
        String version = (String) ctx.get("version");
        if (StringUtils.isEmpty(action) || StringUtils.isEmpty(version)) {
            throw new RouteException("参数不对");
        }
        return new ZuulProperties.ZuulRoute();
    }
}
