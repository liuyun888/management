package com.permission.management.core.service;

import net.sf.json.JSONObject;
import com.permission.management.core.bean.UserInfo;

import java.util.List;

public interface UserInfoService {

    /**通过username查找用户信息;*/
    JSONObject findByUsername(String username);
    /**通过username查找用户信息;用户身份验证*/
    UserInfo getByUsername(String username);
    /**查询用户信息列表*/
    List<UserInfo> findUserInfoList();
    /**新增用户 */
    void addUserInfo(UserInfo userInfo);
    /**删除用户*/
    void delUserInfo(Long uid);
    /**设置用户角色*/
    void setUserRole(Long uid,Long roleid);
    /**获得用户角色，用于重复验证*/
    int getUserRole(Long uid,Long roleid);
}
