package com.lynn.web.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @Description:
 * @Date: 2019/4/11 18:33
 * @Auther: lynn
 */
public interface MenuMapper {
    /**
     * 根据用户id获得该用户的菜单权限
     *
     * @param id
     * @return
     */
    Set<String> getMenuByUid(@Param("id") String id);

    /**
     * 获得超级管理员的菜单权限
     *
     * @return
     */
    Set<String> getMenuByAdmin();
}
