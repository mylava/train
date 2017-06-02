package cn.mylava.log.constant;

/**
 * Created by lipengfei on 2017/5/31.
 */
public enum LogType {
    API_REQUEST("API调用"),
    API_RESPONSE("API响应"),
    //是否加入 process usercenter 类型
    MIDDLEWARE("中间件调用"),
    PROCESS_IN(""),
    PROCESS_OUT(""),
    DB("数据库操作"),
    JOB_EXECUTE("job执行"),
    CUSTOM("自定义"),
    ERR("错误"),
    THIRDPARTY("第三方调用");

    private String description;

    LogType(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }

}
