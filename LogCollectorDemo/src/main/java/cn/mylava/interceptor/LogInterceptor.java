package cn.mylava.interceptor;

import cn.mylava.log.bean.RequestLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * Created by lipengfei on 2017/5/26.
 */
@Deprecated
public class LogInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //开始统计页面耗时
        //检查访客唯一标识、用户号和会话token等cookie

      /*  RequestLog log = RequestLog.build();

        log.id(LogContext.getTraceId())
                .version1(LogContext.getVersion())
                .host(request.getHeader("Host"))
                .referer(request.getHeader("Referer"))
                .userAgent(request.getHeader("User-Agent"))
                .cookie(request.getHeader("Cookie"))
                .clientIp(request.getRemoteAddr())
                .contentType(request.getContentType())
                .contentPath(request.getContextPath())
                .serverName(request.getServerName())
                .method(request.getMethod())
                .sessionId(request.getRequestedSessionId())
                .requestURI(request.getRequestURI())
                .requestURL(request.getRequestURL().toString())
                .servletPath(request.getServletPath())
                .params(request.getParameterMap());

        if (null != request.getQueryString() && !"".equals(request.getQueryString()))
            log.queryString(URLDecoder.decode(request.getQueryString(), "utf-8"));




        request.setAttribute("baseLog", log);*/
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
