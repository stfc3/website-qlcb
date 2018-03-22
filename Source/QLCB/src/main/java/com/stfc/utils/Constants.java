/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.utils;

/**
 *
 * @author dmin
 */
public interface Constants {
    
    //Session
    String TOKEN="token"; 
    
    //Page
    String PAGE_HOME="/index.zul";
    String PAGE_LOGIN="/login.zul";
    
    public static final String WIDGET_TYPE_HOTNEWS = "1";
    public static final String WIDGET_TYPE_NEWS_EVENT = "2";
    public static final String WIDGET_TYPE_MULTI = "3";
    public static final String WIDGET_TYPE_FOOTER = "5";
    
    //1: Type Category, 2: Type Text
    public static final String WIDGET_CONTENT_TYPE_CATEGORY = "CATEGORY";
    public static final String WIDGET_CONTENT_TYPE_TEXT = "TEXT";
    
    //Position widget
    public static final String WIDGET_POSITION_CONTENT = "CONTENT";
    public static final String WIDGET_POSITION_FOOTER = "FOOTER";
    String PREFIX_POST = "post/";
    String PREFIX_CATEGORY = "category/";
    
    public static final String KEY_CATEGORY_NOTICE_BANNER = "CATEGORY_NOTICE_BANNER";
    
}
