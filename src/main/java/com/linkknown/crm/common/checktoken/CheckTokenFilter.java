package com.linkknown.crm.common.checktoken;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoupeng
 * @date 2023/3/17 13:07
 */
public class CheckTokenFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }


    /**
     * 不需要校验token的url
     * @param requestUri 请求头
     * @return 是否
     */
    private boolean noNeedCheckToken(String requestUri) {
        return requestUri.contains("/crmWebApi")

                || requestUri.endsWith(".css")
                || requestUri.endsWith(".js")
                || requestUri.endsWith(".jpg")
                || requestUri.endsWith(".png")
                || requestUri.endsWith(".html");
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        Map<String,String> map = new HashMap<>();
        String requestUri = httpServletRequest.getRequestURI();
        logger.info("====================请求上来了(requestUri):{}",requestUri);

        if(requestUri!=null){
            //以下请求不需要校验token，直接放行
            if(this.noNeedCheckToken(requestUri)){
                filterChain.doFilter(httpServletRequest,httpServletResponse);
                return;

            }else{
                String token = (httpServletRequest).getHeader("token");
                if(!StringUtils.isEmpty(token)){
                    //token验证结果
                    int verifyCode  = JwtUtils.verifyToken(token,httpServletRequest);
                    if (verifyCode == -1){
                        //验证失败
                        map.put("code","303");
                        map.put("message","token过期");
                        httpServletResponse.setStatus(303);

                    }else if (verifyCode == -2){
                        //验证失败
                        map.put("code","304");
                        map.put("message","token解析失败");
                        httpServletResponse.setStatus(304);

                    }else if (verifyCode == -3){
                        //验证失败
                        map.put("code","305");
                        map.put("message","用户不存在");
                        httpServletResponse.setStatus(305);

                    }else if (verifyCode == 1){
                        //验证成功，放行
                        filterChain.doFilter(httpServletRequest,httpServletResponse);
                        return;

                    }else if(verifyCode == 0){
                        map.put("code","306");
                        map.put("message","token认证失败");
                        httpServletResponse.setStatus(306);
                    }

                }else {
                    map.put("code","307");
                    map.put("message","当前请求未携带token信息");
                    httpServletResponse.setStatus(307);
                }
            }
        }
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(JSONObject.toJSONString(map));
        writer.flush();
        writer.close();
    }


    @Override
    public void destroy() { }


}
