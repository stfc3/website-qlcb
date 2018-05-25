/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;

import com.stfc.backend.domain.Enroll;
import com.stfc.backend.domain.FeedBack;
import com.stfc.backend.service.UtilsService;
import com.stfc.utils.SpringConstant;
import com.stfc.utils.StringUtils;
import com.stfc.website.service.WidgetService;

/**
 *
 * @author admin
 * @since 27/03/2018
 */
public class FeedBackController extends GenericForwardComposer<Component> {

    private static final Logger logger = Logger.getLogger(FeedBackController.class);

    @WireVariable
    protected UtilsService utilsService;
    @WireVariable
    protected WidgetService widgetService;

    // private Listbox resultList;
    private ListModelList<Enroll> listData = new ListModelList<>();

    private ListModelList listSearch;

    @Wire
    private Grid gridFeedBack;

    @WireVariable
    private Textbox txtFeedBackName;

    @WireVariable
    private Textbox txtEmail;

    @WireVariable
    private Textbox txtPhone;

    private ListModelList<com.stfc.website.domain.Class> listModelType = new ListModelList<>();

    /**
     * @return the listModelType
     */
    public ListModelList<com.stfc.website.domain.Class> getListModelType() {
        return listModelType;
    }

    /**
     * @param listModelType the listModelType to set
     */
    public void setListModelType(ListModelList<com.stfc.website.domain.Class> listModelType) {
        this.listModelType = listModelType;
    }

    public ListModelList<Enroll> getListData() {
        return listData;
    }

    public void setListData(ListModelList<Enroll> listData) {
        this.listData = listData;
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        utilsService = (UtilsService) SpringUtil.getBean(SpringConstant.UTILS_SERVICE);
        widgetService = (WidgetService) SpringUtil.getBean(SpringConstant.WIDGET_SERVICE);
        search();

    }

    public void onSearch(ForwardEvent event) {
        search();
    }

    private void search() {
        FeedBack feedBack = new FeedBack();
        if (StringUtils.valiString(txtFeedBackName.getValue())) {
            feedBack.setName(txtFeedBackName.getValue().trim());
        }
        if (StringUtils.valiString(txtPhone.getValue())) {
            feedBack.setPhone(txtPhone.getValue().trim());
        }
        if (StringUtils.valiString(txtEmail.getValue())) {
            feedBack.setEmail(txtEmail.getValue().trim());
        }

        List<FeedBack> listFeedBack = utilsService.onSearchFeedBack(feedBack);

        listSearch = new ListModelList(listFeedBack);
        listSearch.setMultiple(true);
        gridFeedBack.setModel(listSearch);

    }

    public void onRefresh(ForwardEvent event) {
        txtFeedBackName.setValue("");
        txtPhone.setValue("");
        txtEmail.setValue("");
    }

}
