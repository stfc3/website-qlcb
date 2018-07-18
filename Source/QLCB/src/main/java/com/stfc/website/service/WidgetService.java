/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.service;

import com.stfc.website.bean.Banner;
import com.stfc.website.bean.Document;
import com.stfc.website.bean.Post;
import com.stfc.website.bean.UserToken;
import com.stfc.website.domain.Widget;
import com.stfc.website.bean.WidgetContent;
import com.stfc.website.domain.Category;
import com.stfc.website.domain.Param;
import java.util.List;
import com.stfc.website.domain.Class;
import com.stfc.website.domain.EnrollStudent;
import com.stfc.website.domain.Feedback;

/**
 *
 * @author dmin
 */
public interface WidgetService {

    List<Widget> getAllWidget();

    List<WidgetContent> getAllWidgetContent(List<Long> lstWidgetId);

    List<Post> getPost(List<Long> lstCategorytId, int isPrivate);

    List<Banner> getBanner();

    List<Post> getPostByCategoryId(Long categorytId, int limitPost, int isPrivate);

    List<Post> getPostBySlug(String postSlug);

    List<Param> getAllParam();

    List<Category> getAllCategory();

    List<Post> getPostByCategorySlug(String categorytSlug, int limitPost, int isPrivate);

    List<Post> getPostByCategoryIdRelated(Long categorytId, int limitPost, Long postId, int isPrivate);

    List<Document> getDocument();

    List<Category> getCategoryDocument();

    List<Class> getAllClass();

    void insertStudent(EnrollStudent student);

    UserToken getUserByUserName(String userName);
    
    List<Document> getDocumentById(Long documentId);
    
    List<Document> getDocumentByCategoryId(Long categoryId);
    
    List<Document> getClassSchedule();
    
    void insertFeedback(Feedback feedback);
    
    List<Document> getScheduleWeek();
    
    void changePassword(UserToken user);
}
