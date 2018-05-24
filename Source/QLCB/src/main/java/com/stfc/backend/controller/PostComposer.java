package com.stfc.backend.controller;

import com.stfc.backend.domain.Post;
import com.stfc.backend.service.PostService;
import com.stfc.utils.SpringConstant;
import com.stfc.utils.StringUtils;
import java.util.Date;
import java.util.List;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;

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

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
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
            status =Integer.valueOf(postStatus.getSelectedItem().getValue());
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

}
