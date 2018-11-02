package com.permission.management.core.utils;

import com.permission.management.core.bean.SysPermission;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SysRoleUtil {
    public static void getPermissions(JSONObject result, JSONArray roleListArray) {
        if(roleListArray.size() > 0) {
            //拥有的权限
            List permissionlist = getPermission(roleListArray);
            result.put("permissionlist", permissionlist);
        }
    }
    //获取拥有的权限
    private static List getPermission(JSONArray roleListArray) {
        //权限List
        List permissionList = new ArrayList();
        //权限名称List
        List permissionNames = new ArrayList();
        //权限路径List
        List permissionUrls = new ArrayList();
        //权限路径List
        List permissionId = new ArrayList();
        //权限路径List
        List permissionIds = new ArrayList();

        //遍历的角色List
        for (int i = 0; i < roleListArray.size(); i++) {
            //转化为JSONObject
            JSONObject roleJson = (JSONObject) roleListArray.get(i);
            //转为SysPermission实体
            SysPermission permissionJsonDate = (SysPermission) JSONObject.toBean(roleJson, SysPermission.class);
            //获取实体中权限数据
            permissionList.add(permissionJsonDate.getPermission());
            //获取实体中权限名称
            permissionNames.add(permissionJsonDate.getName());
            //获取实体中权限路径
            permissionUrls.add(permissionJsonDate.getUrl());
            //获取实体中parentId
            permissionId.add(permissionJsonDate.getParentid());
            permissionIds.add(permissionJsonDate.getParentids());
        }
        Map map = new HashMap();
        map.put("permissionList",permissionList);
        map.put("permissionNames",permissionNames);
        map.put("permissionUrls",permissionUrls);
        map.put("permissionId",permissionId);
        map.put("permissionIds",permissionIds);
        List<Map<String, String>> listMaps = new ArrayList<>();
        listMaps.add(map);


        return listMaps;
    }

}
