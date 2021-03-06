package com.iviui.carlife.modules.role.service;

import com.iviui.carlife.modules.role.vo.RoleInfo;

import java.util.List;
import java.util.Map;

/**
 * @author: ChengPan
 * @date: 2019/7/26
 * @description: 角色管理
 */
public interface RoleService {

    /**
     * 符合条件的总条数
     * @param roleInfo
     * @return
     */
    Integer countRoleInfo(RoleInfo roleInfo);

    /**
     * 筛选符合条件的数据
     * @param roleInfo
     * @return
     */
    List<Map<String, Object>> listRoleInfo(RoleInfo roleInfo);
}
