package cn.mylava.business.web;

import cn.mylava.business.BUS;
import cn.mylava.business.service.TestService;
import cn.mylava.business.service.UserService;
import cn.mylava.dependency.beans.User;
import cn.mylava.dependency.service.DBService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.ruwe.collectlog.context.LogContext;
import com.ruwe.collectlog.util.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lipengfei on 2017/5/25.
 */
@Controller
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Resource
    private UserService userService;

    @Resource
    private TestService testService;

   /* @Reference(version = "1.0")
    private DBService dbService;*/

    @RequestMapping("/hello")
    public ModelAndView greeting(@RequestParam(value = "name", defaultValue = "李四") String name, HttpServletRequest request) throws UnsupportedEncodingException {
        LogUtils.info(LOGGER, "request", "2\n\n", request);
        User user = userService.getUser();
        //自己打印数据
        LogUtils.info(LOGGER, "用户信息--------1", "1\n\n", user);

        //自己打印数据
//        user = dbService.getUsers().get(0);
        LogUtils.info(LOGGER, "用户信息--------1", "1\n\n", user);

        Map<String, String> map = new HashMap<String, String>();
        map.put("userName", user.getName());

        return new ModelAndView("/hello", map);
    }
}
