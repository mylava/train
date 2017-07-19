package cn.mylava.dependency.service;

import cn.mylava.dependency.beans.User;

import java.util.List;

/**
 * Created by lipengfei on 2017/6/5.
 */
public interface DBService {
    public List<User> getUsers();
}
