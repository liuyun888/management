package com.permission.management.core.controller;

import com.permission.management.core.bean.UserInfo;
import com.permission.management.core.service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;
    /**
     * 用户查询.
     * @return
     */
    @RequestMapping(value = "/userInfo/userList", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("userInfo:view")//权限管理;
    public UserInfo userInfo(UserInfo userInfo){
        UserInfo userInfo1 =  userInfoService.findByUsername(userInfo.getUsername());
        return userInfo1;
    }

    /**
     * 用户添加;
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")//权限管理;
    public String userInfoAdd(){
        return "userInfoAdd";
    }
    /**
     * 用户删除;
     * @return
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")//权限管理;
    public String userDel(){
        return "userInfoDel";
    }
}