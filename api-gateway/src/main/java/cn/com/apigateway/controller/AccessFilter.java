package cn.com.apigateway.controller;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

/**
 * 这是一个请求过滤器
 * @ClassName AccessFilter
 * @Description TODD
 * @Author Administrator
 * @Date 2019/3/21 0021 上午 11:39
 * @Version 1.0
 **/
public class AccessFilter extends ZuulFilter {
    /**
     * 巨lterType: 过滤器的类型， 它决定过滤器在请求的哪个生命周期中执行。 这里
     * 定义为pre, 代表会在请求被路由之前执行。
     * pre:  可以在请求被路由之前调用。
     * routing:在路由请求时被调用。
     * post: 在 routing 和 error 过滤器之后被调用。
     * error:  处理请求时发生错误时被调用。
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * FlterOrder: 过滤器的执行顺序。 当请求在一 个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行。
     * 通过 int 值来定义过滤器的执行顺序,数值越小优先级越高。
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回 一 个 boolean 值来判断该过滤器是否要执行。 我们可以通过此方法来指定过滤器的有效范围。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        /*RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.containsKey("error.status_code") && !ctx.getBoolean(SEND_ERROR_FILTER_RAN, false);*/
        return true;
    }

    /**
     * 过滤器的具体逻辑,在该函数中,我们可以实现自定义的过滤逻辑,
     * 来确定是否要拦截当前的请求,不对其进行后续的路由,或是在请求路由返回结果之后，
     * 对处理结果做 一 些加工等。
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("ddddd");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object accessToken = request.getParameter("accessToken");
        /*if(accessToken ==  null){
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }*/
        return null;
    }

    private void doSomething(){
        throw new RuntimeException("Exist some errors ...");
    }
}
