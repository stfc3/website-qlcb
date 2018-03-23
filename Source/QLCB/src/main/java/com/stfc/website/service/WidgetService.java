/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.service;

import com.stfc.website.bean.Banner;
import com.stfc.website.bean.Post;
import com.stfc.website.domain.Widget;
import com.stfc.website.bean.WidgetContent;
import com.stfc.website.domain.Category;
import com.stfc.website.domain.Param;
import java.util.List;

/**
 *
 * @author dmin
 */
public interface WidgetService {

    List<Widget> getAllWidget();
    List<WidgetContent> getAllWidgetContent(List<Long> lstWidgetId);
    List<Post> getPost(List<Long> lstCategorytId);
    List<Banner> getBanner();
    List<Post> getPostByCategoryId(Long categorytId, int limitPost);
    List<Post> getPostBySlug(String postSlug);
    List<Param> getAllParam();
    List<Category> getAllCategory();
}
