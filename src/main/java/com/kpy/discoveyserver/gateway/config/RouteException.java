package com.kpy.discoveyserver.gateway.config;

/**
 * Created by grw on 2018/7/19.
 */
public class RouteException extends RuntimeException {
    public RouteException(String message) {
        super(message);
    }

    public RouteException(String message, Throwable cause) {
        super(message, cause);
    }
}
