/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.service.impl;

import com.stfc.website.bean.Banner;
import com.stfc.website.bean.Post;
import com.stfc.website.dao.WidgetDAO;
import com.stfc.website.domain.Widget;
import com.stfc.website.bean.WidgetContent;
import com.stfc.website.domain.Category;
import com.stfc.website.domain.Param;
import com.stfc.website.service.WidgetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dmin
 */
@Service
public class WidgetServiceImpl implements WidgetService {

    @Autowired
    private WidgetDAO widgetDAO;


    @Transactional(readOnly = true)
    @Override
    public List<Widget> getAllWidget() {
        return widgetDAO.getAllWidget();
    }

    @Transactional(readOnly = true)
    @Override
    public List<WidgetContent> getAllWidgetContent(List<Long> lstWidgetId) {
        return widgetDAO.getAllWidgetContent(lstWidgetId);
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Post> getPost(List<Long> lstCategorytId) {
        return widgetDAO.getPost(lstCategorytId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Banner> getBanner() {
        return widgetDAO.getBanner();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Post> getPostByCategoryId(Long categorytId, int limitPost) {
        return widgetDAO.getPostByCategoryId(categorytId, limitPost);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Param> getAllParam() {
        return widgetDAO.getAllParam();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> getAllCategory() {
        return widgetDAO.getAllCategory();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Post> getPostBySlug(String postSlug) {
        return widgetDAO.getPostBySlug(postSlug);
    }
    
}
