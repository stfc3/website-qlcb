/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website;

import com.stfc.utils.Constants;
import com.stfc.utils.SpringConstant;
import com.stfc.website.domain.Widget;
import com.stfc.website.service.WidgetService;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.zkoss.zhtml.H2;
import org.zkoss.zhtml.H4;
import org.zkoss.zhtml.P;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.A;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Span;

/**
 *
 * @author dmin
 */
public class IndexController extends SelectorComposer<Div> {
    
    private static final Logger logger = Logger.getLogger(IndexController.class);
    
    private Session session;
    
    @Wire
    Div indexSlider;
    
    @Wire
    Div addWidgetIndex;
    
    @WireVariable
    protected WidgetService widgetService;
    
    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp);
        session = Sessions.getCurrent();
        if (session.getAttribute(Constants.TOKEN) != null) {
            Executions.sendRedirect(Constants.PAGE_HOME);
        }
        
        widgetService = (WidgetService) SpringUtil.getBean(SpringConstant.WIDGET_SERVICE);
        List<Widget> vlstWidget = widgetService.getAllWidget();
        
        buildSlider();
        
        buildWidget(vlstWidget);
        buildFooter(3);
        
    }
    
    private void buildWidget(List<Widget> plstWidget) {
        if (plstWidget != null && !plstWidget.isEmpty()) {
            List<Long> lstWidgetId = new ArrayList<>();
            for (Widget wg : plstWidget) {
                lstWidgetId.add(wg.getWidgetId());
            }
            
            List<Widget> vlstWidget = widgetService.getAllWidget();
            // lay widgetContent
            for (Widget wg : plstWidget) {
                if (Constants.WIDGET_TYPE_HOTNEWS.equals(wg.getWidgetType())) {
                    buildWidgetHotNews(wg);
                } else if (Constants.WIDGET_TYPE_NEWS_EVENT.equals(wg.getWidgetType())) {
                    buildWidgetNewsPost(wg);
                } else if (Constants.WIDGET_TYPE_MULTI.equals(wg.getWidgetType())) {
                    buildMultiCategory(wg);
                }
            }
        }
    }
    
    private void buildSlider() {
        Div slider;
        Image img;
        
        for (int i = 1; i <= 3; i++) {
            slider = new Div();
            if (i == 1) {
                slider.setClass("item slides active");
            } else {
                slider.setClass("item slides");
            }
            slider.setParent(indexSlider);
            img = new Image();
            String src = "media/1/" + i + ".jpg";
            img.setSrc(src);
            img.setParent(slider);
        }
    }

    /*
    /*Build HOT NEWS
     */
    private void buildWidgetHotNews(Widget wg) {
        Div hotNewMain = new Div();
        hotNewMain.setClass("irs-blog-field");
        hotNewMain.setParent(addWidgetIndex);
        
        Div container = new Div();
        container.setClass("container");
        container.setParent(hotNewMain);
        
        Div rowTitle = new Div();
        rowTitle.setClass("row border-bottom-title-category");
        rowTitle.setParent(container);
        
        Div col_md_8 = new Div();
        col_md_8.setClass("col-md-8");
        col_md_8.setParent(rowTitle);
        
        Div titleCategory = new Div();
        titleCategory.setClass("title-category");
        titleCategory.setParent(col_md_8);
        
        H2 h2Title = new H2();
        h2Title.setParent(titleCategory);
        
        A hotnew = new A();
        hotnew.setParent(h2Title);
        
        Span spanTitle = new Span();
        spanTitle.setParent(hotnew);
        String widgetTitle = "";
        if (wg.getWidgetName() != null || !"".equals(wg.getWidgetName())) {
            widgetTitle = wg.getWidgetName();
        }
        Label lblFunctionName = new Label(widgetTitle);
        lblFunctionName.setParent(spanTitle);

        //Content
        Div rowContent = new Div();
        rowContent.setClass("row animatedParent animateOnce");
        rowContent.setParent(container);

        //Bai viet
        for (int i = 0; i < 4; i++) {
            Div resContent = new Div();
            resContent.setClass("col-md-3 col-sm-5");
            resContent.setParent(rowContent);
            
            Div col = new Div();
            col.setClass("irs-courses-col animated fadeInLeftShort slow delay-250");
            col.setParent(resContent);
            
            Div divImg = new Div();
            divImg.setClass("irs-courses-img index-img");
            divImg.setParent(col);
            
            A linkImg = new A();
            linkImg.setHref("https://www.google.com.vn/");
            linkImg.setParent(divImg);
            
            Image img = new Image();
            String src = "media/tinhot/1/1.jpg";
            img.setSrc(src);
            img.setParent(linkImg);
            
            Div divTime = new Div();
            divTime.setClass("irs-courses-img-text");
            divTime.setParent(divImg);
            
            Div divTimeClearfix = new Div();
            divTimeClearfix.setClass("clearfix");
            divTimeClearfix.setParent(divTime);
            
            Span spanTime = new Span();
            spanTime.setParent(divTimeClearfix);
            
            Label lblTime = new Label("Thứ Ba, 09:23 27/02/2018 ");
            lblTime.setClass("zmdi-calendar-alt");
            lblTime.setParent(spanTime);
            
            Div divTitle = new Div();
            divTitle.setClass("irs-courses-content");
            divTitle.setParent(col);
            
            A linkTitle = new A();
            linkTitle.setHref("https://www.google.com.vn/");
            linkTitle.setParent(divTitle);
            
            Label lblTitle = new Label("Gặp mặt đầu xuân Mậu Tuất 2018 Trường cán bộ quản lý Giao thông vận tải");
            lblTitle.setParent(linkTitle);
            
        }

        //Buton Xem them
        Div divReadMore = new Div();//
        divReadMore.setClass("col-md-12");
        divReadMore.setParent(rowContent);
        
        A linkReadMore = new A();
        linkReadMore.setClass("btn btn-default irs-btn-transparent-two btn-read-more");
        linkReadMore.setHref("https://www.google.com.vn/");
        linkReadMore.setParent(divReadMore);
        
        Label lblTitle = new Label("Xem thêm");
        lblTitle.setParent(linkReadMore);
    }
    
    private void buildWidgetNewsPost(Widget wg) {
        Div hotNewMain = new Div();
        hotNewMain.setClass("irs-blog-field irs-blog-single-field");
        hotNewMain.setParent(addWidgetIndex);
        
        Div container = new Div();
        container.setClass("container");
        container.setParent(hotNewMain);
        
        Div rowTitle = new Div();
        rowTitle.setClass("row border-bottom-title-category");
        rowTitle.setParent(container);
        
        Div col_md_8 = new Div();
        col_md_8.setClass("col-md-8");
        col_md_8.setParent(rowTitle);
        
        Div titleCategory = new Div();
        titleCategory.setClass("title-category");
        titleCategory.setParent(col_md_8);
        
        H2 h2Title = new H2();
        h2Title.setParent(titleCategory);
        
        A hotnew = new A();
        hotnew.setParent(h2Title);
        
        Span spanTitle = new Span();
        spanTitle.setParent(hotnew);
        String widgetTitle = "";
        if (wg.getWidgetName() != null || !"".equals(wg.getWidgetName())) {
            widgetTitle = wg.getWidgetName();
        }
        Label lblFunctionName = new Label(widgetTitle);
        lblFunctionName.setParent(spanTitle);

        //build content
        Div divRow = new Div();
        divRow.setClass("row");
        divRow.setParent(container);

        //build primary post
        Div divPrimaryPost = new Div();
        divPrimaryPost.setClass("col-md-7");
        divPrimaryPost.setParent(divRow);
        
        Div divPrimaryCol = new Div();
        divPrimaryCol.setClass("irs-blog-single-col");
        divPrimaryCol.setParent(divPrimaryPost);
        
        Div divPrimaryColBlog = new Div();
        divPrimaryColBlog.setClass("irs-blog-col");
        divPrimaryColBlog.setParent(divPrimaryCol);
        
        Div divPrimaryImg = new Div();
        divPrimaryImg.setClass("irs-courses-img");
        divPrimaryImg.setParent(divPrimaryColBlog);
        
        A linkImg = new A();
        linkImg.setHref("https://www.google.com.vn/");
        linkImg.setParent(divPrimaryImg);
        
        Image img = new Image();
        String src = "media/tinhot/1/1.jpg";
        img.setSrc(src);
        img.setParent(linkImg);
        
        Div divPrimaryTime = new Div();
        divPrimaryTime.setClass("irs-info-text");
        divPrimaryTime.setParent(divPrimaryColBlog);
        
        Div divTimeClearfix = new Div();
        divTimeClearfix.setClass("clearfix");
        divTimeClearfix.setParent(divPrimaryTime);
        
        Span spanTime = new Span();
        spanTime.setParent(divTimeClearfix);
        
        Label lblPrimaryTime = new Label("Thứ Ba, 09:23 27/02/2018");
        lblPrimaryTime.setClass("icofont-clock-time");
        lblPrimaryTime.setParent(spanTime);
        
        Div divPrimaryTitle = new Div();
        divPrimaryTitle.setClass("irs-courses-content");
        divPrimaryTitle.setParent(divPrimaryColBlog);
        
        A aPrimaryLinkTitle = new A();
        aPrimaryLinkTitle.setHref("https://www.google.com.vn/");
        aPrimaryLinkTitle.setParent(divPrimaryTitle);
        
        Label lblPrimaryTitle = new Label("Gặp mặt đầu xuân Mậu Tuất 2018 Trường cán bộ quản lý Giao thông vận tải");
        lblPrimaryTitle.setParent(aPrimaryLinkTitle);

        //build Post
        Div divPost = new Div();
        divPost.setClass("col-md-5 col-sm-12");
        divPost.setParent(divRow);
        
        Div divPostCol = new Div();
        divPostCol.setClass("irs-blog-single-col");
        divPostCol.setParent(divPost);
        
        Div divPostSide = new Div();
        divPostSide.setClass("irs-side-bar");
        divPostSide.setParent(divPostCol);
        
        Div irsPost = new Div();
        irsPost.setClass("irs-post");
        irsPost.setParent(divPostSide);
        
        for (int i = 0; i < 5; i++) {
            Div divPostItem = new Div();
            divPostItem.setClass("irs-post-item post-item-padding");
            divPostItem.setParent(irsPost);
            
            A linkPostItem = new A();
            linkPostItem.setHref("https://www.google.com.vn/");
            linkPostItem.setParent(divPostItem);
            
            Image imgPostItem = new Image();
            String srcPostItem = "media/tintuc_sukien/2/1.jpg";
            imgPostItem.setSrc(srcPostItem);
            imgPostItem.setParent(linkPostItem);
            
            A aPostItemTitle = new A();
            aPostItemTitle.setHref("https://www.google.com.vn/");
            aPostItemTitle.setParent(divPostItem);
            
            Label lblPostTitle = new Label("Gặp mặt đầu xuân Mậu Tuất 2018 Trường cán bộ quản lý Giao thông vận tải");
            lblPostTitle.setClass("post-title");
            lblPostTitle.setParent(aPostItemTitle);
            
            P spanPostTime = new P();
            spanPostTime.setParent(divPostItem);
            
            Label lblPostItemTime = new Label("Thứ Ba, 09:23 27/02/2018 ");
            lblPostItemTime.setClass("time-post");
            lblPostItemTime.setParent(spanPostTime);
        }
        
        A linkReadMore = new A();
        linkReadMore.setClass("btn btn-default irs-btn-transparent-two btn-read-more");
        linkReadMore.setHref("https://www.google.com.vn/");
        linkReadMore.setParent(irsPost);
        
        Label lblTitle = new Label("Xem thêm");
        lblTitle.setParent(linkReadMore);
    }
    
    private void buildMultiCategory(Widget wg) {
        Div hotNewMain = new Div();
        hotNewMain.setClass("irs-blog-field irs-blog-single-field");
        hotNewMain.setParent(addWidgetIndex);
        
        Div container = new Div();
        container.setClass("container");
        container.setParent(hotNewMain);
        
        Div rowTitle = new Div();
        rowTitle.setClass("row border-bottom-title-category");
        rowTitle.setParent(container);
        
        Div col_md_8 = new Div();
        col_md_8.setClass("col-md-8");
        col_md_8.setParent(rowTitle);
        
        Div titleCategory = new Div();
        titleCategory.setClass("title-category");
        titleCategory.setParent(col_md_8);
        
        H2 h2Title = new H2();
        h2Title.setParent(titleCategory);
        
        A hotnew = new A();
        hotnew.setParent(h2Title);
        
        Span spanTitle = new Span();
        spanTitle.setParent(hotnew);
        String widgetTitle = "";
        if (wg != null && wg.getWidgetName() != null || !"".equals(wg.getWidgetName())) {
            widgetTitle = wg.getWidgetName();
        }
        Label lblFunctionName = new Label(widgetTitle);
        lblFunctionName.setParent(spanTitle);

        //build content
        Div divRow = new Div();
        divRow.setClass("row");
        divRow.setParent(container);
        
        for (int i = 0; i < 3; i++) {
            Div divContent = new Div();
            divContent.setClass("col-md-4 col-sm-12");
            divContent.setParent(divRow);
            
            Div divContentCol = new Div();
            divContentCol.setClass("irs-blog-single-col");
            divContentCol.setParent(divContent);
            
            Div divContentBar = new Div();
            divContentBar.setClass("irs-side-bar");
            divContentBar.setParent(divContentCol);
            
            Div divContentPost = new Div();
            divContentPost.setClass("irs-post");
            divContentPost.setParent(divContentBar);
            
            Div divContentPostTitle = new Div();
            divContentPostTitle.setClass("irs-post-item-group");
            divContentPostTitle.setParent(divContentPost);
            
            Image imgPostItem = new Image();
            String srcPostItem = "images/tuyen_sinh.png";
            imgPostItem.setSrc(srcPostItem);
            imgPostItem.setParent(divContentPostTitle);
            
            Span spanTime = new Span();
            spanTime.setParent(divContentPostTitle);
            
            Label lblPrimaryTime = new Label("Tuyển sinh");
            lblPrimaryTime.setClass("irs-post-item-group-title");
            lblPrimaryTime.setParent(spanTime);
            
        }
        
    }
    
    private void buildFooter(int column) {
        
        Div divFooter = new Div();
        divFooter.setClass("irs-footer-field");
        divFooter.setParent(addWidgetIndex);
        
        Div divContainer = new Div();
        divContainer.setSclass("container");
        divContainer.setParent(divFooter);
        
        Div divRow = new Div();
        divRow.setSclass("row animatedParent animateOnce animateOnce");
        divRow.setParent(divContainer);
        
        if (column == 3) {
            Div divColumn3 = new Div();
            divColumn3.setSclass("col-md-4");
            divColumn3.setParent(divRow);
            
            Div irsFooer = new Div();
            irsFooer.setClass("irs-footer-link");
            irsFooer.setParent(divColumn3);
            
            H4 title = new H4();
            title.setParent(irsFooer);
            
            Label lblFunctionName = new Label("Các Phòng chuyên môn, nghiệp vụ");
            lblFunctionName.setClass("irs-footer-heading");
            lblFunctionName.setParent(title);
            
            P spanFooterContent = new P();
            spanFooterContent.setParent(irsFooer);
            
            Label lblPostItemTime = new Label("<p>Phòng Đào tạo và Quản lý khoa học</p>\n"
                    + "                            <p>Phòng Tài chính kế toán</p>\n"
                    + "                            <p>Phòng Quản trị thiết bị</p>");
            lblPostItemTime.setParent(spanFooterContent);
            
        }
    }
    
}