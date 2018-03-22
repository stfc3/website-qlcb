/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website;

import com.stfc.utils.Constants;
import com.stfc.website.bean.Banner;
import com.stfc.website.bean.WidgetMapContent;
import com.stfc.website.widget.WidgetBuilder;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zhtml.H1;
import org.zkoss.zhtml.H3;
import org.zkoss.zul.Label;
import org.zkoss.zul.Span;
import org.zkoss.zhtml.P;
import org.zkoss.zul.A;
import org.zkoss.zul.Image;

/**
 *
 * @author daond
 */
public class PostController extends SelectorComposer<Div> {

    private static final Logger logger = Logger.getLogger(PostController.class);
    @Wire
    Div indexSlider;

    @Wire
    Div postDetail;

    @Wire
    Div addWidgetFooter;

    private WidgetBuilder widgetBuilder = new WidgetBuilder();

    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp);
        List<Banner> lstBanner = new ArrayList<>(Memory.getListBannerCache().values());
        if (lstBanner != null && !lstBanner.isEmpty()) {
            widgetBuilder.buildBanner(lstBanner, indexSlider);
        }
        buildPostDetail();
        List<WidgetMapContent> vlstWidget = new ArrayList<>(Memory.getListWidgetMapContentCache().values());
        if (vlstWidget != null && !vlstWidget.isEmpty()) {
            for (WidgetMapContent wg : vlstWidget) {
                if (Constants.WIDGET_TYPE_FOOTER.equals(wg.getWidgetType())
                        && Constants.WIDGET_POSITION_FOOTER.equalsIgnoreCase(wg.getWidgetPosition())) {
                    widgetBuilder.buildFooter(wg, addWidgetFooter);
                }
            }
        }
    }

    private void buildPostDetail() {
        Div postMain = new Div();
        postMain.setClass("inner-page-content left-img");
        postMain.setParent(postDetail);

        Div divContainer = new Div();
        divContainer.setClass("container");
        divContainer.setParent(postMain);

        Div divRow = new Div();
        divRow.setClass("row");
        divRow.setParent(divContainer);

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

        H1 titlePost = new H1();
        titlePost.setParent(irsBlogCol);

        Span spanTitle = new Span();
        spanTitle.setClass("pTitle");
        spanTitle.setParent(titlePost);
        String postTitle = "Gặp mặt đầu xuân Mậu Tuất 2018";
        Label lblPostTitle = new Label(postTitle);
        lblPostTitle.setParent(spanTitle);

        P postContent = new P();
        postContent.setParent(irsBlogCol);

        Span spanContent = new Span();
        spanContent.setClass("pBody");
        spanContent.setParent(postContent);
        String strPostContent = "Sáng ngày 21/2/2018, Trường Cán bộ quản lý GTVT đã long trọng tổ chức buổi gặp mặt và chúc Tết các cán bộ, công chức, viên chức, Nhà trường nhân dịp đầu năm mới";
        Label lblPostContent = new Label(strPostContent);
        lblPostContent.setParent(spanContent);

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

        Span spanNewPost = new Span();
        spanNewPost.setClass("irs-sidebar-title");
        spanNewPost.setParent(newPost);
        String postNewPos = "Tin tiêu điểm";
        Label lblNewPos = new Label(postNewPos);
        lblNewPos.setParent(spanNewPost);

        Div divPostItem = new Div();
        divPostItem.setClass("irs-post-item post-item-padding");
        divPostItem.setParent(irsPost);

        A linkPostItem = new A();
        linkPostItem.setHref("");
        linkPostItem.setParent(divPostItem);

        Image imgPostItem = new Image();
        String srcPostItem = "media/tintuc_sukien/1/1.jpg";
        imgPostItem.setSrc(srcPostItem);
        imgPostItem.setParent(linkPostItem);

        A aPostItemTitle = new A();
        aPostItemTitle.setHref("");
        aPostItemTitle.setParent(divPostItem);

        Label lblPostTitleItem = new Label("Hội nghị nghiệm thu đề tài Nghiên cứu khoa học");
        lblPostTitleItem.setClass("post-title");
        lblPostTitleItem.setParent(aPostItemTitle);

        P spanPostTime = new P();
        spanPostTime.setParent(divPostItem);

//        String datePostPrimary = dateFormat.format(p.getPostDate());
        Label lblPostItemTime = new Label("21/03/2018");
        lblPostItemTime.setClass("time-post");
        lblPostItemTime.setParent(spanPostTime);
    }
}
