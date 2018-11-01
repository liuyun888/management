package com.permission.management.core.service.impl;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import com.permission.management.core.bean.UserInfo;
import com.permission.management.core.repository.UserInfoRepository;
import com.permission.management.core.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Service
public class  UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoRepository userInfoRepository;

    @Override
    public JSONObject findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");

        UserInfo userInfo = userInfoRepository.findByUsername(username);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        JSONObject userObject =JSONObject.fromObject(userInfo, jsonConfig);
        return userObject;
    }



    /**通过username查找用户信息;用户身份验证*/
    public UserInfo getByUsername(String username)
    {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoRepository.findByUsername(username);
    }

}


