package com.stfc.backend.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stfc.backend.dao.MenuDAO;
import com.stfc.backend.domain.Menu;
import com.stfc.backend.service.MenuService;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuServiceImpl implements MenuService {

    private static final Logger logger = Logger.getLogger(MenuServiceImpl.class);
    @Autowired
    MenuDAO menuDAO;

    @Transactional(readOnly = true)
    @Override
    public List<Menu> getMenuByType(Integer type) {
        return menuDAO.getMenuByType(type);
    }

    @Transactional
    @Override
    public void saveOrUpdate(Menu menu) {
        menuDAO.saveOrUpdate(menu);
    }

}
