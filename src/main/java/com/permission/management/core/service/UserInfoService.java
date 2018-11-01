package com.permission.management.core.service;

import net.sf.json.JSONObject;
import com.permission.management.core.bean.UserInfo;

public interface UserInfoService {

    /**通过username查找用户信息;*/
    JSONObject findByUsername(String username);

    /**通过username查找用户信息;用户身份验证*/
    UserInfo getByUsername(String username);
    /**新增用户 */
    int addUserInfo(UserInfo userInfo);

}
