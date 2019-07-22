package com.iviui.carlife.modules.user.service;

import com.iviui.carlife.modules.user.vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author: ChengPan
 * @date: 2019/5/13
 * @description: 菜单信息
 */
public interface UserInfoService {

    Integer countUserInfo(User userInfo);

    List<Map<String, Object>> listUserInfo(User userInfo);

    List<Map<String, Object>> userInfoDownLoad(User userInfo);

    void insertBatchesUserInfo();

    void downLoadBatchesUserInfo(HttpServletRequest request, HttpServletResponse response);

    String insertPeople();
}
