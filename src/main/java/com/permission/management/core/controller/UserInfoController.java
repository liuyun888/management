package com.permission.management.core.controller;

import com.alibaba.fastjson.JSON;
import com.permission.management.core.bean.SysPermission;
import com.permission.management.core.bean.SysRole;
import com.permission.management.core.bean.UserInfo;
import com.permission.management.core.service.UserInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/userInfo")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;
    /**
     * 用户查询.
     * @return
     */
    @GetMapping("/userList/{username}")
    @RequiresPermissions("userInfo:view")//权限管理;
    public JSONObject getUserInfoByName(@PathVariable String username){

        JSONObject result = new JSONObject();
        JSONObject userInfo = userInfoService.findByUsername(username);

        //账号
        result.put("username", userInfo.get("username"));
        //名称（昵称或者真实姓名）
        result.put("name", userInfo.get("name"));

        /**把JSONArray(roleJsonArray)转换为List对象*/
        JSONArray roleListArray = (JSONArray) userInfo.get("roleList");
        if(roleListArray.size() > 0) {
            //拥有的角色
            List rolelist = getRoles(roleListArray);
            result.put("roleList", rolelist);
            //拥有的权限
            List permissionlist = getPermission(roleListArray);
            result.put("permissionlist", permissionlist);
        }

        return result;
    }
    //获取拥有的角色
    private List getRoles(JSONArray roleListArray) {
        List<SysRole> roleJonList = new ArrayList<>();
        List roleList = new ArrayList();
        for (int i = 0; i < roleListArray.size(); i++) {
            JSONObject roleJson = (JSONObject) roleListArray.get(i);
            SysRole roleJsonDate = (SysRole) JSONObject.toBean(roleJson, SysRole.class);
            roleJonList.add(roleJsonDate);
            roleList.add(roleJonList.get(i).getRole());
        }
        return roleList;
    }
    //获取拥有的权限
    private List getPermission(JSONArray roleListArray) {
        List<SysRole> roleJonList = new ArrayList<>();
        List<SysPermission> permissionJonList = new ArrayList<>();
        List permissions = new ArrayList();
        for (int i = 0; i < roleListArray.size(); i++) {
            JSONObject roleJson = (JSONObject) roleListArray.get(i);
            SysRole roleJsonDate = (SysRole) JSONObject.toBean(roleJson, SysRole.class);
            roleJonList.add(roleJsonDate);

            List permissionList = roleJonList.get(i).getPermissions();
            if(permissionList.size() > 0)
            {
                for (int j = 0; j < permissionList.size() ; j++) {
                    JSONObject  permissionJson =JSONObject.fromObject(permissionList.get(j));
                    SysPermission permissionJsonDate = (SysPermission) JSONObject.toBean(permissionJson, SysPermission.class);
                    permissionJonList.add(permissionJsonDate);

                    permissions.add(permissionJonList.get(j).getPermission());
                }
                permissionList.clear();
            }
        }
        return permissions;
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