package com.permission.management.core.repository;

import com.permission.management.core.bean.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysPermissionRepository extends JpaRepository<SysPermission,Long> {

}
