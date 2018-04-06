/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.newsadvertisement;

import com.stfc.website.*;
import com.stfc.utils.Common;
import com.stfc.utils.Constants;
import com.stfc.utils.SpringConstant;
import com.stfc.website.bean.Banner;
import com.stfc.website.bean.Post;
import com.stfc.website.bean.WidgetContent;
import com.stfc.website.bean.WidgetMapContent;
import com.stfc.website.service.WidgetService;
import com.stfc.website.widget.WidgetBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zhtml.H3;
import org.zkoss.zul.Label;
import org.zkoss.zul.Span;
import org.zkoss.zhtml.P;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.A;
import org.zkoss.zul.Html;

/**
 *
 * @author daond
 */
public class EnrollStudentsController extends SelectorComposer<Div> {

    private static final Logger logger = Logger.getLogger(EnrollStudentsController.class);
    @Wire
    Div indexSlider;

    @Wire
    Div addWidgetIndexRight;

    @Wire
    Div addWidgetFooter;

    private WidgetBuilder widgetBuilder = new WidgetBuilder();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/mm/yyyy");
    private SimpleDateFormat dateFormatRelated = new SimpleDateFormat("dd/mm/yyyy");

    @WireVariable
    protected WidgetService widgetService;

    private String urlImage;

    private Common com = new Common();

    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp);
        logger.info("======>URL from Executions: " + Executions.getCurrent().getAttribute(Constants.STFC_URL_ATTRIBUTE));
        widgetService = (WidgetService) SpringUtil.getBean(SpringConstant.WIDGET_SERVICE);
        urlImage = Common.getParamByKey(Constants.ENROLL_STUDENTS_URL_IMAGE).getParamValue();
        List<Banner> lstBanner = new ArrayList<>(Memory.getListBannerCache().values());
        if (lstBanner != null && !lstBanner.isEmpty()) {
            widgetBuilder.buildBanner(lstBanner, indexSlider, urlImage);
        }

        //build post detail
//        String postSlug = String.valueOf(Executions.getCurrent().getAttribute(Constants.STFC_URL_ATTRIBUTE));
//        List<Post> lstPost = widgetService.getPostBySlug(postSlug);
//        buildPostDetail(lstPost);

        List<WidgetMapContent> vlstWidget = new ArrayList<>(Memory.getListWidgetMapContentCache().values());
        List<Post> lstPostRight = widgetService.getPost(Memory.getLstCategoryId(), Constants.POST_IS_PUBLIC);
        buildWidgetRight(vlstWidget, lstPostRight);

        if (vlstWidget != null && !vlstWidget.isEmpty()) {
            for (WidgetMapContent wg : vlstWidget) {
                if (Constants.WIDGET_TYPE_FOOTER.equals(wg.getWidgetType())
                        && Constants.WIDGET_POSITION_FOOTER.equalsIgnoreCase(wg.getWidgetPosition())) {
                    widgetBuilder.buildFooter(wg, addWidgetFooter);
                }
            }
        }
    }

//    private void buildPostDetail(List<Post> lstPost) {
//        if (lstPost != null && !lstPost.isEmpty()) {
//            Post p = lstPost.get(0);
//            Long categoryId = lstPost.get(0).getCategoryId();
//            Category categoryTitle = Common.getCategoryById(categoryId);
//            Div postMain = new Div();
//            postMain.setClass("inner-page-content left-img");
//            postMain.setParent(enrollStudents);
//
//            Div divContainer = new Div();
//            divContainer.setClass("");
//            divContainer.setParent(postMain);
//
//            Div divRow = new Div();
//            divRow.setClass("row");
//            divRow.setParent(divContainer);
//
//            Div divColMd8 = new Div();
//            divColMd8.setClass("col-md-12");
//            divColMd8.setParent(divRow);
//
//            //Post content
//            Div irsBlogSingle = new Div();
//            irsBlogSingle.setClass("irs-blog-single-col");
//            irsBlogSingle.setParent(divColMd8);
//
//            Div irsBlogCol = new Div();
//            irsBlogCol.setClass("irs-blog-col");
//            irsBlogCol.setParent(irsBlogSingle);
//
//            H1 titlePost = new H1();
//            titlePost.setParent(irsBlogCol);
//
//            Span spanTitle = new Span();
//            spanTitle.setClass("pTitle");
//            spanTitle.setParent(titlePost);
//            String postTitle = "";
//            if (p.getPostTitle() != null && !"".equals(p.getPostTitle())) {
//                postTitle = p.getPostTitle();
//            }
//            Label lblPostTitle = new Label(postTitle);
//            lblPostTitle.setParent(spanTitle);
//
//            P postContent = new P();
//            postContent.setParent(irsBlogCol);
//
//            Span spanContent = new Span();
//            spanContent.setClass("pBody");
//            spanContent.setParent(postContent);
//            String strPostContent = "";
//            Html htmPostContent = new Html();
//            if (p.getPostContent() != null && !"".equals(p.getPostContent())) {
//                strPostContent = p.getPostContent();
//            }
//            htmPostContent.setContent(strPostContent);
//            htmPostContent.setParent(spanContent);
//
//            //Tin tuc lien quan
//            Div hotNewMain = new Div();
//            hotNewMain.setClass("irs-blog-field");
//            hotNewMain.setParent(divColMd8);
//
//            Div container = new Div();
//            container.setClass("");
//            container.setParent(hotNewMain);
//
//            Div rowTitle = new Div();
//            rowTitle.setClass("row border-bottom-title-category");
//            rowTitle.setParent(container);
//
//            Div col_md_8 = new Div();
//            col_md_8.setClass("col-md-8");
//            col_md_8.setParent(rowTitle);
//
//            Div titleCategory = new Div();
//            titleCategory.setClass("title-category");
//            titleCategory.setParent(col_md_8);
//
//            H2 h2Title = new H2();
//            h2Title.setParent(titleCategory);
//
//            A hotnew = new A();
//            hotnew.setParent(h2Title);
//
//            Span spanTitleCategory = new Span();
//            spanTitleCategory.setParent(hotnew);
//            String widgetTitle = "Tin tức liên quan";
//
//            Label lblFunctionName = new Label(widgetTitle);
//            lblFunctionName.setParent(spanTitleCategory);
//            Div divListPost = new Div();
//            divListPost.setParent(container);
//            List<Post> lstPostRelated = widgetService.getPostByCategoryIdRelated(categoryTitle.getCategoryId(), 10, p.getPostId());
//            if (lstPostRelated != null && !lstPostRelated.isEmpty()) {
//                for (Post p1 : lstPostRelated) {
//                    Div divContentPostItem = new Div();
//                    divContentPostItem.setClass("irs-post-item-3-column-related");
//                    divContentPostItem.setParent(divListPost);
//                    //daond
//                    A aPostItemTitle = new A();
//                    aPostItemTitle.setHref(p1.getPostSlug());
//                    aPostItemTitle.setParent(divContentPostItem);
//
//                    H4 h4Post = new H4();
//                    h4Post.setParent(aPostItemTitle);
//                    String titleRelated = "<i class='fa fa-angle-double-right'></i> " + p1.getPostTitle() + "<span style='font-size:11px; color: #0d4e96;'> (" + dateFormatRelated.format(p1.getPostDate()) + ") </span>";
//                    Html htmPostItem = new Html();
//                    htmPostItem.setContent(titleRelated);
//                    htmPostItem.setParent(h4Post);
//
//                    P spanPostTime = new P();
//                    spanPostTime.setParent(divContentPostItem);
//                }
//            }
//        }
//    }

    private void buildWidgetRight(List<WidgetMapContent> plstWidget, List<Post> plstPost) {
        if (plstWidget != null && !plstWidget.isEmpty()) {

            for (WidgetMapContent wg : plstWidget) {
                if (Constants.WIDGET_POSITION_RIGHT_CONTENT.equalsIgnoreCase(wg.getWidgetPosition())) {
                    buildWidgetRightCategory(wg, plstPost);
                }
            }
        }
    }

    private void buildWidgetRightCategory(WidgetMapContent wg, List<Post> lstPost) {
        //Build tin tuc noi bat
        if (wg.getListContent() != null && !wg.getListContent().isEmpty()) {
            List<WidgetContent> wgContent = wg.getListContent();
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

                try {
                    Span spanNewPost = new Span();
                    spanNewPost.setClass("irs-sidebar-title-right");
                    spanNewPost.setParent(newPost);
                    String postNewPos = wc.getWidgetContentName();
                    Label lblNewPos = new Label(postNewPos);
                    lblNewPos.setParent(spanNewPost);
                    if (wc != null && wc.getWidgetContent() != null && Constants.WIDGET_CONTENT_TYPE_CATEGORY.equalsIgnoreCase(wc.getWidgetType())) {
                        List<Post> lstPost1 = com.getPostByContent(wg, lstPost);
                        int maxPost = 5;
                        if (lstPost1.size() <= 5) {
                            maxPost = lstPost1.size();
                        }
                        if (lstPost1 != null && !lstPost1.isEmpty()) {
                            for (int i = 0; i < maxPost; i++) {
                                Post p1 = lstPost1.get(i);
                                Div divPostItem = new Div();
                                divPostItem.setClass("irs-post-item-post-detail");
                                divPostItem.setParent(irsPost);

                                Div divPostTitle = new Div();
                                divPostTitle.setClass("irs-post-item-post-detail-title-right");
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

                                P spanPostTime = new P();
                                spanPostTime.setParent(divPostItem);

                                String dateP1 = "";
                                if (p1.getPostDate() != null && !"".equals(p1.getPostDate())) {
                                    dateP1 = dateFormat.format(p1.getPostDate());
                                }
                                Label lblPostItemTime = new Label(dateP1);
                                lblPostItemTime.setClass("time-post");
                                lblPostItemTime.setParent(spanPostTime);
                            }
                        }
                    } else if (wc != null && wc.getWidgetContent() != null && Constants.WIDGET_CONTENT_TYPE_TEXT.equalsIgnoreCase(wc.getWidgetType())) {
                        Html htmPostItemTime = new Html();
                        String strContent = "";
                        if (wc.getWidgetContent() != null && !"".equals(wc.getWidgetContent())) {
                            strContent = wc.getWidgetContent();
                        }
                        htmPostItemTime.setContent(strContent);
                        htmPostItemTime.setParent(irsPost);
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }

        }
    }
}
