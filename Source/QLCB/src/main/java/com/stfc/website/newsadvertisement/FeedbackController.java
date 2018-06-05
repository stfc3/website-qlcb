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
import com.stfc.utils.StringUtils;
import com.stfc.utils.ValidateUtils;
import com.stfc.website.bean.Banner;
import com.stfc.website.bean.Post;
import com.stfc.website.bean.WidgetMapContent;
import com.stfc.website.domain.Category;
import com.stfc.website.domain.Param;
import com.stfc.website.service.WidgetService;
import com.stfc.website.widget.WidgetBuilder;
import com.stfc.website.domain.Feedback;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zhtml.H3;
import org.zkoss.zul.Label;
import org.zkoss.zul.Span;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.A;
import org.zkoss.zul.Image;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

/**
 *
 * @author daond
 */
public class FeedbackController extends SelectorComposer<Div> {

    private static final Logger logger = Logger.getLogger(FeedbackController.class);
    @Wire
    Div indexSlider;

    @Wire
    Div indexNotice;

    @Wire
    Div addWidgetIndexRight;

    @Wire
    Div addWidgetFooter;

    @Wire
    private Textbox name;

    @Wire
    private Textbox email;

    @Wire
    private Textbox phone;

    @Wire
    private Textbox content;

    @Wire
    private Label mesg;

    private WidgetBuilder widgetBuilder = new WidgetBuilder();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

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

        List<WidgetMapContent> vlstWidget = new ArrayList<>(Memory.getListWidgetMapContentCache().values());
        List<Post> lstPostRight = widgetService.getPost(Memory.getLstCategoryId(), Constants.POST_IS_PUBLIC);
        widgetBuilder.buildWidgetRight(vlstWidget, lstPostRight, addWidgetIndexRight, widgetService);

        if (vlstWidget != null && !vlstWidget.isEmpty()) {
            for (WidgetMapContent wg : vlstWidget) {
                if (Constants.WIDGET_TYPE_FOOTER.equals(wg.getWidgetType())
                        && Constants.WIDGET_POSITION_FOOTER.equalsIgnoreCase(wg.getWidgetPosition())) {
                    widgetBuilder.buildFooter(wg, addWidgetFooter);
                }
            }
        }

//        buildNewsHot();
    }

    @Listen("onClick = #btnRegisterStudent")
    public void registerStudent() {
        int validate = validateRegister(name.getValue(), email.getValue(), phone.getValue(), content.getValue());
        switch (validate) {
            case 0:
                //Luu
                Feedback feedback = new Feedback();
                feedback.setName(name.getValue());
                feedback.setEmail(email.getValue());
                feedback.setPhone(phone.getValue());
                feedback.setContent(content.getValue());
                widgetService.insertFeedback(feedback);
                Messagebox.show(Labels.getLabel("enrollstudents.message.content", new String[]{}), Labels.getLabel("enrollstudents.message.title"), Messagebox.OK, Messagebox.INFORMATION);
                break;
            case 1:
                mesg.setValue(Labels.getLabel("enrollstudents.birthday.error"));
                break;
            case 2:
                mesg.setValue(Labels.getLabel("enrollstudents.email.error"));
                break;
            case 3:
                mesg.setValue(Labels.getLabel("enrollstudents.phone.error"));
                break;
            default:
                mesg.setValue(Labels.getLabel("enrollstudents.error"));
                break;
        }

    }

    private int validateRegister(String name, String email, String phone,
            String content) {
        if (!StringUtils.valiString(name) || !StringUtils.valiString(email)
                || !StringUtils.valiString(phone)
                || !StringUtils.valiString(content)) {
            return -1;
        }
        if (!StringUtils.isValidEmailAddress(email)) {
            return 2;
        }

        if (!ValidateUtils.isValidPhone(phone)) {
            return 3;
        }
        return 0;
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
                        divPostTitle.setClass("irs-post-item-post-detail-title-1");
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
