package com.lynn.web.service.impl;

import com.lynn.web.mapper.MenuMapper;
import com.lynn.web.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @Description:
 * @Date: 2019/4/11 18:26
 * @Auther: lynn
 */
@Transactional
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper mapper;

    @Override
    public Set<String> getMenuByUId(String id) {
        return mapper.getMenuByUid(id);
    }

    @Override
    public Set<String> getMenuByAdmin() {
        return mapper.getMenuByAdmin();
    }
}
