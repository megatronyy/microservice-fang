package com.fang.cloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by quwb on 2017/11/28.
 */
public class AccessFilter extends ZuulFilter {
    @Value(value = "${myProps.accesstoken}")
    private String accesstoken;

    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

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
        String accessToken = request.getParameter("accessToken");
        log.info(String.format("Comsumer Info:%s, AccessToken:%s", accessToken.toString(), accesstoken));

        if(!accessToken.equals(accesstoken)) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"HttpStatus\": 401, \"Message\": \"no permission\", \"Success\": false}");
            return null;
        }
        return null;
    }
}
