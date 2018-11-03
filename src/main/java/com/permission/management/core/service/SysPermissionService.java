package com.permission.management.core.service;

import com.permission.management.core.bean.SysPermission;

import java.util.List;
import java.util.Optional;

public interface SysPermissionService {
    /**查询权限列表*/
    List<SysPermission> findSysPermissionList();
    /**通过name查询权限*/
    SysPermission findSysPermissionByName(String name);
    /**通过id查询权限*/
    Optional<SysPermission> findSysPermissionById(Long uid);
    /**新增权限 */
    void addSysPermission(SysPermission sysPermission);
    /**删除权限*/
    void delSysPermission(Long uid);
    /**更新权限*/
    void updateSysPermission(SysPermission sysPermission);
}
