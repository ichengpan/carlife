package com.iviui.carlife.modules.user.mapper;

import com.iviui.carlife.modules.util.Page;
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
    Integer countUserInfo();

    List<Map<String, Object>> listUserInfo(Page page);
}
