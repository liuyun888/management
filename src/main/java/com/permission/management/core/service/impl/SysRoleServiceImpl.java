package com.permission.management.core.service.impl;

import com.permission.management.core.bean.SysRole;
import com.permission.management.core.repository.SysRoleRepository;
import com.permission.management.core.service.SysRoleService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleRepository sysRoleRepository;

    @Override
    public List<SysRole> findRoles() {

        return sysRoleRepository.findAll();
    }

    @Override
    public void addSysRole(SysRole sysRole) {
        sysRoleRepository.save(sysRole);
    }

    @Override
    public void delSysRole(Long uid) {
        sysRoleRepository.existsById(uid);
    }

    @Override
    public void setRolePermission(Long roleId, Long permissionId) {
        sysRoleRepository.setRolePermission(roleId,permissionId);
    }

    @Override
    public int getRolePermission(Long roleId, Long permissionId) {
        return sysRoleRepository.getRolePermission(roleId,permissionId);
    }


}
