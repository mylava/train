package cn.mylava.provider;

import cn.mylava.dependency.beans.User;
import cn.mylava.dependency.service.DBService;
import com.alibaba.dubbo.config.annotation.Service;
import com.ruwe.collectlog.context.LogContext;
import com.ruwe.collectlog.util.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lipengfei on 2017/6/5.
 */
@Service(version = "1.0")
public class DBServiceImpl implements DBService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBServiceImpl.class);
    public List<User> getUsers() {
        List list = new ArrayList();
        User u1 = new User();
        u1.setId(1l);
        u1.setName("李雷");
        u1.setBirthday(new Date());
        u1.setRegistrationTime(System.currentTimeMillis());

        User u2 = new User();
        u1.setId(2l);
        u1.setName("韩梅梅");
        u1.setBirthday(new Date());
        u1.setRegistrationTime(System.currentTimeMillis());

        LogContext.getTraceId(null);
        LogUtils.info(LOGGER, "用户信息--------3", "3", u2);
        list.add(u1);
        list.add(u2);

        return list;
    }
}
