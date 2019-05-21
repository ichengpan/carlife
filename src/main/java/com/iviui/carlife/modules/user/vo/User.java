package com.iviui.carlife.modules.user.vo;

import com.iviui.carlife.modules.util.Page;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author: ChengPan
 * @date: 2019/5/21
 * @description: 用户
 */
public class User extends Page {

    private long uid;//用户id;

    private String username;//账号.

    private String name;//名称（昵称或者真实姓名，不同系统不同定义）

    private byte state;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", state=" + state +
                '}';
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }
}