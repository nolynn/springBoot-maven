package com.lynn.web.service;

import java.util.Set;

public interface MenuService {
    /**
     * 根据用户id获得该用户的菜单权限
     *
     * @param id
     * @return
     */
    Set<String> getMenuByUId(String id);

    /**
     * 获得超级管理员的菜单权限
     *
     * @return
     */
    Set<String> getMenuByAdmin();

}
