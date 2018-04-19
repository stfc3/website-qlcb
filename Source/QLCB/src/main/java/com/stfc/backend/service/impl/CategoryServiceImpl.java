package com.stfc.backend.service.impl;

import com.stfc.backend.dao.CategoryDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stfc.backend.dao.MenuDAO;
import com.stfc.backend.domain.Menu;
import com.stfc.backend.service.CategoryService;
import com.stfc.website.domain.Category;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = Logger.getLogger(CategoryServiceImpl.class);
    @Autowired
    CategoryDAO categoryDAO;

    @Transactional(readOnly = true)
    @Override
    public  List<Category> getCategory() {
        return categoryDAO.getCategory();
    }

    @Transactional
    @Override
    public void saveOrUpdate(Category category) {
        categoryDAO.saveOrUpdate(category);
    }

}
