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
import java.util.List;


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
    /**查找用户信息列表*/
    @Override
    public  List<UserInfo> findUserInfoList() {

        List<UserInfo> userInfoList = userInfoRepository.findAll();
        return userInfoList;
    }
    /**添加用户信息*/
    @Override
    public void addUserInfo(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
    }
    /**删除用户信息*/
    @Override
    public void delUserInfo(Long uid) {
        userInfoRepository.deleteById(uid);
    }
    /**设置用户的角色*/
    @Override
    public void setUserRole(Long uid, Long roleid) {
        userInfoRepository.setUserRole(uid,roleid);
    }

    @Override
    public int getUserRole(Long uid, Long roleid) {
        return userInfoRepository.getUserRole(uid,roleid);
    }


}


