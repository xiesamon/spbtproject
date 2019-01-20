package com.samon.spbt.filter;

import com.sun.javafx.tools.packager.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 过滤器
 */
//注册器名称为customFilter,拦截的url为所有
@WebFilter(filterName="customFilter",urlPatterns={"/*"})
public class CustomFilter implements Filter {

    private final static Logger log = LoggerFactory.getLogger(CustomFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter 初始化");
    }

    /*@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        log.info("doFilter 请求处理");
        //对request、response进行一些预处理
        // 比如设置请求编码
        // request.setCharacterEncoding("UTF-8");
        // response.setCharacterEncoding("UTF-8");
        //TODO 进行业务逻辑

        //链路 直接传给下一个过滤器
        chain.doFilter(request, response);
    }*/

    @Override
    public void destroy() {
        log.info("filter 销毁");
    }

    /**
     * json web token 在请求头的名字
     */
    private String tokenHeader = "X_Auth_Token";

    /**
     * 跨域处理
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String token = request.getHeader("X_Auth_Token");
        System.out.println(token + "token");
        String Origin = request.getHeader("Origin");
        System.out.println("Origin:" + Origin);
        System.out.println("tokenHeader:" + this.tokenHeader);
        log.info("Origin:  " + Origin);
        response.setHeader("Access-Control-Allow-Origin", Origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, " + this.tokenHeader);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(req, res);
    }

}