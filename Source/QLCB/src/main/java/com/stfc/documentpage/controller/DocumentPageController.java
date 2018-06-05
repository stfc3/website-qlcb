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
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zhtml.H2;
import org.zkoss.zhtml.H4;
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
public class DocumentPageController extends SelectorComposer<Div> {

    private static final Logger logger = Logger.getLogger(DocumentPageController.class);
    @Wire
    Div indexSlider;

    @Wire
    Div indexNotice;

    @Wire
    Div documentPage;

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
        List<Document> lstDocument = widgetService.getDocument();
        List<Category> lstCategoryDoc = widgetService.getCategoryDocument();

        List<WidgetMapContent> vlstWidget = new ArrayList<>(Memory.getListWidgetMapContentCache().values());
        if (vlstWidget != null && !vlstWidget.isEmpty()) {
            for (WidgetMapContent wg : vlstWidget) {
                if (Constants.WIDGET_TYPE_FOOTER.equals(wg.getWidgetType())
                        && Constants.WIDGET_POSITION_FOOTER.equalsIgnoreCase(wg.getWidgetPosition())) {
                    widgetBuilder.buildFooter(wg, addWidgetFooter);
                }
            }
        }
        buildInternal(lstCategoryDoc, lstDocument);
        List<Post> lstPostRight = widgetService.getPost(Memory.getLstCategoryId(), Constants.POST_IS_PUBLIC);
        widgetBuilder.buildWidgetRight(vlstWidget, lstPostRight, addWidgetIndexRight, widgetService);
    }

    private void buildInternal(List<Category> plstCategory, List<Document> plstDocument) {
//        Div postMain = new Div();
//        postMain.setClass("inner-page-content left-img");
//        postMain.setParent(documentPage);
//
//        Div divContainer = new Div();
//        divContainer.setClass("container");
//        divContainer.setParent(postMain);
//
//        Div divRow = new Div();
//        divRow.setClass("row");
//        divRow.setParent(divContainer);

        if (plstCategory != null && !plstCategory.isEmpty()) {
//            for (Category wmc : plstCategory) {
//            Div divColMd8 = new Div();
//            divColMd8.setClass("col-md-8");
//            divColMd8.setParent(divRow);

            //Post content
            if (plstCategory != null && !plstCategory.isEmpty()) {
                for (Category wc : plstCategory) {
                    Div rowTitle = new Div();
                    rowTitle.setClass("border-bottom-title-category");
                    rowTitle.setParent(documentPage);

                    Div titleCategory = new Div();
                    titleCategory.setClass("title-category");
                    titleCategory.setParent(rowTitle);

                    H2 h2Title = new H2();
                    h2Title.setParent(titleCategory);

                    A hotnew = new A();
                    hotnew.setParent(h2Title);

                    Span spanTitleCategory = new Span();
                    spanTitleCategory.setParent(hotnew);
                    String widgetTitle = wc.getCategoryName();

                    Label lblFunctionName = new Label(widgetTitle);

                    if (plstDocument != null && !plstDocument.isEmpty()) {
                        for (Document p1 : plstDocument) {
                            if (wc.getCategoryId() == p1.getCategoryId()) {
                                lblFunctionName.setParent(spanTitleCategory);

                                Div divContentPostItem = new Div();
                                divContentPostItem.setClass("irs-post-item-3-column-related");
                                divContentPostItem.setParent(documentPage);
                                A aPostItemTitle = new A();
                                aPostItemTitle.setHref(p1.getDocumentPath());
                                aPostItemTitle.setParent(divContentPostItem);

                                H4 h4Post = new H4();
                                h4Post.setParent(aPostItemTitle);
                                String titleRelated = "<i class='fa fa-angle-double-right'></i> " + p1.getDocumentName() + "<span style='font-size:11px; color: #0d4e96;'> (" + p1.getAuthor() + ") </span>";
                                Html htmPostItem = new Html();
                                htmPostItem.setContent(titleRelated);
                                htmPostItem.setParent(h4Post);

                                P spanPostTime = new P();
                                spanPostTime.setParent(divContentPostItem);
                            }
                        }
                    }
                }
            }
//            }
        }

    }

}
