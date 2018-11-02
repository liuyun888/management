package com.permission.management.core.controller;

import com.permission.management.core.bean.UserInfo;
import com.permission.management.core.service.UserInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.permission.management.core.utils.UserInfoUtil.getRoleListInfo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/userInfo")
public class UserInfoController {

    //随机数生成器
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    //指定散列算法为md5
    private String algorithmName = "MD5";
    //散列迭代次数
    private final int hashIterations = 2;

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
        JSONObject userObject = userInfoService.findByUsername(username);
        //ID
        result.put("uid",userObject.get("uid"));
        //账号
        result.put("username", userObject.get("username"));
        //名称（昵称或者真实姓名）
        result.put("name", userObject.get("name"));
        /**把JSONArray 转换为List对象,然后操作List*/
        //将userInfo中的角色列表信息转换为JSONArray
        JSONArray roleListArray = (JSONArray) userObject.get("roleList");
        //权限在角色数据中
        getRoleListInfo(result, roleListArray);
        return result;
    }

    /**
     * 查询用户信息列表
     * @return
     */
    @GetMapping("/userList")
    @RequiresPermissions("userInfo:list")//权限管理;
    public List<JSONObject> getUserInfoList(){
        List<JSONObject> results = new ArrayList<>();
        List<UserInfo> userInfoList = userInfoService.findUserInfoList();

        for (UserInfo userinfo: userInfoList) {
            JSONObject result = new JSONObject();
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
            JSONObject userObject =JSONObject.fromObject(userinfo, jsonConfig);
            //ID
            result.put("uid",userObject.get("uid"));
            //账号
            result.put("username", userObject.get("username"));
            //名称（昵称或者真实姓名）
            result.put("name", userObject.get("name"));
            /**把JSONArray 转换为List对象,然后操作List*/
            //将userInfo中的角色列表信息转换为JSONArray
            JSONArray roleListArray = (JSONArray) userObject.get("roleList");
            //权限在角色数据中
           getRoleListInfo(result, roleListArray);
            results.add(result);
        }
        return results;
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
            //生成salt
            userInfo.setSalt(randomNumberGenerator.nextBytes().toHex());
            //生成加密密码
            String newPassword =
                    new SimpleHash(algorithmName,userInfo.getPassword(),
                            ByteSource.Util.bytes(userInfo.getCredentialsSalt()),hashIterations).toHex();
            userInfo.setPassword(newPassword);

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
    @RequestMapping(value = "/userDel", method = RequestMethod.DELETE)
    @RequiresPermissions("userInfo:del")//权限管理;
    public String userDel(Long uid){
        JSONObject jsonObject = new JSONObject();
        try {
            userInfoService.delUserInfo(uid);
            jsonObject.put("msg", "用户删除成功");
        } catch (Exception e) {
            jsonObject.put("msg", "用户删除失败："+e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 设置用户角色
     * @return
     */
    @RequestMapping(value = "/setUserRole", method = RequestMethod.POST)
    @RequiresPermissions("userInfo:setRole")//权限管理;
    public String setUserRole(@RequestParam("uid") long uid ,@RequestParam("roleId") long roleId ){
        JSONObject jsonObject = new JSONObject();
        try {
            int count = userInfoService.getUserRole(uid,roleId);
            if(count == 0) {
                userInfoService.setUserRole(uid, roleId);
                jsonObject.put("msg", "用户角色设置成功");
            }else {
                jsonObject.put("msg", "用户已用于该角色");
            }
        } catch (Exception e) {
            jsonObject.put("msg", "用户角色设置失败："+e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
    /**
     * 批量设置用户角色
     * TODO 可以重复插入
     * @return
     */
    @RequestMapping(value = "/setUserRoles", method = RequestMethod.POST)
    @RequiresPermissions("userInfo:setRole")//权限管理;
    public String setUserRoles(@RequestBody List<Long> uIds ,@RequestBody List<Long> roleIds ){
        JSONObject jsonObject = new JSONObject();
        try {
            for (Long uid:uIds) {
                for (Long roleId: roleIds) {

                    int count = userInfoService.getUserRole(uid,roleId);
                    if(count!=0) continue;
                    userInfoService.setUserRole(uid,roleId);
                }
            }
            jsonObject.put("msg", "用户角色设置成功");
        } catch (Exception e) {
            jsonObject.put("msg", "用户角色设置失败："+e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }
}
