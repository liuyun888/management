package com.permission.management.core.service.impl;

import com.permission.management.core.bean.UserInfo;
import com.permission.management.core.repository.UserInfoRepository;
import com.permission.management.core.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoRepository.findByUsername(username);
    }

}


