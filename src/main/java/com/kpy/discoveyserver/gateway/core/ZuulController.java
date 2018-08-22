package com.kpy.discoveyserver.gateway.core;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ZuulServlet;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ServletWrappingController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Date: 2018/8/22 0022
 * Time:17:25
 *
 * @author:lihf
 * @version:1.0
 */
@Component
public class ZuulController extends ServletWrappingController {

    private static final Logger LOGGER = Logger.getLogger(ZuulController.class);

    public ZuulController() {
        // 设置类为ZuulServlet
        setServletClass(ZuulServlet.class);
        setServletName("zu");
        setSupportedMethods((String[]) null);
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info(request.getPathInfo());
        try {
            return super.handleRequestInternal(request, response);
        } finally {
            RequestContext.getCurrentContext().unset();
        }
    }
}
