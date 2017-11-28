package com.fang.cloud.filter;

import com.fang.cloud.conf.MyProps;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by quwb on 2017/11/28.
 */
public class AccessFilter extends ZuulFilter {
    @Autowired
    private MyProps myProps;

    @Override
    public String filterType() {
        return "pre";
    }
    @Override
    public int filterOrder() {
        return 0;
    }
    @Override
    public boolean shouldFilter() {
        return true;
    }
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object accessToken = request.getParameter("accessToken");
        if(accessToken != myProps.getAccessToken()) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return myProps.getAccessToken();
        }
        return myProps.getAccessToken();
    }
}
