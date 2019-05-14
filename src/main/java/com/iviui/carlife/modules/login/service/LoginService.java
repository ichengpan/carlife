package com.iviui.carlife.modules.login.service;

import com.iviui.carlife.modules.login.vo.SysPermission;
import com.iviui.carlife.modules.login.vo.SysRole;
import com.iviui.carlife.modules.login.vo.UserInfo;

import java.util.List;

/**
 * @author: ChengPan
 * @date: 2019/5/7
 * @description: 用户信息
 */
public interface LoginService {
    UserInfo findUserInfoByUserName(String username);

    List<SysRole> findRoleByUid(Long uid);

    List<SysPermission> findPermissionByRoleId(UserInfo userInfo);

    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}
