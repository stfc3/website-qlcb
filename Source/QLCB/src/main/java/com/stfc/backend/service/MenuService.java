package com.stfc.backend.service;

import java.util.List;

import com.stfc.backend.domain.Menu;

public interface MenuService {

    List<Menu> getMenuByType(Integer type);

    void saveOrUpdate(Menu menu);
}
