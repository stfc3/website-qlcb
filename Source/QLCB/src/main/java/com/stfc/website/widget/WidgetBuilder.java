/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.widget;

import com.stfc.website.bean.Banner;
import com.stfc.website.bean.WidgetContent;
import com.stfc.website.bean.WidgetMapContent;
import java.util.List;
import org.zkoss.zhtml.H4;
import org.zkoss.zhtml.P;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.A;
import org.zkoss.zul.Div;
import org.zkoss.zul.Html;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;

/**
 *
 * @author daond
 */
public class WidgetBuilder {

    public void buildSlider(List<Banner> plstBanner, Component indexSlider) {
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
    }
    
    public void buildFooter(WidgetMapContent wg, Component addWidgetIndex) {
        if (wg.getListContent() != null && !wg.getListContent().isEmpty()) {
            int intWidgetContent = wg.getListContent().size();
            Div divFooter = new Div();
            divFooter.setClass("irs-footer-field");
            divFooter.setParent(addWidgetIndex);

            Div divContainer = new Div();
            divContainer.setSclass("container");
            divContainer.setParent(divFooter);

            Div divRow = new Div();
            divRow.setSclass("row animatedParent animateOnce animateOnce");
            divRow.setParent(divContainer);

            for (WidgetContent wc : wg.getListContent()) {
                Div divColumn3 = new Div();
                divColumn3.setSclass("col-md-4");
                switch (intWidgetContent) {
                    case 1:
                        divColumn3.setClass("col-md-12");
                        break;
                    case 2:
                        divColumn3.setClass("col-md-6");
                        break;
                    case 3:
                        divColumn3.setClass("col-md-4");
                        break;
                    case 4:
                        divColumn3.setClass("col-md-3");
                        break;
                    default:
                        divColumn3.setSclass("col-md-4");
                        break;
                }
                divColumn3.setParent(divRow);

                Div irsFooer = new Div();
                irsFooer.setClass("irs-footer-link");
                irsFooer.setParent(divColumn3);

                H4 title = new H4();
                title.setParent(irsFooer);
                String strTitle = "";
                if (wc.getWidgetContentName() != null && !"".equals(wc.getWidgetContentName())) {
                    strTitle = wc.getWidgetContentName();
                }
                Label lblFunctionName = new Label(strTitle);
                lblFunctionName.setClass("irs-footer-heading");
                lblFunctionName.setParent(title);

                P spanFooterContent = new P();
                spanFooterContent.setParent(irsFooer);

                Html htmPostItemTime = new Html();
                String strContent = "";
                if (wc.getWidgetContent() != null && !"".equals(wc.getWidgetContent())) {
                    strContent = wc.getWidgetContent();
                }
                htmPostItemTime.setContent(strContent);
                htmPostItemTime.setParent(spanFooterContent);

            }
        }
    }
}