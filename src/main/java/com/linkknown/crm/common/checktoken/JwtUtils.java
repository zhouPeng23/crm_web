package com.linkknown.crm.common.checktoken;

import com.alibaba.fastjson.JSONObject;
import com.linkknown.crm.bean.dos.Employee;
import com.linkknown.crm.mapper.EmployeeMapper;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoupeng
 * @date 2023/3/16 15:44
 */
@Component
public class JwtUtils {

    /**
     * 过期时间30分钟
     */
    private static final long EXPIRE_TIME = 30*60*1000;

    /**
     * 加密密钥
     */
    private static final String KEY = "crm_web";


    /**
     * 生成token
     * @param id  用户唯一id
     * @param subject  邮箱 / 账号 ...
     * @return token
     */
    public static String createToken(String id,String subject){
        Map<String,Object> headerMap = new HashMap<>();
        headerMap.put("typ","JWT");
        headerMap.put("alg","HS256");
        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
                //用户ID
                .setId(id)
                //token过期时间  当前时间+有效时间
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                //邮箱
                .setSubject(subject)
                //创建时间
                .setIssuedAt(new Date())
                //加密方式
                .signWith(SignatureAlgorithm.HS256,KEY);
        return builder.compact();
    }


    /**
     * 验证token是否有效
     * @param token  请求头中携带的token
     * @return  token验证结果  1: token认证通过  0: token认证失败  -1: token过期  -2：token解析失败 -3：用户不存在
     */
    public static int verifyToken(String token,HttpServletRequest request){
        EmployeeMapper employeeMapper = getIocBean(EmployeeMapper.class,request);
        Claims claims = null;
        try {
            //token过期后，会抛出ExpiredJwtException 异常，通过这个来判定token过期
            claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            //token过期
            return -1;

        } catch (Exception e){
            //token解析失败
            return -2;
        }

        //判断是不是邮箱，如果是邮箱表示会员，否则表示管理员
        String subject = claims.getSubject();

        //从token中获取用户id，查询该Id的用户是否存在，存在则token验证通过
        Employee employee = null;
        try {
            //员工
            employee = employeeMapper.selectEmployeeById(Long.valueOf(claims.getId()));
        }catch (Exception e){
            //用户不存在
            return -3;
        }

        if(employee!=null){
            //认证通过
            return 1;

        }else {
            //认证失败
            return 0;
        }

    }


    //因为过滤器是在ApplicationContext前面加载的，获取不到IOC容器里面的bean，可以用这种方法获取
    private static  <T> T getIocBean(Class<T> beanClazz, HttpServletRequest request){
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return applicationContext.getBean(beanClazz);
    }


    /**
     * 解析token
     * @param token token
     * @return string
     */
    public static String jiexiToken(String token){
        Claims claims = null;
        try {
            //token过期后，会抛出ExpiredJwtException 异常，通过这个来判定token过期
            claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
            String id = claims.getId();
            String subject = claims.getSubject();
            Date expiration = claims.getExpiration();
            Map<String,Object> resMap = new HashMap<>();
            resMap.put("id",id);
            resMap.put("subject",subject);
            resMap.put("expiration",expiration);
            return JSONObject.toJSONString(resMap);

        }catch (Exception e){
            return "token解析失败";
        }
    }



}
