package com.stfc.backend.service;

import java.util.List;

import com.stfc.website.domain.Category;

public interface CategoryService {

    List<Category> getCategory();

    void saveOrUpdate(Category category);
}
