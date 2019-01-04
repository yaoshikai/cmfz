package com.baizhi.ysk.controller.serviceimpl;

import com.baizhi.ysk.controller.entity.Menu;
import com.baizhi.ysk.controller.mapper.MenuMapper;
import com.baizhi.ysk.controller.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Menu> queryAll() {
        List<Menu> list = menuMapper.queryAll();
        return list;
    }

}
