package com.stfc.backend.controller;

import com.stfc.utils.Constants;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkmax.zul.Navitem;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;

public class MainComposer extends GenericForwardComposer<Component> {

    @Wire
    Navitem category, document, user, menu, post, widget, banner;
    @Wire
    Label breadcrumb;
    @Wire
    Include content;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
    }

    public void onClick$category() {
        content.setSrc(Constants.PAGE_CATEGORY);
        breadcrumb.setValue(category.getLabel());
    }
    public void onClick$post() {
        content.setSrc(Constants.PAGE_POST);
        breadcrumb.setValue(post.getLabel());
    }
    public void onClick$user() {
        content.setSrc(Constants.PAGE_USER);
        breadcrumb.setValue(user.getLabel());
    }
    public void onClick$menu() {
        content.setSrc(Constants.PAGE_MENU);
        breadcrumb.setValue(menu.getLabel());
    }
    public void onClick$document() {
        content.setSrc(Constants.PAGE_DOCUMENT);
        breadcrumb.setValue(document.getLabel());
    }
    public void onClick$widget() {
        content.setSrc(Constants.PAGE_WIDGET);
        breadcrumb.setValue(widget.getLabel());
    }
    public void onClick$banner() {
        content.setSrc(Constants.PAGE_BANNER);
        breadcrumb.setValue(banner.getLabel());
    }

}
