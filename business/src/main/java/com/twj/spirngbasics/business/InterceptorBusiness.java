package com.twj.spirngbasics.business;

import com.twj.spirngbasics.server.exception.BusinessException;
import com.twj.spirngbasics.server.manage.UserManage;
import com.twj.spirngbasics.server.util.UserUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public class InterceptorBusiness extends HandlerInterceptorAdapter {


    private static final String[] PATH = {
            "/test/test",
    };
    private static Set<String> ePath = null;
    private static final String TOKEN = "token";

    /**
     * Controller之前执行该方法
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = httpServletRequest.getHeader(TOKEN);
        String path = httpServletRequest.getServletPath();
        if (!StringUtils.isEmpty(token)) {
            try {
                UserManage.setUser(token);
            } catch (BusinessException e) {
                //token解析失败时判断路径是否放行
                return isRelease(path);
            }
            return true;
        }
        return isRelease(path);
    }

    /**
     * 哪些接口即时没有token也直接放行不做拦截。
     * 使用hashset提高效率
     *
     * @param path 请求路径
     * @return true放行 false拦截
     */
    private boolean isRelease(String path) {
        if (this.ePath == null) {
            this.ePath = new HashSet<>();
            for (String c : PATH) {
                this.ePath.add(c);
            }
        }
        if (ePath.contains(path)) {
            return true;
        }
        return false;
    }

    /**
     * Controller之后执行该方法
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //移除该线程存放的变量，防止内存泄漏。
        UserUtils.remove();
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
