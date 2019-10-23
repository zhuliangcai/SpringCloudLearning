package org.cloud;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liangcai_zhu
 * @Description TODO
 * @Date 2019/10/23 17:09
 */
@Component
public class MyFilter2 extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println(this.getClass().getName());
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String token = request.getParameter("token");
        if(!checkToken(token)){
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.SC_FORBIDDEN);
            try {
                ctx.getResponse().getWriter().write("token is illegal");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return null;
        }


        return null;
    }

    private boolean checkToken(String token) {
        return "zhuge".equals(token);
    }
}
