package com.permission.management.core.controller;


import com.permission.management.core.bean.SysPermission;
import com.permission.management.core.bean.SysRole;
import com.permission.management.core.service.SysPermissionService;
import com.permission.management.core.utils.SysRoleUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/permission")
public class SysPermissionController {

    @Resource
    private SysPermissionService sysPermissionService;

    /**
     * 权限查询.
     * @return
     */
    @GetMapping("/permissionList")
    @RequiresPermissions("sysPermission:list")//权限管理;
    public List<JSONObject> getPermissionList(){
        List<JSONObject> results = new ArrayList<>();
        List<SysPermission> permissionList = sysPermissionService.findSysPermissionList();
        for (SysPermission permission: permissionList) {
            JSONObject result = new JSONObject();
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
            JSONObject permissionObject =JSONObject.fromObject(permission, jsonConfig);
            //ID
            result.put("id",permissionObject.get("id"));
            //available
            result.put("available", permissionObject.get("available"));
            //名称
            result.put("name", permissionObject.get("name"));
            //父编号
            result.put("parentId",permissionObject.get("parentid"));
            //父编号列表
            result.put("parentIds",permissionObject.get("parentids"));
            //权限字符串
            result.put("permission",permissionObject.get("permission"));
            //资源类型
            result.put("resourceType",permissionObject.get("resourcetype"));
            //资源路径
            result.put("url",permissionObject.get("url"));

            results.add(result);
        }
        return results;
    }

    /**
     * 权限添加;
     * @return
     */
    @RequestMapping(value = "/permissionAdd", method = RequestMethod.POST)
    @RequiresPermissions("sysPermission:add")//权限管理;
    public String permissionAdd(SysPermission sysPermission){
        JSONObject jsonObject = new JSONObject();
        try {
            sysPermission.setAvailable(true);
            sysPermissionService.addSysPermission(sysPermission);
            jsonObject.put("msg", "权限新增成功");
        }catch (Exception e) {
            jsonObject.put("msg", "权限新增失败："+e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
    /**
     * 权限删除;
     * @return
     */
    @RequestMapping(value = "/permissionDel", method = RequestMethod.DELETE)
    @RequiresPermissions("sysPermission:del")//权限管理;
    public String permissionDel(Long uid){
        JSONObject jsonObject = new JSONObject();
        try {
            sysPermissionService.delSysPermission(uid);
            jsonObject.put("msg", "权限删除成功");
        } catch (Exception e) {
            jsonObject.put("msg", "权限删除失败："+e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
    /**
     * 权限更新;
     * @return
     */
    @RequestMapping(value = "/permissionUpdate", method = RequestMethod.POST)
    @RequiresPermissions("sysPermission:update")//权限管理;
    public String permissionUpdate(SysPermission sysPermission){
        JSONObject jsonObject = new JSONObject();
        try {
            long id = sysPermission.getId();
            if(id == 0) {
                jsonObject.put("msg", "无该ID值的权限");
                return jsonObject.toString();
            }else {
                Optional<SysPermission> sysPermissionOptional = sysPermissionService.findSysPermissionById(sysPermission.getId());




                sysPermissionService.updateSysPermission(id,sysPermission);
                jsonObject.put("msg", "权限更新成功");
            }
        } catch (Exception e) {
            jsonObject.put("msg", "权限更新失败："+e.getMessage());
            e.printStackTrace();
        }



        return jsonObject.toString();
    }

}
