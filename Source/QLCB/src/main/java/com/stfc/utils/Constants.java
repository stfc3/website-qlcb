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
    String TOKEN = "token";

    //Page
    String PAGE_HOME = "/index.zul";
    String PAGE_LOGIN = "/login.zul";

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

    public static final String PARAM_KEY_CATEGORY_NOTICE_BANNER = "CATEGORY_NOTICE_BANNER";
    public static final int MAX_POST_WIDGET_TYPE_MULTI = 5;
    public static final String PARAM_KEY_CATEGORY_WIDGET_POST_DETAIL_1 = "CATEGORY_WIDGET_POST_DETAIL_1";

    public static final int MAX_POST_WIDGET_POST_DETAIL = 5;

    String STFC_URL_ATTRIBUTE = "stfc-url";
    String PAGE_CATEGORY = "manager/category.zul";
    String PAGE_POST = "manager/post.zul";
    String PAGE_USER = "manager/user.zul";
    String PAGE_WIDGET = "manager/widget.zul";
    String PAGE_MENU = "manager/menu.zul";
    String PAGE_DOCUMENT = "manager/document.zul";
    String PAGE_BANNER = "manager/banner.zul";

    public static final String WIDGET_POSITION_INTERNAL = "INTERNAL_CONTENT";
    public static final String WIDGET_CONTENT_TYPE_CATEGORY_INTERNAL = "CATEGORY_INTERNAL";
    public static final String HOME_PAGE_URL_IMAGE = "HOME_PAGE_URL_IMAGE";
    public static final String INTERNAL_PAGE_URL_IMAGE = "INTERNAL_PAGE_URL_IMAGE";
    
    public static final int POST_IS_PUBLIC = 0;
    public static final int POST_IS_PRIVATE = 1;
    public static final int POST_IS_PUBLIC_AND_PRIVATE = 2;

}
