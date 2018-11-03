package com.permission.management.core.service.impl;

import com.permission.management.core.bean.SysPermission;
import com.permission.management.core.repository.SysPermissionRepository;
import com.permission.management.core.service.SysPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Resource
    private SysPermissionRepository sysPermissionRepository;

    @Override
    public List<SysPermission> findSysPermissionList() {
        return sysPermissionRepository.findAll();
    }

    @Override
    public SysPermission findSysPermissionByName(String name) {
        return sysPermissionRepository.findByName(name);
    }

    @Override
    public Optional<SysPermission> findSysPermissionById(Long uid) {
        return sysPermissionRepository.findById(uid);
    }

    @Override
    public void addSysPermission(SysPermission sysPermission) {
        sysPermissionRepository.save(sysPermission);
    }

    @Override
    public void delSysPermission(Long uid) {
        sysPermissionRepository.deleteById(uid);
    }

    @Override
    public void updateSysPermission(SysPermission sysPermission) {
        sysPermissionRepository.save(sysPermission);

    }
}
