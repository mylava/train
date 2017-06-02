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

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lipengfei on 2017/6/2.
 */
public class ServiceLogger {
    private static Logger LOGGER_PRO = LoggerFactory.getLogger("process");
    private static Logger LOGGER_ERR = LoggerFactory.getLogger("err");

    public Object record(ProceedingJoinPoint joinPoint) {

        String methodName = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();


        BaseLog baseLog = LogContext.getBaseLog();
        InvokeTree invokeTree = baseLog.getInvokeTree();
        invokeTree.enter(methodName);

        //获取请求参数
        Object[] args = joinPoint.getArgs();

        RequestLog log = RequestLog.build(baseLog)
                .logType(LogType.MIDDLEWARE)
                .now(System.currentTimeMillis())
                .msName(MSName.KOULIANG_API)
                .invokeTree(invokeTree);

        LOGGER_PRO.info(log.parseLog());

        Object obj = null;
        try {
            obj = joinPoint.proceed();
            invokeTree.exit();
            baseLog = baseLog.logType(LogType.MIDDLEWARE)
                    .now(System.currentTimeMillis())
                    .msName(MSName.KOULIANG_API)
                    .invokeTree(invokeTree);
            LOGGER_PRO.info(baseLog.parseLog(obj));
        } catch (Throwable throwable) {
            LOGGER_ERR.error(throwable.getMessage());
        }
        return obj;
    }
}
