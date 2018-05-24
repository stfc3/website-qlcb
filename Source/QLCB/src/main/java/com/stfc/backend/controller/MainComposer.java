package com.stfc.backend.controller;

import com.stfc.utils.Constants;
import com.stfc.website.bean.UserToken;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menuitem;

public class MainComposer extends GenericForwardComposer<Component> {

    @Wire
    Menuitem category, document, user, menu, addPost, listPost, widget, banner;
    @Wire
    Menu post;
    @Wire
    Label breadcrumb;
    @Wire
    Include content;
    UserToken userToken;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        Executions.getCurrent().getDesktop().getRequestPath();
        if (session.getAttribute(Constants.USER_TOKEN) == null) {
            Executions.sendRedirect(Constants.PAGE_LOGIN);
        } else {
            userToken = (UserToken) session.getAttribute(Constants.USER_TOKEN);
            loadMenu();
        }
        
    }

    private void loadMenu() {
        if (Constants.ROLE_EDITOR.equals(userToken.getRole()) || Constants.ROLE_AUTHOR.equals(userToken.getRole())
                || Constants.ROLE_CONTRIBUTOR.equals(userToken.getRole())) {
            post.setVisible(true);
            addPost.setVisible(true);
            listPost.setVisible(true);
        }
        if (Constants.ROLE_EDITOR.equals(userToken.getRole())) {
            category.setVisible(true);
        }
        if (Constants.ROLE_ADMIN.equals(userToken.getRole())) {
            category.setVisible(true);
            document.setVisible(true);
            user.setVisible(true);
            menu.setVisible(true);
            post.setVisible(true);
            addPost.setVisible(true);
            listPost.setVisible(true);
            widget.setVisible(true);
            banner.setVisible(true);
        }
    }

    public void onClick$category() {
        content.setSrc(Constants.PAGE_CATEGORY);
        breadcrumb.setValue(category.getLabel());
    }

    public void onClick$addPost() {
        content.setSrc(Constants.PAGE_ADD_POST);
        breadcrumb.setValue(addPost.getLabel());
    }

    public void onClick$listPost() {
        content.setSrc(Constants.PAGE_LIST_POST);
        breadcrumb.setValue(listPost.getLabel());
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
