package com.permission.management.core.repository;

import com.permission.management.core.bean.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SysRoleRepository  extends JpaRepository<SysRole,Long> {

    @Transactional
    @Modifying
    @Query(value="insert into sys_role_permission(roleid,permissionid) values(?,?)",nativeQuery=true)
    int setRolePermission(Long roleid,Long permissionid);

    @Query(value="select count(*) from  sys_role_permission where roleid = ? and permissionid = ?",nativeQuery=true)
    int getRolePermission(Long uid,Long roleid);
    /**通过role查找角色信息;*/
    SysRole findByRole(String role);
}
