package com.permission.management.core.repository;

import com.permission.management.core.bean.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysRoleRepository  extends JpaRepository<SysRole,Long> {

    /**通过username查找用户信息;*/
    SysRole findById (long id);


}
