package com.iviui.carlife.modules.user.service;

import com.iviui.carlife.modules.login.vo.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * @author: ChengPan
 * @date: 2019/5/13
 * @description: 菜单信息
 */
public interface UserInfoService {

    Integer countUserInfo(UserInfo userInfo);

    List<Map<String, Object>> listUserInfo(UserInfo userInfo, Integer page, Integer limit);
}
