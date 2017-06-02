package cn.mylava.business.web;

import cn.mylava.business.model.User;
import cn.mylava.business.service.UserService;
import cn.mylava.log.bean.RequestLog;
import cn.mylava.log.context.LogContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lipengfei on 2017/5/25.
 */
@Controller
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private UserService userService;


    @RequestMapping("/hello")
    public ModelAndView greeting(@RequestParam(value="name", defaultValue="晶晶") String name, HttpServletRequest request) throws UnsupportedEncodingException {

        RequestLog log = (RequestLog) request.getAttribute("baseLog");
//        log.now(System.currentTimeMillis()).logType(LogType.API);

        User user = userService.getUser();

        Map<String, String> map = new HashMap<String, String>();
        map.put("userName", name);

        //自己打印数据
        LOGGER.info("-------");
        Map<String,String> obj = new HashMap<>();

        LOGGER.info(LogContext.getBaseLog().parseLog());
//        BaseLog1<User> log = (BaseLog1<User>) request.getAttribute("baseLog");



//        LOGGER.info(JSON.toJSONString(log));
//        LOGGER.info(JSON.toJSONString(user));
//        map.put("traceBean",)
//        String logStr = log.parseLog(log,user,map);


//        LOGGER.info(logStr);



        return new ModelAndView("/hello",map);
    }
}
