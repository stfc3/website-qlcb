/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website;

import com.stfc.utils.Constants;
import com.stfc.utils.SpringConstant;
import com.stfc.website.bean.Banner;
import com.stfc.website.bean.Post;
import com.stfc.website.bean.WidgetContent;
import com.stfc.website.bean.WidgetMapContent;
import com.stfc.website.domain.Category;
import com.stfc.website.service.WidgetService;
import com.stfc.website.widget.WidgetBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.zkoss.zhtml.H2;
import org.zkoss.zhtml.P;
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

    @Wire
    Div indexSlider;

    @Wire
    Div indexNotice;

    @Wire
    Div addWidgetIndex;

    @Wire
    Div addWidgetFooter;

    @WireVariable
    protected WidgetService widgetService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/mm/yyyy");
    private WidgetBuilder widgetBuilder = new WidgetBuilder();

    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp);
        widgetService = (WidgetService) SpringUtil.getBean(SpringConstant.WIDGET_SERVICE);
        List<WidgetMapContent> vlstWidget = new ArrayList<>(Memory.getListWidgetMapContentCache().values());
        List<Post> lstPost = widgetService.getPost(Memory.getLstCategoryId());
        List<Banner> lstBanner = new ArrayList<>(Memory.getListBannerCache().values());
        //Get list Post notice banner
        List<Post> lstPostNotice = new ArrayList<>();
        try {
            lstPostNotice = widgetService.getPostByCategoryId(Memory.getLngCategoryNotice(), 0);
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
            widgetBuilder.buildBanner(lstBanner, indexSlider);
        }
        buildWidget(vlstWidget, lstPost);

    }

    private void buildWidget(List<WidgetMapContent> plstWidget, List<Post> plstPost) {
        if (plstWidget != null && !plstWidget.isEmpty()) {

            for (WidgetMapContent wg : plstWidget) {
                if (Constants.WIDGET_TYPE_HOTNEWS.equals(wg.getWidgetType())
                        && Constants.WIDGET_POSITION_CONTENT.equalsIgnoreCase(wg.getWidgetPosition())) {
                    buildWidgetHotNews(wg, plstPost);
                } else if (Constants.WIDGET_TYPE_NEWS_EVENT.equals(wg.getWidgetType())
                        && Constants.WIDGET_POSITION_CONTENT.equalsIgnoreCase(wg.getWidgetPosition())) {
                    buildWidgetNewsPost(wg, plstPost);
                } else if (Constants.WIDGET_TYPE_MULTI.equals(wg.getWidgetType())
                        && Constants.WIDGET_POSITION_CONTENT.equalsIgnoreCase(wg.getWidgetPosition())) {
                    buildMultiCategory(wg, plstPost);
                } else if (Constants.WIDGET_TYPE_FOOTER.equals(wg.getWidgetType())
                        && Constants.WIDGET_POSITION_FOOTER.equalsIgnoreCase(wg.getWidgetPosition())) {
                    widgetBuilder.buildFooter(wg, addWidgetFooter);
                }
            }
        }
    }

    /*
    /*Build HOT NEWS
     */
    private void buildWidgetHotNews(WidgetMapContent wg, List<Post> lstPost) {
        List<Post> lstPostByContent = getPostByContent(wg, lstPost);
        int postNumber;
        if (lstPostByContent.size() >= 4) {
            postNumber = 4;
        } else {
            postNumber = lstPostByContent.size();
        }
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
        for (int i = 0; i < postNumber; i++) {
            Post p = lstPostByContent.get(i);
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
            linkImg.setHref(Constants.PREFIX_POST + p.getPostSlug());
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
            String datePost = dateFormat.format(p.getPostDate());
            Label lblTime = new Label(datePost);
            lblTime.setClass("zmdi-calendar-alt");
            lblTime.setParent(spanTime);

            Div divTitle = new Div();
            divTitle.setClass("irs-courses-content");
            divTitle.setParent(col);

            A linkTitle = new A();
            linkTitle.setHref(Constants.PREFIX_POST + p.getPostSlug());
            linkTitle.setParent(divTitle);

            Label lblTitle = new Label(p.getPostTitle());
            lblTitle.setParent(linkTitle);

        }

        //Buton Xem them
        Div divReadMore = new Div();//
        divReadMore.setClass("col-md-12");
        divReadMore.setParent(rowContent);

        A linkReadMore = new A();
        linkReadMore.setClass("btn btn-default irs-btn-transparent-two btn-read-more");
        String strUrlDetaiMore = "#";
        if (wg != null && wg.getListContent() != null && wg.getListContent().get(0) != null) {
            if (wg.getListContent().get(0).getDetailMoreSlug() != null && !"".equals(wg.getListContent().get(0).getDetailMoreSlug())) {
                strUrlDetaiMore = wg.getListContent().get(0).getDetailMoreSlug();
            }
        }
        linkReadMore.setHref(Constants.PREFIX_CATEGORY + strUrlDetaiMore);
        linkReadMore.setParent(divReadMore);

        Label lblTitle = new Label("Xem thêm");
        lblTitle.setParent(linkReadMore);
    }

    private void buildWidgetNewsPost(WidgetMapContent wg, List<Post> lstPost) {
        if (wg != null && wg.getListContent() != null && !wg.getListContent().isEmpty() && lstPost != null && !lstPost.isEmpty()) {
            List<Post> lstPostByContent = getPostByContent(wg, lstPost);
            int postNumber;
            if (lstPostByContent.size() >= 6) {
                postNumber = 6;
            } else {
                postNumber = lstPostByContent.size();
            }
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
            if (!lstPostByContent.isEmpty() && lstPostByContent.get(0) != null) {
                Post postPrimary = lstPostByContent.get(0);
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
                linkImg.setHref(Constants.PREFIX_POST + postPrimary.getPostSlug());
                linkImg.setParent(divPrimaryImg);

                Image img = new Image();
                String src = postPrimary.getFeaturedImage();
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

                String datePostPrimary = dateFormat.format(postPrimary.getPostDate());
                Label lblPrimaryTime = new Label(datePostPrimary);
                lblPrimaryTime.setClass("icofont-clock-time");
                lblPrimaryTime.setParent(spanTime);

                Div divPrimaryTitle = new Div();
                divPrimaryTitle.setClass("irs-courses-content");
                divPrimaryTitle.setParent(divPrimaryColBlog);

                A aPrimaryLinkTitle = new A();
                aPrimaryLinkTitle.setHref(Constants.PREFIX_POST + postPrimary.getPostSlug());
                aPrimaryLinkTitle.setParent(divPrimaryTitle);

                Label lblPrimaryTitle = new Label(postPrimary.getPostTitle());
                lblPrimaryTitle.setParent(aPrimaryLinkTitle);
            }

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
            if (!lstPostByContent.isEmpty()) {
                for (int i = 1; i <= postNumber; i++) {
                    Post p = lstPostByContent.get(i);
                    Div divPostItem = new Div();
                    divPostItem.setClass("irs-post-item post-item-padding");
                    divPostItem.setParent(irsPost);

                    A linkPostItem = new A();
                    linkPostItem.setHref(Constants.PREFIX_POST + p.getPostSlug());
                    linkPostItem.setParent(divPostItem);

                    Image imgPostItem = new Image();
                    String srcPostItem = p.getFeaturedImage();
                    imgPostItem.setSrc(srcPostItem);
                    imgPostItem.setParent(linkPostItem);

                    A aPostItemTitle = new A();
                    aPostItemTitle.setHref(Constants.PREFIX_POST + p.getPostSlug());
                    aPostItemTitle.setParent(divPostItem);

                    Label lblPostTitle = new Label(p.getPostTitle());
                    lblPostTitle.setClass("post-title");
                    lblPostTitle.setParent(aPostItemTitle);

                    P spanPostTime = new P();
                    spanPostTime.setParent(divPostItem);

                    String datePostPrimary = dateFormat.format(p.getPostDate());
                    Label lblPostItemTime = new Label(datePostPrimary);
                    lblPostItemTime.setClass("time-post");
                    lblPostItemTime.setParent(spanPostTime);
                }
            }

            A linkReadMore = new A();
            linkReadMore.setClass("btn btn-default irs-btn-transparent-two btn-read-more");
            linkReadMore.setHref(Constants.PREFIX_CATEGORY);
            linkReadMore.setParent(irsPost);

            Label lblTitle = new Label("Xem thêm");
            lblTitle.setParent(linkReadMore);
        }

    }

    private void buildMultiCategory(WidgetMapContent wg, List<Post> lstPost) {
        if (wg.getListContent() != null && !wg.getListContent().isEmpty()) {
            List<Post> lstPostByContent = getPostByContent(wg, lstPost);
            int intWidgetContent = wg.getListContent().size();
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

            for (WidgetContent wc : wg.getListContent()) {
                Div divContent = new Div();
                switch (intWidgetContent) {
                    case 1:
                        divContent.setClass("col-md-12 col-sm-12");
                        break;
                    case 2:
                        divContent.setClass("col-md-6 col-sm-12");
                        break;
                    case 3:
                        divContent.setClass("col-md-4 col-sm-12");
                        break;
                    case 4:
                        divContent.setClass("col-md-3 col-sm-12");
                        break;
                    default:
                        divContent.setClass("col-md-3 col-sm-12");
                        break;
                }
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
                String srcPostItem = "";
                if (wc.getWidgetImage() != null && !"".equals(wc.getWidgetImage())) {
                    srcPostItem = wc.getWidgetImage();
                }
                imgPostItem.setSrc(srcPostItem);
                imgPostItem.setParent(divContentPostTitle);

                Span spanTime = new Span();
                spanTime.setParent(divContentPostTitle);
                String strTitle = "";
                if (wc.getWidgetContentName() != null && !"".equals(wc.getWidgetContentName())) {
                    strTitle = wc.getWidgetContentName();
                }
                Label lblTitle = new Label(strTitle);
                lblTitle.setClass("irs-post-item-group-title");
                lblTitle.setParent(spanTime);

                //Build post
                int postNumber = 0;
                if (!lstPostByContent.isEmpty()) {
                    for (int i = 0; i < lstPostByContent.size(); i++) {
                        Post p = lstPostByContent.get(i);
                        if (wc.getWidgetContent().equalsIgnoreCase(String.valueOf(p.getCategoryId()))) {
                            if (postNumber == Constants.MAX_POST_WIDGET_TYPE_MULTI) {
                                break;
                            }
                            postNumber++;
                            Div divContentPostItem = new Div();
                            divContentPostItem.setClass("irs-post-item-3-column");
                            divContentPostItem.setParent(divContentPost);
                            //daond
                            Div divContentPostItemTitle = new Div();
                            divContentPostItemTitle.setClass("irs-post-item-3-column-height");
                            divContentPostItemTitle.setParent(divContentPostItem);

                            A aPostItemTitle = new A();
                            aPostItemTitle.setHref(p.getPostSlug());
                            aPostItemTitle.setParent(divContentPostItemTitle);

                            Label lblPostTitle = new Label(p.getPostTitle());
                            lblPostTitle.setClass("post-title");
                            lblPostTitle.setParent(aPostItemTitle);

                            P spanPostTime = new P();
                            spanPostTime.setParent(divContentPostItem);

                            String datePostPrimary = dateFormat.format(p.getPostDate());
                            Label lblPostItemTime = new Label(datePostPrimary);
                            lblPostItemTime.setClass("time-post");
                            lblPostItemTime.setParent(spanPostTime);
                        }
                    }
                }
                A linkReadMore = new A();
                linkReadMore.setClass("btn btn-default irs-btn-transparent-two btn-read-more");
                String strUrlDetaiMore = "";
                if (wc != null && wc.getDetailMoreSlug() != null && !"".equals(wc.getDetailMoreSlug())) {
                    strUrlDetaiMore = wg.getListContent().get(0).getDetailMoreSlug();
                }
                linkReadMore.setHref(Constants.PREFIX_CATEGORY + strUrlDetaiMore);
                linkReadMore.setParent(divContentPost);

                Label lblMore = new Label("Xem thêm");
                lblMore.setParent(linkReadMore);
            }
        }

    }

    private List<Post> getPostByContent(WidgetMapContent wg, List<Post> lstPost) {
        List<Post> lstPostByContent = new ArrayList<>();
        if (wg != null && wg.getListContent() != null && !wg.getListContent().isEmpty() && lstPost != null && !lstPost.isEmpty()) {
            for (WidgetContent wc : wg.getListContent()) {
                for (Post p : lstPost) {
                    if (Constants.WIDGET_CONTENT_TYPE_CATEGORY.equals(wc.getWidgetType())
                            && Long.parseLong(wc.getWidgetContent()) == p.getCategoryId()) {
                        lstPostByContent.add(p);
                    }

                }
            }
        }
        return lstPostByContent;
    }

}
