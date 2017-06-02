package cn.mylava.log.bean;

import cn.mylava.log.constant.LogType;
import cn.mylava.log.constant.MSName;
import cn.mylava.log.context.InvokeTree;
import cn.mylava.log.util.IdGenerator;
import cn.mylava.log.util.LocalAddress;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lipengfei on 2017/5/31.
 */
public class BaseLog {
    protected String id;
    protected LogType logType;
    protected Long now;
    protected String localIp;
    protected String localHostName;
    //微服务名称
    protected MSName msName;
    protected InvokeTree invokeTree;

//    private static SimplePropertyPreFilter filter = new SimplePropertyPreFilter(InvokeTree.class, new String[]{"rootNode"});

    private static ValueFilter valueFilter = new ValueFilter() {
        @Override
        public Object process(Object object, String name, Object value) {
            if(name.equals("invokeTree")){
                BaseLog baseLog = (BaseLog) object;
                return baseLog.getInvokeTree().toString();
            }
            return value;
        }
    };

    BaseLog() {
    }

    public String getId() {
        return id;
    }

    private BaseLog id(String id) {
        this.id = id;
        return this;
    }

    public LogType getLogType() {
        return logType;
    }

    public BaseLog logType(LogType logType) {
        this.logType = logType;
        return this;
    }

    public Long getNow() {
        return now;
    }

    public BaseLog now(Long now) {
        this.now = now;
        return this;
    }

    public String getLocalIp() {
        return localIp;
    }

    private BaseLog localIp(String localIp) {
        this.localIp = localIp;
        return this;
    }
    public String getLocalHostName() {
        return localHostName;
    }

    private BaseLog localHostName(String localHostName) {
        this.localHostName = localHostName;
        return this;
    }

    public MSName getMsName() {
        return msName;
    }

    public BaseLog msName(MSName msName) {
        this.msName = msName;
        return this;
    }

    public InvokeTree getInvokeTree() {
        return invokeTree;
    }

    public BaseLog invokeTree(InvokeTree invokeTree) {
        this.invokeTree = invokeTree;
        return this;
    }

    public static BaseLog build(){
        return new BaseLog().id(IdGenerator.nextId())
                .localIp(LocalAddress.ipAddress())
                .localHostName(LocalAddress.hostName())
                .invokeTree(new InvokeTree());
    }



    public String parseLog() {
        Map<String,Object> map = new HashMap<>();
        map.put(this.getClass().getName(),this);
        return JSON.toJSONString(map, valueFilter);
    }
    public String parseLog(Object obj) {
        Map<String,Object> map = new HashMap<>();
        map.put(this.getClass().getName(),this);
        if (null != obj)
            map.put(obj.getClass().getName(),obj);
        return JSON.toJSONString(map, valueFilter);
    }

    public String parseLog( Map<String,String> replenish) {
        Map<String,Object> map = new HashMap<>();
        map.put(this.getClass().getName(),this);
        if (null != replenish)
            map.put("replenish",replenish);
        return JSON.toJSONString(map, valueFilter);
    }

    public String parseLog( Object obj, Map<String,String> replenish) {
        Map<String,Object> map = new HashMap<>();
        map.put(this.getClass().getName(),this);
        if (null != obj)
            map.put(obj.getClass().getName(),obj);
        if (null != replenish)
            map.put("replenish",replenish);
        return JSON.toJSONString(map, valueFilter);
    }


}
