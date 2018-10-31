package com.permission.management.core.repository;

import com.permission.management.core.bean.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * UserInfo持久化类;
 */
public interface UserInfoRepository extends CrudRepository<UserInfo,Long> {

    /**通过username查找用户信息;*/
    UserInfo findByUsername(String username);

}
