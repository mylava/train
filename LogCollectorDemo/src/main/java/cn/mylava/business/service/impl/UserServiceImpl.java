package cn.mylava.business.service.impl;

import cn.mylava.business.model.User;
import cn.mylava.business.service.UserService;
import cn.mylava.business.web.HelloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by lipengfei on 2017/5/31.
 */
@Service
public class UserServiceImpl implements UserService{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User getUser() {
        LOGGER.info("---------");
        User user = new User(1l,"张三",new Date(),System.currentTimeMillis());
        return user;
    }
}
