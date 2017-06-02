package cn.mylava.log.aspect;

import cn.mylava.log.bean.BaseLog;
import cn.mylava.log.bean.RequestLog;
import cn.mylava.log.constant.LogType;
import cn.mylava.log.constant.MSName;
import cn.mylava.log.context.InvokeTree;
import cn.mylava.log.context.LogContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by lipengfei on 2017/6/2.
 */
@Component
public class ControllerLogger {
    private static Logger LOGGER_WEB = LoggerFactory.getLogger("web");
    private static Logger LOGGER_ERR = LoggerFactory.getLogger("err");

    public Object record(ProceedingJoinPoint joinPoint) {

        String methodName = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();

        BaseLog baseLog = LogContext.getBaseLog();
        InvokeTree invokeTree = baseLog.getInvokeTree();
        invokeTree.start(methodName);

        //获取请求参数
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = null;

        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                request = (HttpServletRequest) arg;
            }
        }

        if (request != null) {
            RequestLog log = RequestLog.build(baseLog)
                    .logType(LogType.API_REQUEST)
                    .now(System.currentTimeMillis())
                    .msName(MSName.KOULIANG_API)
                    .invokeTree(invokeTree)
                    .appInfo("")
                    .host(request.getHeader("Host"))
                    .serverName(request.getServerName())
                    .referer(request.getHeader("Referer"))
                    .userAgent(request.getHeader("User-Agent"))
                    .cookie(request.getHeader("Cookie"))
                    .clientIp(request.getRemoteAddr())
                    .contentType(request.getContentType())
                    .method(request.getMethod())
                    .sessionId(request.getRequestedSessionId())
                    .requestURI(request.getRequestURI())
                    .requestURL(request.getRequestURL().toString())
                    .contentPath(request.getContextPath())
                    .servletPath(request.getServletPath())
                    .params(request.getParameterMap());

            if (null != request.getQueryString() && !"".equals(request.getQueryString())) {
                try {
                    log.queryString(URLDecoder.decode(request.getQueryString(), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    log.queryString(request.getQueryString());
                    LOGGER_ERR.error(e.getMessage());
                }
            }

            LOGGER_WEB.info(log.parseLog());
        }

        Object obj = null;
        try {
            obj = joinPoint.proceed();
            invokeTree.exit();
            baseLog = baseLog.logType(LogType.API_RESPONSE)
                    .now(System.currentTimeMillis())
                    .msName(MSName.KOULIANG_API)
                    .invokeTree(invokeTree);
            LOGGER_WEB.info(baseLog.parseLog(obj));
        } catch (Throwable throwable) {
            LOGGER_ERR.error(throwable.getMessage());
        }
        return obj;
    }
}
