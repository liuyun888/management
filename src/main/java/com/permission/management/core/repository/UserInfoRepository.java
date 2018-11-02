package com.permission.management.core.repository;

import net.sf.json.JSONObject;
import com.permission.management.core.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserInfo持久化类;
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

    /**通过username查找用户信息;*/
    UserInfo findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value="insert into Sys_User_Role(uid,roleid) values(?,?)",nativeQuery=true)
    int setUserRole(Long uid,Long roleid);

    @Query(value="select count(*) from  Sys_User_Role where uid = ? and roleid = ?",nativeQuery=true)
    int getUserRole(Long uid,Long roleid);

}
