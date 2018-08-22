package com.kpy.discoveyserver.gateway.dao;

import com.kpy.discoveyserver.gateway.entity.KPYZullRoute;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Date: 2018/8/22 0022
 * Time:14:14
 * 路由dao
 * @author:lihf
 * @version:1.0
 */
public interface ZullRouterDao {

    @Select("SELECT * FROM KPYZuulRoute")
    List<KPYZullRoute> getAllRoute();


}
