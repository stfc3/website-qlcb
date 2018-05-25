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

    //Page
    String PAGE_LOGIN = "/login";
    String BACKEND_PAGE_HOME = "/admin";

    public static final String WIDGET_TYPE_HOTNEWS = "1";
    public static final String WIDGET_TYPE_NEWS_EVENT = "2";
    public static final String WIDGET_TYPE_MULTI = "3";
    public static final String WIDGET_TYPE_FOOTER = "5";

    //1: Type Category, 2: Type Text
    public static final String WIDGET_CONTENT_TYPE_CATEGORY = "CATEGORY";
    public static final String WIDGET_CONTENT_TYPE_TEXT = "TEXT";

    //Position widget
    public static final String WIDGET_POSITION_CONTENT = "CONTENT";
    public static final String WIDGET_POSITION_RIGHT_CONTENT = "RIGHT_CONTENT";
    public static final String WIDGET_POSITION_FOOTER = "FOOTER";

    public static final String PARAM_KEY_CATEGORY_NOTICE_BANNER = "CATEGORY_NOTICE_BANNER";
    public static final int MAX_POST_WIDGET_TYPE_MULTI = 5;
    public static final String PARAM_KEY_CATEGORY_WIDGET_POST_DETAIL_1 = "CATEGORY_WIDGET_POST_DETAIL_1";

    public static final int MAX_POST_WIDGET_POST_DETAIL = 5;

    String STFC_URL_ATTRIBUTE = "stfc-url";
    String STFC_ID_ATTRIBUTE = "stfc-id";
    String PAGE_CATEGORY = "manager/category.zul";
    String PAGE_ADD_POST = "manager/addEditPost.zul";
    String PAGE_LIST_POST = "manager/post.zul";
    String PAGE_USER = "manager/user.zul";
    String PAGE_WIDGET = "manager/widget.zul";
    String PAGE_MENU = "manager/menu.zul";
    String PAGE_DOCUMENT = "manager/document.zul";
    String PAGE_BANNER = "manager/banner.zul";
    String PAGE_ENROLL = "manager/enroll.zul";
    String PAGE_FEEDBACK = "manager/feedback.zul";

    public static final String WIDGET_POSITION_INTERNAL = "INTERNAL_CONTENT";
    public static final String WIDGET_CONTENT_TYPE_CATEGORY_INTERNAL = "CATEGORY_INTERNAL";
    public static final String HOME_PAGE_URL_IMAGE = "HOME_PAGE_URL_IMAGE";
    public static final String INTERNAL_PAGE_URL_IMAGE = "INTERNAL_PAGE_URL_IMAGE";
    public static final String DOCUMENT_PAGE_URL_IMAGE = "DOCUMENT_PAGE_URL_IMAGE";

    public static final int POST_IS_PUBLIC = 0;
    public static final int POST_IS_PRIVATE = 1;
    public static final int POST_IS_PUBLIC_AND_PRIVATE = 2;
    public static final String HOME_NEWS_ADVERT_URL_IMAGE = "HOME_NEWS_ADVERT_URL_IMAGE";
    public static final String ENROLL_STUDENTS_URL_IMAGE = "ENROLL_STUDENTS_URL_IMAGE";

    Integer STATUS_ACTIVE = 1;
    Integer STATUS_INACTIVE = 0;

    String MESSAGE_POSTION_END_CENTER = "end_center";
    Integer MESSAGE_TIME_CLOSE = 3000;
    String PREFIX_SLUG_CATEGORY = "/category/";
    String PREFIX_SLUG_POST = "/post/";

    String USER_TOKEN = "userToken";

    Integer ROLE_ADMIN = 1;
    Integer ROLE_EDITOR = 2;
    Integer ROLE_AUTHOR = 3;
    Integer ROLE_CONTRIBUTOR = 4;
    
    public static final String CATEGORY_LEGISLATION = "CATEGORY_LEGISLATION";
    public static final String PREFIX_SLUG_DOCUMENT = "/doc/";
	
	public static final String KEY_COMPATE_NEW_POST = "KEY_COMPATE_NEW_POST";

}
