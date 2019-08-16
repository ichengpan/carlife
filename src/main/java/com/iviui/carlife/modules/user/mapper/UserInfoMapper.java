package com.iviui.carlife.modules.user.mapper;

import com.iviui.carlife.modules.user.vo.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * @author: ChengPan
 * @date: 2019/5/17
 * @description: 用户信息
 */
@Mapper
public interface UserInfoMapper{
    Integer countUserInfo(User user);

    List<Map<String, Object>> listUserInfo(User user);

    List<Map<String, Object>> userInfoDownLoad(User userInfo);

    int insertUserInfo(List<Map<String, Object>> list);

    List<Map<String, Object>> queryPeopleData(Map<String, Object> paramMap);

    Map<String, Object> getUserInfoByUid(String uid);
}
