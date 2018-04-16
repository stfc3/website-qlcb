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
import com.stfc.website.bean.WidgetContent;
import com.stfc.website.bean.WidgetMapContent;
import com.stfc.website.domain.Category;
import com.stfc.website.domain.Param;
import com.stfc.website.service.WidgetService;
import com.stfc.website.widget.WidgetBuilder;
import com.stfc.website.domain.Class;
import com.stfc.website.domain.EnrollStudent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zhtml.H3;
import org.zkoss.zul.Label;
import org.zkoss.zul.Span;
import org.zkoss.zhtml.P;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.A;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Html;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

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

    @Wire
    private Textbox name;

    @Wire
    private Datebox birthday;

    @Wire
    private Textbox email;

    @Wire
    private Textbox address;

    @Wire
    private Textbox phone;

    @Wire
    private Combobox cbxClass;

    @Wire
    private Label mesg;

    private WidgetBuilder widgetBuilder = new WidgetBuilder();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @WireVariable
    protected WidgetService widgetService;

    private String urlImage;

    private Common com = new Common();

    private List<Class> lstClass;
    ListModelList<Class> listDataModelFilter;

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
        lstClass = widgetService.getAllClass();
        listDataModelFilter = new ListModelList(lstClass);
        cbxClass.setModel(listDataModelFilter);

        List<WidgetMapContent> vlstWidget = new ArrayList<>(Memory.getListWidgetMapContentCache().values());
//        List<Post> lstPostRight = widgetService.getPost(Memory.getLstCategoryId(), Constants.POST_IS_PUBLIC);
//        buildWidgetRight(vlstWidget, lstPostRight);

        if (vlstWidget != null && !vlstWidget.isEmpty()) {
            for (WidgetMapContent wg : vlstWidget) {
                if (Constants.WIDGET_TYPE_FOOTER.equals(wg.getWidgetType())
                        && Constants.WIDGET_POSITION_FOOTER.equalsIgnoreCase(wg.getWidgetPosition())) {
                    widgetBuilder.buildFooter(wg, addWidgetFooter);
                }
            }
        }

        buildNewsHot();
    }

    @Listen("onClick = #btnRegisterStudent")
    public void registerStudent() {
        Long classId = -1L;
        if (cbxClass.getSelectedItem() != null) {
            classId = cbxClass.getSelectedItem().getValue();
        }
        int validate = validateRegister(name.getValue(), birthday.getValue(), email.getValue(), address.getValue(),
                phone.getValue(), classId);
        switch (validate) {
            case 0:
                //Luu
                EnrollStudent student = new EnrollStudent();
                student.setStudentName(name.getValue());
                student.setBirthday(dateFormat.format(birthday.getValue()));
                student.setAddress(address.getValue());
                student.setEmail(email.getValue());
                student.setPhone(phone.getValue());
                student.setClassId(classId);
                widgetService.insertStudent(student);
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

    private int validateRegister(String name, Date birthday, String email, String address, String phone,
            Long classId) {
        if (!StringUtils.valiString(name) || !StringUtils.valiString(email)
                || !StringUtils.valiString(address) || !StringUtils.valiString(phone)
                || classId == -1L) {
            return -1;
        }
        if (!StringUtils.isValidEmailAddress(String.valueOf(birthday))) {
            try {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                df.format(birthday);
            } catch (Exception e) {
                return 1;
            }
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
