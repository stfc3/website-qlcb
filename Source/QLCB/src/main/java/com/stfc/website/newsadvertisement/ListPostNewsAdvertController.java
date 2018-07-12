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
import com.stfc.website.bean.WidgetMapContent;
import com.stfc.website.domain.Category;
import com.stfc.website.domain.Param;
import com.stfc.website.service.WidgetService;
import com.stfc.website.widget.WidgetBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.zkoss.zhtml.H1;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zhtml.H2;
import org.zkoss.zhtml.H3;
import org.zkoss.zul.Label;
import org.zkoss.zul.Span;
import org.zkoss.zhtml.P;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.A;
import org.zkoss.zul.Image;

/**
 *
 * @author daond
 */
public class ListPostNewsAdvertController extends SelectorComposer<Div> {

    private static final Logger logger = Logger.getLogger(ListPostNewsAdvertController.class);
    @Wire
    Div indexSlider;

    @Wire
    Div indexNotice;

    @Wire
    Div listPost;

    @Wire
    Div addWidgetFooter;

    private WidgetBuilder widgetBuilder = new WidgetBuilder();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");

    @WireVariable
    protected WidgetService widgetService;

    private String urlImage;

    @Wire
    Div addWidgetIndexRight;

    private Common com = new Common();

    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp);
        logger.info("======>URL from Executions: " + Executions.getCurrent().getAttribute(Constants.STFC_URL_ATTRIBUTE));
        widgetService = (WidgetService) SpringUtil.getBean(SpringConstant.WIDGET_SERVICE);
        urlImage = Common.getParamByKey(Constants.HOME_PAGE_URL_IMAGE).getParamValue();
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

        String categorySlug = String.valueOf(Executions.getCurrent().getAttribute(Constants.STFC_URL_ATTRIBUTE));
        List<Post> lstPost = widgetService.getPostByCategorySlug(categorySlug, 0, Constants.POST_IS_PUBLIC);

        buildPostDetail(lstPost);

        List<WidgetMapContent> vlstWidget = new ArrayList<>(Memory.getListWidgetMapContentCache().values());

        List<Post> lstPostRight = widgetService.getPost(Memory.getLstCategoryId(), Constants.POST_IS_PUBLIC);
        widgetBuilder.buildWidgetRight(vlstWidget, lstPostRight, addWidgetIndexRight, widgetService);
//        buildNewsHot();

        if (vlstWidget != null && !vlstWidget.isEmpty()) {
            for (WidgetMapContent wg : vlstWidget) {
                if (Constants.WIDGET_TYPE_FOOTER.equals(wg.getWidgetType())
                        && Constants.WIDGET_POSITION_FOOTER.equalsIgnoreCase(wg.getWidgetPosition())) {
                    widgetBuilder.buildFooter(wg, addWidgetFooter);
                }
            }
        }
    }

    private void buildPostDetail(List<Post> lstPost) {
        if (lstPost != null && !lstPost.isEmpty()) {
            Long categoryId = lstPost.get(0).getCategoryId();
            Category categoryTitle = Common.getCategoryById(categoryId);
            Div postMain = new Div();
            postMain.setClass("inner-page-content left-img");
            postMain.setParent(listPost);

            Div divContainer = new Div();
            divContainer.setClass("");
            divContainer.setParent(postMain);

            Div divRowCategory = new Div();
            divRowCategory.setClass("row");
            divRowCategory.setParent(divContainer);

            Div divColMd12 = new Div();
            divColMd12.setClass("col-md-12");
            divColMd12.setParent(divRowCategory);

            Div divCategoryText = new Div();
            divCategoryText.setClass("irs-discription-col text-center");
            divCategoryText.setParent(divColMd12);

            H1 h1Title = new H1();
            h1Title.setParent(divCategoryText);
            String categoryName = "";
            if (categoryTitle != null && !"".equals(categoryTitle.getCategoryName())) {
                categoryName = categoryTitle.getCategoryName();
            }
            Label lblTitleCategory = new Label(categoryName);
            lblTitleCategory.setParent(h1Title);

            Div divRow = new Div();
            divRow.setClass("row");
            divRow.setParent(divContainer);

            Div divColMd8 = new Div();
            divColMd8.setClass("col-md-12");
            divColMd8.setParent(divRow);

            //List post
            for (int i = 0; i < lstPost.size(); i++) {
                Post p = lstPost.get(i);
                Div divEntry = new Div();
                divEntry.setClass("entry");
                divEntry.setParent(divColMd8);

                Div divPostRow = new Div();
                divPostRow.setClass("row");
                divPostRow.setParent(divEntry);

                Div divPostColMd4 = new Div();
                divPostColMd4.setClass("col-md-4");
                divPostColMd4.setParent(divPostRow);

                Div divPostImg = new Div();
                divPostImg.setClass("image-area");
                divPostImg.setParent(divPostColMd4);

                A linkPost = new A();
                linkPost.setHref(p.getPostSlug());
                linkPost.setParent(divPostImg);

                Image imgPost = new Image();
                String srcPost = "";
                if (p.getFeaturedImage() != null && !"".equals(p.getFeaturedImage())) {
                    srcPost = urlImage + p.getFeaturedImage();
                }
                imgPost.setClass("img-responsive");
                imgPost.setSrc(srcPost);
                imgPost.setParent(linkPost);

                Div divPostColMd8 = new Div();
                divPostColMd8.setClass("col-md-8");
                divPostColMd8.setParent(divPostRow);

                H2 h2Title = new H2();
                h2Title.setParent(divPostColMd8);

                A hotnew = new A();
                hotnew.setHref(p.getPostSlug());
                hotnew.setParent(h2Title);

                Span spanTitle = new Span();
                spanTitle.setClass("entry-title");
                spanTitle.setParent(hotnew);
                String postTitle = "";
                if (p.getPostTitle() != null && !"".equals(p.getPostTitle())) {
                    postTitle = p.getPostTitle();
                }
                Label lblFunctionName = new Label(postTitle);
                lblFunctionName.setParent(spanTitle);

                Div divTime = new Div();
                divTime.setClass("entry-meta");
                divTime.setParent(divPostColMd8);

                P pPostTime = new P();
                pPostTime.setParent(divTime);

                String datePost = "";
                if (p.getPostDate() != null && !"".equals(p.getPostDate())) {
                    datePost = dateFormat.format(p.getPostDate());
                }
                Label lblPostTime = new Label(datePost);
                lblPostTime.setClass("time-post");
                lblPostTime.setParent(pPostTime);

                Div divExcerpt = new Div();
                divExcerpt.setClass("entry-text");
                divExcerpt.setParent(divPostColMd8);

                Span spanExcerpt = new Span();
                spanExcerpt.setParent(divExcerpt);
                String strExcerpt = "";
                if (p.getPostExcerpt() != null && !"".equals(p.getPostExcerpt())) {
                    strExcerpt = p.getPostExcerpt();
                }
                Label lblExcerpt = new Label(strExcerpt);
                lblExcerpt.setParent(spanExcerpt);

                A linkReadMore = new A();
                linkReadMore.setClass("read-more");
                String strUrlDetaiMore = p.getPostSlug();
                linkReadMore.setHref(strUrlDetaiMore);
                linkReadMore.setParent(divPostColMd8);

                Label lblTitle = new Label("Xem thêm");
                lblTitle.setParent(linkReadMore);
            }
//            Div divColMd4 = new Div();
//            divColMd4.setClass("col-md-4");
//            divColMd4.setParent(divRow);
//
//            Div irsSideBar = new Div();
//            irsSideBar.setClass("irs-side-bar");
//            irsSideBar.setParent(divColMd4);
//
//            Div irsPost = new Div();
//            irsPost.setClass("irs-post");
//            irsPost.setParent(irsSideBar);
//
//            H3 newPost = new H3();
//            newPost.setParent(irsPost);
//
//            Param param1 = Common.getParamByKey(Constants.PARAM_KEY_CATEGORY_WIDGET_POST_DETAIL_1);
//            try {
//                if (param1 != null && param1.getParamValue() != null) {
//                    Long categoryId1 = Long.parseLong(param1.getParamValue());
//                    Category category1 = Common.getCategoryById(categoryId1);
//                    Span spanNewPost = new Span();
//                    spanNewPost.setClass("irs-sidebar-title");
//                    spanNewPost.setParent(newPost);
//                    String postNewPos = category1.getCategoryName();
//                    Label lblNewPos = new Label(postNewPos);
//                    lblNewPos.setParent(spanNewPost);
//
//                    List<Post> lstPost1 = widgetService.getPostByCategoryId(categoryId1, Constants.MAX_POST_WIDGET_POST_DETAIL, Constants.POST_IS_PUBLIC);
//                    if (lstPost1 != null && !lstPost1.isEmpty()) {
//                        for (Post p1 : lstPost1) {
//                            Div divPostItem = new Div();
//                            divPostItem.setClass("irs-post-item-post-detail post-item-padding");
//                            divPostItem.setParent(irsPost);
//
//                            A linkPostItem = new A();
//                            linkPostItem.setHref(p1.getPostSlug());
//                            linkPostItem.setParent(divPostItem);
//
//                            Image imgPostItem = new Image();
//                            String src = "";
//                            if (p1.getFeaturedImage() != null && !"".equals(p1.getFeaturedImage())) {
//                                src = p1.getFeaturedImage();
//                            }
//                            imgPostItem.setSrc(src);
//                            imgPostItem.setParent(linkPostItem);
//
//                            Div divPostTitle = new Div();
//                            divPostTitle.setClass("irs-post-item-post-detail-title");
//                            divPostTitle.setParent(divPostItem);
//
//                            A aPostItemTitle = new A();
//                            aPostItemTitle.setHref(p1.getPostSlug());
//                            aPostItemTitle.setParent(divPostTitle);
//                            String p1Title = "";
//                            if (p1.getPostTitle() != null && !"".equals(p1.getPostTitle())) {
//                                p1Title = p1.getPostTitle();
//                            }
//
//                            Label lblPostTitleItem = new Label(p1Title);
//                            lblPostTitleItem.setClass("post-title");
//                            lblPostTitleItem.setParent(aPostItemTitle);
//
//                            P spanPostTime = new P();
//                            spanPostTime.setParent(divPostItem);
//
//                            String dateP1 = "";
//                            if (p1.getPostDate() != null && !"".equals(p1.getPostDate())) {
//                                dateP1 = dateFormat.format(p1.getPostDate());
//                            }
//                            Label lblPostItemTime = new Label(dateP1);
//                            lblPostItemTime.setClass("time-post");
//                            lblPostItemTime.setParent(spanPostTime);
//                        }
//                    }
//                }
//            } catch (Exception e) {
//                logger.error(e.getMessage(), e);
//            }
        }
    }

    private void buildNewsHot() {
        Div divColMd4 = new Div();
        divColMd4.setParent(addWidgetIndexRight);

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
                            src = p1.getFeaturedImage();
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

//                        P spanPostTime = new P();
//                        spanPostTime.setParent(divPostItem);
//
//                        String dateP1 = "";
//                        if (p1.getPostDate() != null && !"".equals(p1.getPostDate())) {
//                            dateP1 = dateFormat.format(p1.getPostDate());
//                        }
//                        Label lblPostItemTime = new Label(dateP1);
//                        lblPostItemTime.setClass("time-post");
//                        lblPostItemTime.setParent(spanPostTime);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
