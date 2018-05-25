/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.stfc.backend.domain.Enroll;
import com.stfc.backend.service.UtilsService;
import com.stfc.utils.SpringConstant;
import com.stfc.utils.StringUtils;
import com.stfc.website.service.WidgetService;

/**
 *
 * @author admin
 * @since 27/03/2018
 */
public class EnrollController extends GenericForwardComposer<Component> {
    
    private static final Logger logger = Logger.getLogger(EnrollController.class);
    
    @WireVariable
    protected UtilsService utilsService;
    @WireVariable
    protected WidgetService widgetService;

    // private Listbox resultList;
    private ListModelList<Enroll> listData = new ListModelList<>();
    
    private ListModelList listSearch;
    
    @Wire
    private Grid gridEnroll;
    
    private Window enrollManager;
    
    @WireVariable
    private Textbox txtStudentName;
    
    @WireVariable
    private Textbox txtEmail;
    
    @WireVariable
    private Textbox txtPhone;
    
    @WireVariable
    private Combobox cbxClass;
    
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
        
        List<com.stfc.website.domain.Class> listClass = widgetService.getAllClass();
        com.stfc.website.domain.Class c = new com.stfc.website.domain.Class();
        c.setClassId(-1l);
        c.setClassName(Labels.getLabel("option"));
        listClass.add(0, c);
        
        listModelType = new ListModelList<>(listClass);
        cbxClass.setModel(listModelType);
    }
    
    public void onSearch(ForwardEvent event) {
        search();
    }
    
    private void search() {
        Enroll enroll = new Enroll();
        if (StringUtils.valiString(txtStudentName.getValue())) {
            enroll.setStudentName(txtStudentName.getValue().trim());
        }
        if (StringUtils.valiString(txtPhone.getValue())) {
            enroll.setPhone(txtPhone.getValue().trim());
        }
        if (StringUtils.valiString(txtEmail.getValue())) {
            enroll.setEmail(txtEmail.getValue().trim());
        }
        
        if (cbxClass.getSelectedItem() != null && !cbxClass.getSelectedItem().getValue().equals(-1l)) {
            enroll.setClassId(cbxClass.getSelectedItem().getValue());
        }
        List<Enroll> listEnroll = utilsService.searchEnroll(enroll);
        
        listSearch = new ListModelList(listEnroll);
        listSearch.setMultiple(true);
        gridEnroll.setModel(listSearch);
        
    }
    
    public void onRefresh(ForwardEvent event) {
        txtStudentName.setValue("");
        txtPhone.setValue("");
        txtEmail.setValue("");
        cbxClass.setSelectedIndex(0);
    }
    
}
