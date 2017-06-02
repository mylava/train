package cn.mylava.log.bean;

import cn.mylava.log.constant.LogType;
import cn.mylava.log.constant.MSName;
import cn.mylava.log.context.InvokeTree;
import cn.mylava.log.util.LocalAddress;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lipengfei on 2017/5/27.
 */
public class RequestLog extends BaseLog {
    //json-------考虑是否放入ua
    private String appInfo;
    //request host
    private String host;
    //request serverName
    private String serverName;
    //referer
    private String referer;
    //userAgent
    private String userAgent;
    //cookie
    private String cookie;
    //客户端ip
    private String clientIp;
    //mime type
    private String contentType;
    //请求方法
    private String method;
    //请求字符串
    private String queryString;
    //session id
    private String sessionId;
    //request uri
    private String requestURI;
    //request url
    private String requestURL;
    //content path
    private String contentPath;
    //servlet path
    private String servletPath;
    //请求参数
    private Map<String, String[]> params;

    private RequestLog() {
    }

    private RequestLog(BaseLog log) {
        this.id(log.getId())
            .localIp(log.getLocalIp())
            .localHostName(log.getLocalHostName());
    }

    private RequestLog id(String id) {
        this.id = id;
        return this;
    }

    public RequestLog logType(LogType logType) {
        this.logType = logType;
        return this;
    }

    public RequestLog invokeTree(InvokeTree invokeTree) {
        this.invokeTree = invokeTree;
        return this;
    }

    public RequestLog now(Long now) {
        this.now = now;
        return this;
    }

    private RequestLog localIp(String localIp) {
        this.localIp = localIp;
        return this;
    }

    private RequestLog localHostName(String localHostName) {
        this.localHostName = localHostName;
        return this;
    }

    public RequestLog msName(MSName msName) {
        this.msName = msName;
        return this;
    }

    public String getHost() {
        return host;
    }

    public RequestLog host(String host) {
        this.host = host;
        return this;
    }

    public String getReferer() {
        return referer;
    }

    public RequestLog referer(String referer) {
        this.referer = referer;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public RequestLog userAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public String getCookie() {
        return cookie;
    }

    public RequestLog cookie(String cookie) {
        this.cookie = cookie;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public RequestLog contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String getServerName() {
        return serverName;
    }

    public RequestLog serverName(String serverName) {
        this.serverName = serverName;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public RequestLog method(String method) {
        this.method = method;
        return this;
    }

    public String getQueryString() {
        return queryString;
    }

    public RequestLog queryString(String queryString) {
        this.queryString = queryString;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public RequestLog sessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public RequestLog requestURI(String requestURI) {
        this.requestURI = requestURI;
        return this;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public RequestLog requestURL(String requestURL) {
        this.requestURL = requestURL;
        return this;
    }

    public String getContentPath() {
        return contentPath;
    }

    public RequestLog contentPath(String contentPath) {
        this.contentPath = contentPath;
        return this;
    }

    public String getServletPath() {
        return servletPath;
    }

    public RequestLog servletPath(String servletPath) {
        this.servletPath = servletPath;
        return this;
    }

    public Map<String, String[]> getParams() {
        return params;
    }

    public RequestLog params(Map<String, String[]> params) {
        this.params = params;
        return this;
    }

    public String getClientIp() {
        return clientIp;
    }

    public RequestLog clientIp(String clientIp) {
        this.clientIp = clientIp;
        return this;
    }

    public String getAppInfo() {
        return appInfo;
    }

    public RequestLog appInfo(String appInfo) {
        this.appInfo = appInfo;
        return this;
    }

    public static RequestLog build(BaseLog log){
        return new RequestLog(log);
    }

    /*public String parseLog() {
        Map<String,Object> map = new HashMap<>();
        map.put(this.getClass().getName(),this);
        return JSON.toJSONString(map);
    }
    public String parseLog(Object obj) {
        Map<String,Object> map = new HashMap<>();
        map.put(this.getClass().getName(),this);
        if (null != obj)
            map.put(obj.getClass().getName(),obj);
        return JSON.toJSONString(map);
    }
    public String parseLog(RequestLog log, Object obj) {
        Map<String,Object> map = new HashMap<>();
        if (null != log)
            map.put(log.getClass().getName(),log);
        if (null != obj)
            map.put(obj.getClass().getName(),obj);
        return JSON.toJSONString(map);
    }

    public String parseLog(RequestLog log, Map<String,String> replenish) {
        Map<String,Object> map = new HashMap<>();
        if (null != log)
            map.put(log.getClass().getName(),log);
        if (null != replenish)
            map.put("replenish",replenish);
        return JSON.toJSONString(map);
    }

    public String parseLog(RequestLog log, Object obj, Map<String,String> replenish) {
        Map<String,Object> map = new HashMap<>();
        if (null != log)
            map.put(log.getClass().getName(),log);
        if (null != obj)
            map.put(obj.getClass().getName(),obj);
        if (null != replenish)
            map.put("replenish",replenish);
        return JSON.toJSONString(map);
    }*/

}
