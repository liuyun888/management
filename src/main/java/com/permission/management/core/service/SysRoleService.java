package com.permission.management.core.service;

import com.permission.management.core.bean.SysRole;
import net.sf.json.JSONObject;

import java.util.List;

public interface SysRoleService {
    /**查找角色信息列表*/
    List<SysRole> findRoles();
    /**新增角色 */
    void addSysRole(SysRole sysRole);
    /**删除角色*/
    void delSysRole(Long uid);
    /**设置角色权限*/
    void setRolePermission(Long roleId,Long permissionId);
    /**获取角色权限，用于判断重复设置*/
    int getRolePermission(Long roleId,Long permissionId);

}
