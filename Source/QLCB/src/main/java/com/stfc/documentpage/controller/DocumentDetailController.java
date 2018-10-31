/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.documentpage.controller;

import com.stfc.website.*;
import com.stfc.utils.Common;
import com.stfc.utils.Constants;
import com.stfc.utils.SpringConstant;
import com.stfc.website.bean.Banner;
import com.stfc.website.bean.Document;
import com.stfc.website.bean.Post;
import com.stfc.website.bean.WidgetMapContent;
import com.stfc.website.domain.Category;
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
import org.zkoss.zul.Label;
import org.zkoss.zul.Span;
import org.zkoss.zhtml.P;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zss.ui.Spreadsheet;
import org.zkoss.zul.Iframe;

/**
 *
 * @author daond
 */
public class DocumentDetailController extends SelectorComposer<Div> {

    private static final Logger logger = Logger.getLogger(DocumentDetailController.class);
    @Wire
    Div indexSlider;

    @Wire
    Div indexNotice;

    @Wire
    Div addWidgetIndexRight;

    @Wire
    Div documentPage;

    @Wire
    Div addWidgetFooter;

    private WidgetBuilder widgetBuilder = new WidgetBuilder();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
    private SimpleDateFormat dateFormatRelated = new SimpleDateFormat("dd/MM/yyyy");

    @WireVariable
    protected WidgetService widgetService;
    private String urlImage;

    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp);
        logger.info("======>URL from Executions: " + Executions.getCurrent().getAttribute(Constants.STFC_ID_ATTRIBUTE));
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
        Long documentId = Long.valueOf(String.valueOf(Executions.getCurrent().getAttribute(Constants.STFC_ID_ATTRIBUTE)));
        List<Document> lstDocument = widgetService.getDocumentById(documentId);
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
        buildInternal(lstDocument);
        List<Post> lstPostRight = widgetService.getPost(Memory.getLstCategoryId(), Constants.POST_IS_PUBLIC);
        widgetBuilder.buildWidgetRight(vlstWidget, lstPostRight, addWidgetIndexRight, widgetService);
    }

    private void buildInternal(List<Document> plstDocument) {
        Div postMain = new Div();
        postMain.setClass("inner-page-content left-img");
        postMain.setParent(documentPage);

        Div divContainer = new Div();
        divContainer.setClass("container");
        divContainer.setParent(postMain);

        Div divRow = new Div();
        divRow.setClass("row");
        divRow.setParent(divContainer);

        if (plstDocument != null && !plstDocument.isEmpty()) {
            Div divColMd8 = new Div();
            divColMd8.setClass("col-md-8");
            divColMd8.setParent(divRow);

            //Post content
            Div irsBlogSingle = new Div();
            irsBlogSingle.setClass("irs-blog-single-col");
            irsBlogSingle.setParent(divColMd8);

            Div irsBlogCol = new Div();
            irsBlogCol.setClass("irs-blog-col");
            irsBlogCol.setParent(irsBlogSingle);

            //dongdv
//            H1 titlePost = new H1();
//            titlePost.setParent(irsBlogCol);
//
//            Span spanTitle = new Span();
//            spanTitle.setClass("pTitle");
//            spanTitle.setParent(titlePost);
            Div titlePost = new Div();
            titlePost.setClass("post-title-detail");
            titlePost.setParent(irsBlogCol);

            Span spanTitle = new Span();
            spanTitle.setClass("post-title-detail");
            spanTitle.setParent(titlePost);
            String postTitle = "";
//            if (plstDocument.get(0).getDocumentType() == 2) {
            if (plstDocument.get(0).getDocumentName() != null && !"".equals(plstDocument.get(0).getDocumentName())) {
                postTitle = plstDocument.get(0).getDocumentName();
            }
//            } else {
//                if (plstDocument.get(0).getCategoryName() != null && !"".equals(plstDocument.get(0).getCategoryName())) {
//                    postTitle = plstDocument.get(0).getCategoryName();
//                }
//            }
            Label lblPostTitle = new Label(postTitle);
            lblPostTitle.setParent(spanTitle);

            P postContent = new P();
            postContent.setParent(irsBlogCol);

            Span spanContent = new Span();
            spanContent.setClass("pBody");
            spanContent.setParent(postContent);
            String strDocumentPath = "";
            if (plstDocument.get(0).getDocumentType() == 2) {
                Iframe htmPostContent = new Iframe();
                htmPostContent.setClass("documentIframe");
                if (plstDocument.get(0).getDocumentPath() != null && !"".equals(plstDocument.get(0).getDocumentPath())) {
                    strDocumentPath = plstDocument.get(0).getDocumentPath();
                }
                htmPostContent.setSrc(strDocumentPath);
                htmPostContent.setParent(spanContent);
            } else {
                Spreadsheet htmPostContent = new Spreadsheet();
                htmPostContent.setShowSheetbar(true);
                htmPostContent.setMaxrows(10000);
                htmPostContent.setMaxcolumns(5000);
                htmPostContent.setPreloadColumnSize(100);
                htmPostContent.setPreloadRowSize(100);
                htmPostContent.setWidth("100%");
                htmPostContent.setHeight("600px");

                htmPostContent.setClass("documentIframe");
                if (plstDocument.get(0).getDocumentPath() != null && !"".equals(plstDocument.get(0).getDocumentPath())) {
                    strDocumentPath = plstDocument.get(0).getDocumentPath();
                }
                htmPostContent.setSrc(strDocumentPath);
                htmPostContent.setParent(spanContent);
            }

        }
    }
}
