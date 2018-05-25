/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.service.impl;

import com.stfc.website.bean.Banner;
import com.stfc.website.bean.Document;
import com.stfc.website.bean.Post;
import com.stfc.website.bean.UserToken;
import com.stfc.website.dao.WidgetDAO;
import com.stfc.website.domain.Widget;
import com.stfc.website.domain.Class;
import com.stfc.website.bean.WidgetContent;
import com.stfc.website.domain.Category;
import com.stfc.website.domain.EnrollStudent;
import com.stfc.website.domain.Feedback;
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
    public List<Post> getPost(List<Long> lstCategorytId, int isPrivate) {
        return widgetDAO.getPost(lstCategorytId, isPrivate);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Banner> getBanner() {
        return widgetDAO.getBanner();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Post> getPostByCategoryId(Long categorytId, int limitPost, int isPrivate) {
        return widgetDAO.getPostByCategoryId(categorytId, limitPost, isPrivate);
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

    @Transactional(readOnly = true)
    @Override
    public List<Post> getPostByCategorySlug(String categorytSlug, int limitPost, int isPrivate) {
        return widgetDAO.getPostByCategorySlug(categorytSlug, limitPost, isPrivate);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Post> getPostByCategoryIdRelated(Long categorytId, int limitPost, Long postId, int isPrivate) {
        return widgetDAO.getPostByCategoryIdRelated(categorytId, limitPost, postId, isPrivate);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Document> getDocument() {
        return widgetDAO.getDocument();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> getCategoryDocument() {
        return widgetDAO.getCategoryDocument();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Class> getAllClass() {
        return widgetDAO.getAllClass();
    }

    @Transactional
    @Override
    public void insertStudent(EnrollStudent student) {
        widgetDAO.insertStudent(student);
    }

    @Transactional(readOnly = true)
    @Override
    public UserToken getUserByUserName(String userName) {
        return widgetDAO.getUserByUserName(userName);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Document> getDocumentById(Long documentId) {
        return widgetDAO.getDocumentById(documentId);
    }

    @Override
    public List<Document> getDocumentByCategoryId(Long categoryId) {
        return widgetDAO.getDocumentByCategoryId(categoryId);
    }

    @Override
    public List<Document> getClassSchedule() {
        return widgetDAO.getClassSchedule();
    }

    @Override
    public void insertFeedback(Feedback feedback) {
        widgetDAO.insertFeedback(feedback);
    }

}
