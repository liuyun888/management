package com.permission.management.core.repository;

import net.sf.json.JSONObject;
import com.permission.management.core.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserInfo持久化类;
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

    /**通过username查找用户信息;*/
    UserInfo findByUsername(String username);



}
