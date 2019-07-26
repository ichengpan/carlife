package com.iviui.carlife.modules.role.vo;

import com.iviui.carlife.modules.util.Page;

/**
 * @author: ChengPan
 * @date: 2019/7/26
 * @description: 角色信息
 */
public class RoleInfo extends Page {
   private Long id;

   private Boolean available;

   private String description;

   private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}