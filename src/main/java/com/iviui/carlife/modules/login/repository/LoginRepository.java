package com.iviui.carlife.modules.login.repository;

import com.iviui.carlife.modules.login.vo.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author: ChengPan
 * @date: 2019/5/8
 * @description: 用户信息
 */
public interface LoginRepository extends CrudRepository<UserInfo,Long> {
    /**
     *
     * @param username 用户名
     * @return 通过username查找用户信息
     */
    public UserInfo findByUsername(String username);
}
