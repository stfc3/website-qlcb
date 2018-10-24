/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.widget;

import com.stfc.utils.Common;
import com.stfc.utils.Constants;
import com.stfc.website.bean.Banner;
import com.stfc.website.bean.Document;
import com.stfc.website.bean.Post;
import com.stfc.website.bean.WidgetContent;
import com.stfc.website.bean.WidgetMapContent;
import com.stfc.website.domain.Param;
import com.stfc.website.service.WidgetService;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.log4j.Logger;
import org.zkoss.zhtml.H4;
import org.zkoss.zk.ui.Component;
import org.zkoss.zhtml.H2;
import org.zkoss.zhtml.H3;
import org.zkoss.zhtml.P;
import org.zkoss.zul.A;
import org.zkoss.zul.Div;
import org.zkoss.zul.Html;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Span;

/**
 *
 * @author daond
 */
public class WidgetBuilder {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    Common com = new Common();
    private static final Logger logger = Logger.getLogger(WidgetBuilder.class);

    public void buildBanner(List<Banner> plstBanner, Component indexSlider, String urlImage) {
        Div slider;
        Image img;

        for (int i = 0; i < plstBanner.size(); i++) {
            Banner b = plstBanner.get(i);
            if (b.getBannerType() == 2) {
                slider = new Div();
                A linkbanner = new A();
                linkbanner.setHref(b.getBannerUrl());
                linkbanner.setParent(slider);
                if (i == 0) {
                    slider.setClass("item slides active");
                } else {
                    slider.setClass("item slides");
                }
                slider.setParent(indexSlider);
                img = new Image();
                String src = "";
                if (b.getBannerImage() != null) {
                    src = urlImage + b.getBannerImage();
                }
                img.setSrc(src);
                img.setParent(linkbanner);
            }
        }
    }

    public void buildBannerIndex(List<Banner> plstBanner, Component indexSlider, Component indexNotice,
            String titleNotice, List<Post> lstPostNotice) {
        Div slider;
        Image img;

        for (int i = 0; i < plstBanner.size(); i++) {
            Banner b = plstBanner.get(i);
            if (b.getBannerType() == 2) {
                slider = new Div();
                A linkbanner = new A();
                linkbanner.setHref(b.getBannerUrl());
                linkbanner.setParent(slider);
                if (i == 0) {
                    slider.setClass("item slides active");
                } else {
                    slider.setClass("item slides");
                }
                slider.setParent(indexSlider);
                img = new Image();
                String src = "";
                if (b.getBannerImage() != null) {
                    src = b.getBannerImage();
                }
                img.setSrc(src);
                img.setParent(linkbanner);
            }
        }
        //Build Notice
        Div divColMd4 = new Div();
        divColMd4.setSclass("col-md-3 no-pading");
        divColMd4.setParent(indexNotice);

        Div divBlogCol = new Div();
        divBlogCol.setSclass("irs-blog-single-col");
        divBlogCol.setParent(divColMd4);

        Div divIrsPost = new Div();
//        divIrsPost.setSclass("irs-post");
        divIrsPost.setParent(divBlogCol);

        Div divTitle = new Div();
        divTitle.setSclass("title-category-hot");
        divTitle.setParent(divIrsPost);

        H2 h2Title = new H2();
        h2Title.setParent(divTitle);

        Span spanTitle = new Span();
        spanTitle.setClass("irs-sidebar-title-right-banner");
        spanTitle.setParent(h2Title);

        Label lblFunctionName = new Label(titleNotice);
        lblFunctionName.setParent(spanTitle);

        Div divListPost = new Div();
        divListPost.setSclass("post-hot scroll-y");
        divListPost.setParent(divIrsPost);

        if (lstPostNotice != null && !lstPostNotice.isEmpty()) {
            for (Post p : lstPostNotice) {
                Div divContentPostItem = new Div();
                divContentPostItem.setClass("irs-post-item-3-column-hot");
                divContentPostItem.setParent(divListPost);
                //daond
                A aPostItemTitle = new A();
                aPostItemTitle.setHref(p.getPostSlug());
                aPostItemTitle.setParent(divContentPostItem);

                H4 h4Post = new H4();
                h4Post.setParent(aPostItemTitle);

                Label lblPostTitle = new Label(p.getPostTitle());
//                lblPostTitle.setClass("post-title");
                lblPostTitle.setParent(h4Post);
//                int datePostPrimary = Integer.parseInt(dateFormat.format(p.getPostDate()));
//                int dateNow = Integer.parseInt(dateFormat.format(new Date()));
//                Param param = Common.getParamByKey(Constants.KEY_COMPATE_NEW_POST);
//                if (param != null && param.getParamValue() != null) {
//                    int dateCompate = Integer.parseInt(param.getParamValue());
                if (com.checkNewsPost(p.getPostDate())) {
                    Div divNew = new Div();
                    divNew.setClass("new_flash");
                    divNew.setParent(h4Post);
                }
//                }

//                P spanPostTime = new P();
//                spanPostTime.setParent(divContentPostItem);
//                String datePostPrimary = dateFormat.format(p.getPostDate());
//                Label lblPostItemTime = new Label(datePostPrimary);
//                lblPostItemTime.setClass("time-hot");
//                lblPostItemTime.setParent(spanPostTime);
            }
        }

    }

    public void buildFooter(WidgetMapContent wg, Component addWidgetIndex) {
        if (wg.getListContent() != null && !wg.getListContent().isEmpty()) {
//            int intWidgetContent = wg.getListContent().size();
//            Div divFooter = new Div();
//            divFooter.setClass("irs-footer-field");
//            divFooter.setParent(addWidgetIndex);

//            Div divContainer = new Div();
//            divContainer.setSclass("container");
//            divContainer.setParent(divFooter);
//
//            Div divRow = new Div();
//            divRow.setSclass("row animatedParent animateOnce animateOnce");
//            divRow.setParent(divContainer);
//            for (WidgetContent wc : wg.getListContent()) {
//                Div divColumn3 = new Div();
//                divColumn3.setSclass("col-md-4");
//                switch (intWidgetContent) {
//                    case 1:
//                        divColumn3.setClass("col-md-12");
//                        break;
//                    case 2:
//                        divColumn3.setClass("col-md-6");
//                        break;
//                    case 3:
//                        divColumn3.setClass("col-md-4");
//                        break;
//                    case 4:
//                        divColumn3.setClass("col-md-3");
//                        break;
//                    default:
//                        divColumn3.setSclass("col-md-4");
//                        break;
//                }
//                divColumn3.setParent(divRow);
            WidgetContent wc = wg.getListContent().get(0);
//                Div irsFooer = new Div();
//                irsFooer.setClass("irs-footer-link");
//                irsFooer.setParent(divColumn3);

            H4 title = new H4();
            title.setParent(addWidgetIndex);
            String strTitle = "";
            if (wc.getWidgetContentName() != null && !"".equals(wc.getWidgetContentName())) {
                strTitle = wc.getWidgetContentName();
            }
            Label lblFunctionName = new Label(strTitle);
            lblFunctionName.setClass("irs-footer-heading");
            lblFunctionName.setParent(title);

            P spanFooterContent = new P();
            spanFooterContent.setParent(addWidgetIndex);

            Html htmPostItemTime = new Html();
            String strContent = "";
            if (wc.getWidgetContent() != null && !"".equals(wc.getWidgetContent())) {
                strContent = wc.getWidgetContent();
            }
            htmPostItemTime.setContent(strContent);
            htmPostItemTime.setParent(spanFooterContent);

//            }
        }
    }

    public void buildWidgetRight(List<WidgetMapContent> plstWidget, List<Post> plstPost, Component addWidgetIndexRight,
            WidgetService widgetService) {
        if (plstWidget != null && !plstWidget.isEmpty()) {

            for (WidgetMapContent wg : plstWidget) {
                if (Constants.WIDGET_POSITION_RIGHT_CONTENT.equalsIgnoreCase(wg.getWidgetPosition())) {
                    buildWidgetRightCategory(wg, plstPost, addWidgetIndexRight, widgetService);
                }
            }
        }
    }

    private void buildWidgetRightCategory(WidgetMapContent wg, List<Post> lstPost, Component addWidgetIndexRight,
            WidgetService widgetService) {
        //Build tin tuc noi bat
        if (wg.getListContent() != null && !wg.getListContent().isEmpty()) {
            List<WidgetContent> wgContent = wg.getListContent();
            Param categoryLegislation = com.getParamByKey(Constants.CATEGORY_LEGISLATION);
            for (WidgetContent wc : wgContent) {
                Div divColMd4 = new Div();
                divColMd4.setClass("");
                divColMd4.setParent(addWidgetIndexRight);

                Div irsSideBar = new Div();
                irsSideBar.setClass("irs-side-bar");
                irsSideBar.setParent(divColMd4);

                Div irsPost = new Div();
                irsPost.setClass("irs-post");
                irsPost.setParent(irsSideBar);

                H3 newPost = new H3();
                newPost.setParent(irsPost);

                Div irsPostContent = new Div();
                irsPostContent.setClass("irs-side-bar-border");
                irsPostContent.setParent(irsPost);

                try {
                    Span spanNewPost = new Span();
                    spanNewPost.setClass("irs-sidebar-title-right");
                    spanNewPost.setParent(newPost);
                    String postNewPos = wc.getWidgetContentName();
                    Label lblNewPos = new Label(postNewPos);
                    lblNewPos.setParent(spanNewPost);
                    if (wc != null && wc.getWidgetContent() != null && Constants.WIDGET_CONTENT_TYPE_CATEGORY.equalsIgnoreCase(wc.getWidgetType())
                            && !wc.getWidgetContent().equalsIgnoreCase(categoryLegislation.getParamValue())) {
                        List<Post> lstPost1 = com.getPostByContent(wg, lstPost);
                        int maxPost = 5;
                        if (lstPost1.size() <= 5) {
                            maxPost = lstPost1.size();
                        }
                        if (lstPost1 != null && !lstPost1.isEmpty()) {
                            for (int i = 0; i < maxPost; i++) {

                                Post p1 = lstPost1.get(i);
                                if (wc.getWidgetContent().equalsIgnoreCase(String.valueOf(p1.getCategoryId()))) {
                                    Div divPostItem = new Div();
                                    divPostItem.setClass("irs-post-item-post-detail-2");
                                    divPostItem.setParent(irsPostContent);

                                    Div divPostTitle = new Div();
                                    divPostTitle.setClass("");
                                    divPostTitle.setParent(divPostItem);

                                    A aPostItemTitle = new A();
                                    aPostItemTitle.setHref(p1.getPostSlug());
                                    aPostItemTitle.setParent(divPostTitle);
                                    String p1Title = "";
                                    if (p1.getPostTitle() != null && !"".equals(p1.getPostTitle())) {
                                        p1Title = p1.getPostTitle();
                                    }

                                    Label lblPostTitleItem = new Label(p1Title);
                                    lblPostTitleItem.setClass("post-title");
                                    lblPostTitleItem.setParent(aPostItemTitle);

                                    if (com.checkNewsPost(p1.getPostDate())) {
                                        Div divNew = new Div();
                                        divNew.setClass("new_flash");
                                        divNew.setParent(divPostTitle);
                                    }
//                                P spanPostTime = new P();
//                                spanPostTime.setParent(divPostItem);
//
//                                String dateP1 = "";
//                                if (p1.getPostDate() != null && !"".equals(p1.getPostDate())) {
//                                    dateP1 = dateFormat.format(p1.getPostDate());
//                                }
//                                Label lblPostItemTime = new Label(dateP1);
//                                lblPostItemTime.setClass("time-post");
//                                lblPostItemTime.setParent(spanPostTime);
                                }

                            }
                        }
                        if (lstPost1.size() >= 5) {
                            A linkReadMore = new A();
                            linkReadMore.setClass("more-detail-right");
                            String strUrlDetaiMore = "#";
                            if (wc.getDetailMoreSlug() != null && !"".equals(wc.getDetailMoreSlug())) {
                                strUrlDetaiMore = wc.getDetailMoreSlug();
                            }
                            linkReadMore.setHref(strUrlDetaiMore);
                            linkReadMore.setParent(irsPostContent);

                            Label lblTitle = new Label("Xem thêm >>");
                            lblTitle.setParent(linkReadMore);
                        }
                    }
                    if (wc != null && wc.getWidgetContent() != null && Constants.WIDGET_CONTENT_TYPE_CATEGORY.equalsIgnoreCase(wc.getWidgetType())
                            && wc.getWidgetContent().equalsIgnoreCase(categoryLegislation.getParamValue())) {
                        //Van ban phap luat
                        List<Document> lstPost1 = widgetService.getDocumentByCategoryId(Long.parseLong(wc.getWidgetContent()));
                        int maxPost = 5;
                        if (lstPost1.size() <= 5) {
                            maxPost = lstPost1.size();
                        }
                        if (lstPost1 != null && !lstPost1.isEmpty()) {
                            for (int i = 0; i < maxPost; i++) {
                                Document p1 = lstPost1.get(i);
                                Div divPostItem = new Div();
                                divPostItem.setClass("border-post");
                                divPostItem.setParent(irsPostContent);

                                Div divPostTitle = new Div();
//                                divPostTitle.setClass("irs-post-item-post-detail-title-right");
                                divPostTitle.setParent(divPostItem);

                                A aPostItemTitle = new A();
                                aPostItemTitle.setHref(Constants.PREFIX_SLUG_DOCUMENT + p1.getDocumentId());
                                aPostItemTitle.setParent(divPostTitle);
                                String p1Title = "";
                                if (p1.getDocumentName() != null && !"".equals(p1.getDocumentName())) {
                                    p1Title = p1.getDocumentName();
                                }

                                Label lblPostTitleItem = new Label(p1Title);
                                lblPostTitleItem.setClass("post-title");
                                lblPostTitleItem.setParent(aPostItemTitle);

                                P spanPostTime = new P();
                                spanPostTime.setParent(divPostItem);

//                                String dateP1 = "";
//                                if (p1.get != null && !"".equals(p1.getPostDate())) {
//                                    dateP1 = dateFormat.format(p1.getPostDate());
//                                }
//                                Label lblPostItemTime = new Label(dateP1);
//                                lblPostItemTime.setClass("time-post");
//                                lblPostItemTime.setParent(spanPostTime);
                            }
                            if (lstPost1.size() >= 5) {
                                A linkReadMore = new A();
                                linkReadMore.setClass("more-detail-right");
                                String strUrlDetaiMore = "#";
                                if (wc.getDetailMoreSlug() != null && !"".equals(wc.getDetailMoreSlug())) {
                                    strUrlDetaiMore = wc.getDetailMoreSlug();
                                }
                                linkReadMore.setHref(strUrlDetaiMore);
                                linkReadMore.setParent(irsPostContent);

                                Label lblTitle = new Label("Xem thêm >>");
                                lblTitle.setParent(linkReadMore);
                            }
                        }
                    } else if (wc != null && wc.getWidgetContent() != null && Constants.WIDGET_CONTENT_TYPE_TEXT.equalsIgnoreCase(wc.getWidgetType())) {
                        Html htmPostItemTime = new Html();
                        String strContent = "";
                        if (wc.getWidgetContent() != null && !"".equals(wc.getWidgetContent())) {
                            strContent = wc.getWidgetContent();
                        }
                        htmPostItemTime.setContent(strContent);
                        htmPostItemTime.setParent(irsPostContent);
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }

        }
    }
}
