package cn.mylava.business.dao;

import cn.mylava.dependency.beans.User;

import java.util.List;

/**
 * @author lipengfei
 */
public interface UserDao {
    public void insert(List<User> datas);
}
