package cn.mylava.business.service.impl;

import cn.mylava.dependency.beans.User;
import cn.mylava.business.service.TestService;
import com.ruwe.collectlog.util.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by lipengfei on 2017/6/2.
 */
@Service
public class TestServiceImpl implements TestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceImpl.class);
    @Override
    public User test(User user) {
        LogUtils.info(LOGGER,"testService--------4","1",user);
        return user;
    }

}
