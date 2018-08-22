package com.kpy.discoveyserver.gateway.web;

import com.kpy.discoveyserver.gateway.core.RefreshRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Date: 2018/8/22 0022
 * Time:16:01
 *
 * @author:lihf
 * @version:1.0
 */
@RestController
public class RouterController {
    @Autowired
   private  RefreshRouteService refreshRouteService;

    @RequestMapping("/refreshRoute")
    public String refreshRoute() {
        refreshRouteService.refreshRoute();
        return "refreshRoute";
    }
}
