package com.threebrother.qofood.interceptor;

import com.threebrother.qofood.common.Constant;
import com.threebrother.qofood.entity.MngUser;
import com.threebrother.qofood.model.Enum.Types;
import com.threebrother.qofood.service.MngUserServier;
import com.threebrother.qofood.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 自定义 后台管理拦截器
 * @author zhaoxiaolezi
 * @date 2018/6/19 18:24
 */
@Component
public class MngInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MngInterceptor.class);

    private static final String USER_AGENT = "user-agent";

    private MapCache cache = MapCache.single();

    @Resource
    private MngUserServier mngUserServier;

    @Resource
    private Commons commons;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String uri = httpServletRequest.getRequestURI();

        LOGGER.info("UserAgent: {}", httpServletRequest.getHeader(USER_AGENT));
        LOGGER.info("用户访问地址: {}, 来路地址: {}", uri, IPKit.getIpAddrByRequest(httpServletRequest));

        // 请求拦截处理 从session中获取
        MngUser mngUser = TaleUtil.getLoginUser(httpServletRequest);
        if (null == mngUser) {
            // 从cookie中获取
            Integer mngUserId = TaleUtil.getCookieUid(httpServletRequest);
            if (null != mngUserId) {
                // 这里还是有安全隐患,cookie是可以伪造的
                mngUser = mngUserServier.queryUserById(mngUserId);
                httpServletRequest.getSession().setAttribute(Constant.LOGIN_SESSION_KEY, mngUser);
            }
        }

        if (uri.startsWith("/mng") && !uri.startsWith("/mng/login") && null == mngUser) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/mng/login");
            return false;
        }

        // 设置get请求的token
        if (httpServletRequest.getMethod().equals("GET")) {
            String csrf_token = UUID.UU64();
            // 默认存储30分钟
            cache.hset(Types.CSRF_TOKEN.getType(), csrf_token, uri, 30 * 60);
            httpServletRequest.setAttribute("_csrf_token", csrf_token);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // 一些工具类和公共方法
        httpServletRequest.setAttribute("commons", commons);
        // httpServletRequest.setAttribute("adminCommons", adminCommons);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
