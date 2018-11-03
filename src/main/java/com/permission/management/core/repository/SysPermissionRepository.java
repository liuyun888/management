package com.permission.management.core.repository;

import com.permission.management.core.bean.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysPermissionRepository extends JpaRepository<SysPermission,Long> {

    /**通过name查找权限信息;*/
    SysPermission findByName(String name);
}
