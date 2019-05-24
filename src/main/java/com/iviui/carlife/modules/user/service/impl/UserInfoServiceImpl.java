package com.iviui.carlife.modules.user.service.impl;

import com.iviui.carlife.modules.user.mapper.UserInfoMapper;
import com.iviui.carlife.modules.user.service.UserInfoService;
import com.iviui.carlife.modules.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: ChengPan
 * @date: 2019/5/13
 * @description: 菜单信息
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Integer countUserInfo(User userInfo) {
        return userInfoMapper.countUserInfo(userInfo);
    }

    @Override
    public List<Map<String, Object>> listUserInfo(User userInfo) {

        return userInfoMapper.listUserInfo(userInfo);
    }

    @Override
    public List<Map<String, Object>> userInfoDownLoad(User userInfo) {
        return userInfoMapper.userInfoDownLoad(userInfo);
    }

}