package cn.mylava.log.context;

import cn.mylava.log.bean.BaseLog;

/**
 * Created by lipengfei on 2017/6/2.
 */
public class LogContext {
    //线程容器
    private static final ThreadLocal<LogContext> local = new ThreadLocal<LogContext>();
    //追踪Id
    private String traceId;
    private BaseLog baseLog;


    private LogContext() {
    }

    /**
     * 初始化LogContext
     */
    public static LogContext getContext() {
        LogContext context = local.get();
        if (context == null) {
            context = new LogContext();
            context.baseLog = BaseLog.build();
            context.traceId = context.baseLog.getId();
            local.set(context);
        }
        return context;
    }

    public static String getTraceId() {
        return getContext().traceId;
    }

    public static BaseLog getBaseLog() {
        return getContext().baseLog;
    }

}
