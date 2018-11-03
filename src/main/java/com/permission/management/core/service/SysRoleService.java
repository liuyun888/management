package com.permission.management.core.service;

import com.permission.management.core.bean.SysPermission;
import com.permission.management.core.bean.SysRole;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Optional;

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
    /**通过role查找角色信息;*/
    SysRole getByRole(String role);
    /**通过id查询角色*/
    Optional<SysRole> findSysRoleById(Long uid);
    /**更新角色 */
    void updateSysRole(SysRole sysRole);
}
