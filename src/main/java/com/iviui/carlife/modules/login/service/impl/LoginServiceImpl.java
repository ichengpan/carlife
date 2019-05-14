package com.iviui.carlife.modules.login.service.impl;

import com.iviui.carlife.modules.login.repository.LoginRepository;
import com.iviui.carlife.modules.login.service.LoginService;
import com.iviui.carlife.modules.login.vo.SysPermission;
import com.iviui.carlife.modules.login.vo.SysRole;
import com.iviui.carlife.modules.login.vo.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: ChengPan
 * @date: 2019/5/7
 * @description: 用户信息实现类
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginRepository userInfoRepository;

    @Override
    public UserInfo findUserInfoByUserName(String username) {
        return null;
    }

    @Override
    public List<SysRole> findRoleByUid(Long uid) {
        return null;
    }

    @Override
    public List<SysPermission> findPermissionByRoleId(UserInfo userInfo) {
        return null;
    }

    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoRepository.findByUsername(username);
    }
}