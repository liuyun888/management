package com.permission.management.core.controller;

import com.permission.management.core.bean.SysPermission;
import com.permission.management.core.bean.SysRole;
import com.permission.management.core.bean.UserInfo;
import com.permission.management.core.service.UserInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
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
    @RequiresPermissions("userInfo:list")//权限管理;
    public JSONObject getUserInfoByName(@PathVariable String username){

        JSONObject result = new JSONObject();
        JSONObject userInfo = userInfoService.findByUsername(username);

        //账号
        result.put("username", userInfo.get("username"));
        //名称（昵称或者真实姓名）
        result.put("name", userInfo.get("name"));

        /**把JSONArray 转换为List对象,然后操作List*/
        //将userInfo中的角色列表信息转换为JSONArray
        JSONArray roleListArray = (JSONArray) userInfo.get("roleList");
        //权限在角色数据中
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
        //角色List
        List roleList = new ArrayList();
        //遍历userInfo的角色List
        for (int i = 0; i < roleListArray.size(); i++) {
            //转化为JSONObject
            JSONObject roleJson = (JSONObject) roleListArray.get(i);
            //转为SysRole实体
            SysRole roleJsonDate = (SysRole) JSONObject.toBean(roleJson, SysRole.class);
            //获取实体中角色数据
            roleList.add(roleJsonDate.getRole());
        }
        return roleList;
    }
    //从角色列表数据中获取拥有的权限
    private List getPermission(JSONArray roleListArray) {
        //权限List
        List permissions = new ArrayList();
        //权限名称List
        List permissionNames = new ArrayList();
        //权限路径List
        List permissionUrls = new ArrayList();

        //遍历userInfo的角色List
        for (int i = 0; i < roleListArray.size(); i++) {
            //转化为JSONObject
            JSONObject roleJson = (JSONObject) roleListArray.get(i);
            //转为SysRole实体
            SysRole roleJsonDate = (SysRole) JSONObject.toBean(roleJson, SysRole.class);
            //获取实体中当前角色的权限数据
            List permissionList = roleJsonDate.getPermissions();
            if(permissionList.size() > 0)
            {
                //遍历当前角色的权限数据
                for (int j = 0; j < permissionList.size() ; j++) {
                    //转化为JSONObject
                    JSONObject  permissionJson =JSONObject.fromObject(permissionList.get(j));
                    //转为SysPermission实体
                    SysPermission permissionJsonData = (SysPermission) JSONObject.toBean(permissionJson, SysPermission.class);
                    //获取实体中权限
                    permissions.add(permissionJsonData.getPermission());
                    //获取实体中权限名称
                    permissionNames.add(permissionJsonData.getName());
                    //获取实体中权限路径
                    permissionUrls.add(permissionJsonData.getUrl());
                }
            }
        }

        Map map = new HashMap();
        map.put("permissions",permissions);
        map.put("permissionNames",permissionNames);
        map.put("permissionUrls",permissionUrls);
        List<Map<String, String>> listMaps = new ArrayList<>();
        listMaps.add(map);

        return listMaps;
    }

    /**
     * 用户添加;
     * @return
     */
    @RequestMapping(value = "/userAdd", method = RequestMethod.POST)
    @RequiresPermissions("userInfo:add")//权限管理;
    public String userInfoAdd(UserInfo userInfo){
        JSONObject jsonObject = new JSONObject();
        try {
            userInfoService.addUserInfo(userInfo);
            jsonObject.put("msg", "用户新增成功");
        }catch (Exception e) {
            jsonObject.put("msg", "用户新增失败："+e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toString();
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