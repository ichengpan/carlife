package com.iviui.carlife.modules.role.service.impl;

import com.iviui.carlife.modules.role.mapper.RoleMapper;
import com.iviui.carlife.modules.role.service.RoleService;
import com.iviui.carlife.modules.role.vo.RoleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: ChengPan
 * @date: 2019/7/26
 * @description: 角色管理
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Integer countRoleInfo(RoleInfo roleInfo) {
        return roleMapper.countRoleInfo(roleInfo);
    }

    @Override
    public List<Map<String, Object>> listRoleInfo(RoleInfo roleInfo) {
        return roleMapper.listRoleInfo(roleInfo);
    }
}