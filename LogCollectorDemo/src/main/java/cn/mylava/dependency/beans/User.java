package cn.mylava.dependency.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lipengfei on 2017/5/26.
 */
public class User implements Serializable{
    private Long id;
    private String name;
    private Date birthday;
    private Long registrationTime;

    public User() {
    }

    public User(Long id, String name, Date birthday, Long registrationTime) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.registrationTime = registrationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Long registrationTime) {
        this.registrationTime = registrationTime;
    }
}
