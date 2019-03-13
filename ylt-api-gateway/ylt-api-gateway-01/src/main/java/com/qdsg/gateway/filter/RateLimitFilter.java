//package com.xpc.gateway.filter;
//
//import com.google.common.util.concurrent.RateLimiter;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;
//
//@Component
//public class RateLimitFilter extends ZuulFilter {
//    private final static RateLimiter RATE_LIMTER = RateLimiter.create(100);
//    @Override
//    public String filterType() {
//        return PRE_TYPE;
//    }
//
//    @Override
//    public int filterOrder() {
//        return SERVLET_DETECTION_FILTER_ORDER - 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        if (!RATE_LIMTER.tryAcquire()){
////            throw new RuntimeException("最多只允许100人访问");
//            RequestContext requestContext = RequestContext.getCurrentContext();
//            HttpServletResponse response = requestContext.getResponse();
//            try {
//                response.sendError(-1,"最多只允许100人访问");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//}
