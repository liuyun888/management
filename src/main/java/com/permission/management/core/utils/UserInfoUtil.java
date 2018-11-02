package com.permission.management.core.utils;

import com.permission.management.core.bean.SysPermission;
import com.permission.management.core.bean.SysRole;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInfoUtil {


    //获取userinfo中roleList信息
    public static void getRoleListInfo(JSONObject result, JSONArray roleListArray) {
        if(roleListArray.size() > 0) {
            //拥有的角色
            List rolelist = getRoles(roleListArray);
            result.put("roleList", rolelist);
            //拥有的权限
            List permissionlist = getPermission(roleListArray);
            result.put("permissionlist", permissionlist);
        }
    }
    //获取拥有的角色
    private static List getRoles(JSONArray roleListArray) {
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
    private static List getPermission(JSONArray roleListArray) {
        //权限List
        List permissions = new ArrayList();
        //权限名称List
        List permissionNames = new ArrayList();
        //权限路径List
        List permissionUrls = new ArrayList();
        //权限路径List
        List permissionId = new ArrayList();
        //权限路径List
        List permissionIds = new ArrayList();

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
                    //获取实体中parentId
                    permissionId.add(permissionJsonData.getParentid());
                    permissionIds.add(permissionJsonData.getParentids());
                }
            }
        }

        Map map = new HashMap();
        map.put("permissions",permissions);
        map.put("permissionNames",permissionNames);
        map.put("permissionUrls",permissionUrls);
        map.put("permissionId",permissionId);
        map.put("permissionIds",permissionIds);
        List<Map<String, String>> listMaps = new ArrayList<>();
        listMaps.add(map);

        return listMaps;
    }

}
