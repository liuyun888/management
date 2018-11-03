package com.permission.management.core.controller;

import com.permission.management.core.bean.SysPermission;
import com.permission.management.core.bean.SysRole;
import com.permission.management.core.service.SysRoleService;
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
@RequestMapping(value = "/role")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;
    /**
     * 角色查询.
     * @return
     */
    @GetMapping("/roleList")
    @RequiresPermissions("sysRole:list")//权限管理;
    public List<JSONObject> getRoleList(){
        List<JSONObject> results = new ArrayList<>();
        List<SysRole> roleList = sysRoleService.findRoles();
        for (SysRole role: roleList) {
            JSONObject result = new JSONObject();
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
            JSONObject roleObject =JSONObject.fromObject(role, jsonConfig);
            //ID
            result.put("id",roleObject.get("id"));
            //available
            result.put("available", roleObject.get("available"));
            //角色描述
            result.put("description", roleObject.get("description"));
            //角色
            result.put("role",roleObject.get("role"));
            /**把JSONArray 转换为List对象,然后操作List*/
            //将userInfo中的角色列表信息转换为JSONArray
            JSONArray roleListArray = (JSONArray) roleObject.get("permissions");
            //获取权限列表
           SysRoleUtil.getPermissions(result, roleListArray);
           results.add(result);
        }
        return results;
    }

    /**
     * 角色添加;
     * @return
     */
    @RequestMapping(value = "/roleAdd", method = RequestMethod.POST)
    @RequiresPermissions("sysRole:add")//权限管理;
    public String roleAdd(SysRole sysRole){
        JSONObject jsonObject = new JSONObject();
        try {
            SysRole sysRoleForCheck = sysRoleService.getByRole(sysRole.getRole());
            if(sysRoleForCheck != null){ jsonObject.put("msg", "该角色已存在");return jsonObject.toString();}
            sysRole.setAvailable(true);
            sysRoleService.addSysRole(sysRole);
            jsonObject.put("msg", "角色新增成功");
        }catch (Exception e) {
            jsonObject.put("msg", "角色新增失败："+e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
    /**
     * 角色删除;
     * @return
     */
    @RequestMapping(value = "/roleDel", method = RequestMethod.DELETE)
    @RequiresPermissions("sysRole:del")//权限管理;
    public String roleDel(Long id){
        JSONObject jsonObject = new JSONObject();
        try {
            sysRoleService.delSysRole(id);
            jsonObject.put("msg", "角色删除成功");
        } catch (Exception e) {
            jsonObject.put("msg", "角色删除失败："+e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 设置角色权限
     * @return
     */
    @RequestMapping(value = "/setRolePermission", method = RequestMethod.POST)
    @RequiresPermissions("sysRole:setPermission")//权限管理;
    public String setUserRole(@RequestParam("roleId") long roleId ,@RequestParam("permissionId") long permissionId ){
        JSONObject jsonObject = new JSONObject();
        try {
            int count = sysRoleService.getRolePermission(roleId,permissionId);
            if(count == 0) {
                sysRoleService.setRolePermission(roleId,permissionId);
                jsonObject.put("msg", "角色权限设置成功");
            }else {
                jsonObject.put("msg", "角色已用于该权限");
            }
        } catch (Exception e) {
            jsonObject.put("msg", "角色权限设置失败："+e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    /**
     * 角色更新;
     * @return
     */
    @RequestMapping(value = "/roleUpdate", method = RequestMethod.POST)
    @RequiresPermissions("sysRole:update")//权限管理;
    public String permissionUpdate(SysRole sysRole){
        JSONObject jsonObject = new JSONObject();
        try {
            long id = sysRole.getId();
            if(id == 0) {
                jsonObject.put("msg", "请选择要修改的角色");
                return jsonObject.toString();
            }else {
                SysRole sysRole1ForCheck = sysRoleService.getByRole(sysRole.getRole());
                if(sysRole1ForCheck != null){ jsonObject.put("msg", "该角色已存在");return jsonObject.toString();}
                //Return default value if Optional is empty
                Optional<SysRole> sysRoleOptional = sysRoleService.findSysRoleById(sysRole.getId());
                //id
                sysRole.setId(sysRole.getId());
                //description
                sysRole.setDescription(Optional.ofNullable(sysRole.getDescription()).orElse(sysRoleOptional.get().getDescription()));
                //available
                sysRole.setAvailable(Optional.ofNullable(sysRole.getAvailable()).orElse(sysRoleOptional.get().getAvailable()));
                //role
                sysRole.setRole(Optional.ofNullable(sysRole.getRole()).orElse(sysRoleOptional.get().getRole()));

                sysRoleService.updateSysRole(sysRole);
                jsonObject.put("msg", "权限更新成功");
            }
        } catch (Exception e) {
            jsonObject.put("msg", "权限更新失败："+e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toString();
    }





}
