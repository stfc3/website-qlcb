package com.stfc.backend.controller;

import com.stfc.backend.domain.Post;
import com.stfc.backend.service.PostService;
import com.stfc.utils.Constants;
import com.stfc.utils.SpringConstant;
import com.stfc.utils.StringUtils;
import java.util.Date;
import java.util.List;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class PostComposer extends SelectorComposer<Component> {

    @WireVariable
    PostService postService;
    @Wire
    Combobox postStatus;
    @Wire
    Textbox postTitle;
    @Wire
    Datebox fromDate, toDate;
    @Wire
    Grid listPost;
    @Wire
    Window winPost;
    private Session session;
    

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        session = (Session) Sessions.getCurrent();
        postService = (PostService) SpringUtil.getBean(SpringConstant.POST_SERVICE);

    }

    @Listen("onClick = #btnSearch")
    public void search() {
        String strPostTile = null;
        Integer status = null;
        Date dteFromDate = null;
        Date dteToDate = null;
        if (StringUtils.valiString(postTitle.getValue())) {
            strPostTile = postTitle.getValue();
        }
        if (!Labels.getLabel("option").equals(postStatus.getValue())) {
            status = Integer.valueOf(postStatus.getSelectedItem().getValue());
        }
        if (fromDate.getValue() != null) {
            dteFromDate = fromDate.getValue();
        }
        if (toDate.getValue() != null) {
            dteToDate = toDate.getValue();
        }

        List<Post> listData = postService.searchPost(strPostTile, status, dteFromDate, dteToDate);
        ListModelList model = new ListModelList(listData);
        listPost.setModel(model);
    }

    @Listen("onClick = #btnReset")
    public void clearInput() {
        postTitle.setValue("");
        postStatus.setValue(Labels.getLabel("option"));
        fromDate.setValue(null);
        toDate.setValue(null);
    }

    @Listen("onEdit = #listPost")
    public void onEdit(ForwardEvent event) {
        Row rowSelected = (Row) event.getOrigin().getTarget().getParent().getParent();
        Post post = rowSelected.getValue();
        session.setAttribute(Constants.STFC_POST_ATTRIBUTE, post);
        Menuitem addPost=(Menuitem) (winPost.getRoot().getFellow("addPost"));
        Include content=(Include) (winPost.getRoot().getFellow("content"));
        Label breadcrumb=(Label) (winPost.getRoot().getFellow("breadcrumb"));
        
        content.setSrc(Constants.PAGE_ADD_POST);
        breadcrumb.setValue(addPost.getLabel());
        
    }
    @Listen("onPublish = #listPost")
    public void onPublish(ForwardEvent event) {
        Row rowSelected = (Row) event.getOrigin().getTarget().getParent().getParent();
        Post post = rowSelected.getValue();
        post.setPostDate(new Date());
        post.setModifiedDate(new Date());
        post.setPostStatus(3);
        postService.update(post);
    }
    @Listen("onDelete = #listPost")
    public void onDelete(ForwardEvent event) {
        Row rowSelected = (Row) event.getOrigin().getTarget().getParent().getParent();
        Post post = rowSelected.getValue();
        post.setModifiedDate(new Date());
        post.setPostStatus(0);
        postService.update(post);
    }

}
