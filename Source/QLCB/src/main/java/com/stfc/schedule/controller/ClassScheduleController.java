/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.schedule.controller;

import com.stfc.documentpage.controller.*;
import com.stfc.website.*;
import com.stfc.utils.Common;
import com.stfc.utils.Constants;
import com.stfc.utils.SpringConstant;
import com.stfc.website.bean.Banner;
import com.stfc.website.bean.Document;
import com.stfc.website.bean.Post;
import com.stfc.website.bean.WidgetMapContent;
import com.stfc.website.domain.Category;
import com.stfc.website.domain.Param;
import com.stfc.website.service.WidgetService;
import com.stfc.website.widget.WidgetBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zhtml.H2;
import org.zkoss.zhtml.H3;
import org.zkoss.zhtml.H4;
import org.zkoss.zul.Label;
import org.zkoss.zul.Span;
import org.zkoss.zhtml.P;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.A;
import org.zkoss.zul.Html;
import org.zkoss.zul.Image;

/**
 *
 * @author daond
 */
public class ClassScheduleController extends SelectorComposer<Div> {

    private static final Logger logger = Logger.getLogger(ClassScheduleController.class);
    @Wire
    Div indexSlider;

    @Wire
    Div indexNotice;

    @Wire
    Div schedulePage;

    @Wire
    Div addWidgetFooter;

    private WidgetBuilder widgetBuilder = new WidgetBuilder();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/mm/yyyy");
    private SimpleDateFormat dateFormatRelated = new SimpleDateFormat("dd/mm/yyyy");

    @WireVariable
    protected WidgetService widgetService;
    private String urlImage;

    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp);
        logger.info("======>URL from Executions: " + Executions.getCurrent().getAttribute(Constants.STFC_URL_ATTRIBUTE));
        widgetService = (WidgetService) SpringUtil.getBean(SpringConstant.WIDGET_SERVICE);
        urlImage = Common.getParamByKey(Constants.DOCUMENT_PAGE_URL_IMAGE).getParamValue();
        List<Banner> lstBanner = new ArrayList<>(Memory.getListBannerCache().values());
        List<Post> lstPostNotice = new ArrayList<>();
        try {
            lstPostNotice = widgetService.getPostByCategoryId(Memory.getLngCategoryNotice(), 0, Constants.POST_IS_PUBLIC);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (lstPostNotice != null && !lstPostNotice.isEmpty()) {
            List<Category> lstCategory = new ArrayList<>(Memory.getListCategoryCache().values());
            if (lstCategory != null && !lstCategory.isEmpty()) {
                for (Category c : lstCategory) {
                    if (Memory.getLngCategoryNotice() == c.getCategoryId()) {
                        widgetBuilder.buildBannerIndex(lstBanner, indexSlider, indexNotice, c.getCategoryName(), lstPostNotice);
                    }
                }
            }
        } else if (lstBanner != null && !lstBanner.isEmpty()) {
            widgetBuilder.buildBanner(lstBanner, indexSlider, urlImage);
        }

        //build post detail
        List<Document> lstDocument = widgetService.getClassSchedule();
//        List<Category> lstCategoryDoc = widgetService.getCategoryDocument();

        List<WidgetMapContent> vlstWidget = new ArrayList<>(Memory.getListWidgetMapContentCache().values());
        if (vlstWidget != null && !vlstWidget.isEmpty()) {
            for (WidgetMapContent wg : vlstWidget) {
                if (Constants.WIDGET_TYPE_FOOTER.equals(wg.getWidgetType())
                        && Constants.WIDGET_POSITION_FOOTER.equalsIgnoreCase(wg.getWidgetPosition())) {
                    widgetBuilder.buildFooter(wg, addWidgetFooter);
                }
            }
        }
        buildInternal(Labels.getLabel("class.schedule"), lstDocument);
    }

    private void buildInternal(String title, List<Document> plstDocument) {
        Div postMain = new Div();
        postMain.setClass("inner-page-content left-img");
        postMain.setParent(schedulePage);

        Div divContainer = new Div();
        divContainer.setClass("container");
        divContainer.setParent(postMain);

        Div divRow = new Div();
        divRow.setClass("row");
        divRow.setParent(divContainer);

//            for (Category wmc : plstCategory) {
        Div divColMd8 = new Div();
        divColMd8.setClass("col-md-8");
        divColMd8.setParent(divRow);

        //Post content
        Div rowTitle = new Div();
        rowTitle.setClass("border-bottom-title-category");
        rowTitle.setParent(divColMd8);

        Div titleCategory = new Div();
        titleCategory.setClass("title-category");
        titleCategory.setParent(rowTitle);

        H2 h2Title = new H2();
        h2Title.setParent(titleCategory);

        A hotnew = new A();
        hotnew.setParent(h2Title);

        Span spanTitleCategory = new Span();
        spanTitleCategory.setParent(hotnew);
//        String widgetTitle = wc.getCategoryName();

        Label lblFunctionName = new Label(title);

        if (plstDocument != null && !plstDocument.isEmpty()) {
            for (Document p1 : plstDocument) {
                lblFunctionName.setParent(spanTitleCategory);

                Div divContentPostItem = new Div();
                divContentPostItem.setClass("irs-post-item-3-column-related");
                divContentPostItem.setParent(divColMd8);
                A aPostItemTitle = new A();
//                aPostItemTitle.setHref(p1.getDocumentPath());
                aPostItemTitle.setHref(Constants.PREFIX_SLUG_DOCUMENT + p1.getDocumentId());
                aPostItemTitle.setParent(divContentPostItem);

                H4 h4Post = new H4();
                h4Post.setParent(aPostItemTitle);
                String titleRelated = "<i class='fa fa-angle-double-right'></i> " + p1.getCategoryName();
                Html htmPostItem = new Html();
                htmPostItem.setContent(titleRelated);
                htmPostItem.setParent(h4Post);

                P spanPostTime = new P();
                spanPostTime.setParent(divContentPostItem);
            }
        }

        //Build tin tuc noi bat
        Div divColMd4 = new Div();
        divColMd4.setClass("col-md-4");
        divColMd4.setParent(divRow);

        Div irsSideBar = new Div();
        irsSideBar.setClass("irs-side-bar");
        irsSideBar.setParent(divColMd4);

        Div irsPost = new Div();
        irsPost.setClass("irs-post");
        irsPost.setParent(irsSideBar);

        H3 newPost = new H3();
        newPost.setParent(irsPost);

        Param param1 = Common.getParamByKey(Constants.PARAM_KEY_CATEGORY_WIDGET_POST_DETAIL_1);
        try {
            if (param1 != null && param1.getParamValue() != null) {
                Long categoryId1 = Long.parseLong(param1.getParamValue());
                Category category1 = Common.getCategoryById(categoryId1);
                Span spanNewPost = new Span();
                spanNewPost.setClass("irs-sidebar-title");
                spanNewPost.setParent(newPost);
                String postNewPos = category1.getCategoryName();
                Label lblNewPos = new Label(postNewPos);
                lblNewPos.setParent(spanNewPost);

                List<Post> lstPost1 = widgetService.getPostByCategoryId(categoryId1, Constants.MAX_POST_WIDGET_POST_DETAIL, Constants.POST_IS_PUBLIC);
                if (lstPost1 != null && !lstPost1.isEmpty()) {
                    for (Post p1 : lstPost1) {
                        Div divPostItem = new Div();
                        divPostItem.setClass("irs-post-item-post-detail post-item-padding");
                        divPostItem.setParent(irsPost);

                        A linkPostItem = new A();
                        linkPostItem.setHref(p1.getPostSlug());
                        linkPostItem.setParent(divPostItem);

                        Image imgPostItem = new Image();
                        String src = "";
                        if (p1.getFeaturedImage() != null && !"".equals(p1.getFeaturedImage())) {
                            src = urlImage + p1.getFeaturedImage();
                        }
                        imgPostItem.setSrc(src);
                        imgPostItem.setParent(linkPostItem);

                        Div divPostTitle = new Div();
                        divPostTitle.setClass("irs-post-item-post-detail-title");
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
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

}
