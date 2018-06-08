/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.stfc.backend.domain.Enroll;
import com.stfc.backend.service.UtilsService;
import com.stfc.utils.Constants;
import com.stfc.utils.SpringConstant;
import com.stfc.utils.StringUtils;
import com.stfc.website.domain.Class;
import com.stfc.website.service.WidgetService;

/**
 *
 * @author viettx
 * @since 08/06/2018
 */
public class ClassController extends GenericForwardComposer<Component> {

    private static final Logger logger = Logger.getLogger(ClassController.class);

    @WireVariable
    protected UtilsService utilsService;
    @WireVariable
    protected WidgetService widgetService;

    private ListModelList<Enroll> listData = new ListModelList<>();

    private ListModelList<Class> listSearch;

    @Wire
    private Grid gridClass;

    private Window classManager;

    @WireVariable
    private Textbox txtClassName;
    @WireVariable
    private Combobox cbxStatus;
    @WireVariable
    private Textbox txtClassAddName;
    @WireVariable
    private Intbox txtOrder;
    @WireVariable
    private Textbox txtDescription;
    @WireVariable
    private Longbox txtClassId;
    @WireVariable
    private Intbox txtStatus;
    @Wire
    private Window addClass;
    private List<com.stfc.website.domain.Class> listClass;

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
        com.stfc.website.domain.Class classItem = new com.stfc.website.domain.Class();
        if (StringUtils.valiString(txtClassName.getValue())) {
            classItem.setClassName(txtClassName.getValue().trim());
        }
        if (!"-1".equals(cbxStatus.getSelectedItem().getValue())) {
            classItem.setStatus(Integer.valueOf(cbxStatus.getSelectedItem().getValue()));
        }
        listClass = utilsService.search(classItem);

        listSearch = new ListModelList<Class>(listClass);
        listSearch.setMultiple(true);
        gridClass.setModel(listSearch);

    }

    public void onRefresh(ForwardEvent event) {
        txtClassName.setValue("");
        cbxStatus.setSelectedIndex(0);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void onLock(ForwardEvent event) {
        Row rowSelected = (Row) event.getOrigin().getTarget().getParent().getParent();
        com.stfc.website.domain.Class classValue = rowSelected.getValue();
        String status;
        if (classValue.getStatus() == 1) {
            status = "user.lock";
            classValue.setStatus(0);
        } else {
            status = "user.unlock";
            classValue.setStatus(1);
        }

        Messagebox.show(
                Labels.getLabel("user.comfirm.lock",
                        new String[]{Labels.getLabel(status).toLowerCase(), classValue.getClassName()}),
                Labels.getLabel("user.comfirm"), Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
                new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                // TODO Auto-generated method stub
                if (Messagebox.ON_YES.equals(event.getName())) {
                    utilsService.save(classValue);
                    search();
                    Messagebox.show(Labels.getLabel(status) + " " + classValue.getClassName(),
                            Labels.getLabel("NOTIFICATION"), Messagebox.OK, Messagebox.INFORMATION);
                }
            }

        });
    }

    public void onAdd(ForwardEvent event) {
        com.stfc.website.domain.Class classItem = new com.stfc.website.domain.Class();
        classItem.setStatus(1);
        Map<String, Object> arguments = new HashMap();
        arguments.put("classItem", classItem);
        final Window windownUpload = (Window) Executions.createComponents("/backend/manager/include/addClass.zul",
                classManager, arguments);
        windownUpload.doModal();
        windownUpload.setBorder(true);
        windownUpload.setBorder("normal");
        windownUpload.setClosable(true);

    }

    public void onEdit(ForwardEvent event) {
        Row rowSelected = (Row) event.getOrigin().getTarget().getParent().getParent();

        com.stfc.website.domain.Class classValue = rowSelected.getValue();

        Map<String, Object> arguments = new HashMap();
        arguments.put("classItem", classValue);
        final Window windownUpload = (Window) Executions.createComponents("/backend/manager/include/addClass.zul",
                classManager, arguments);
        windownUpload.doModal();
        windownUpload.setBorder(true);
        windownUpload.setBorder("normal");
        windownUpload.setClosable(true);
    }

    @Listen("onClick = #btnSave")
    public void onSave() {
        String className = txtClassAddName.getValue().trim();
        Integer intOrder = txtOrder.getValue();
        String description = txtDescription.getValue().trim();
        Long classId = txtClassId.getValue();
        Integer status = txtStatus.getValue();
        if (!StringUtils.valiString(className)) {

            Clients.showNotification(Labels.getLabel("class.name.empty"), Clients.NOTIFICATION_TYPE_ERROR,
                    txtClassAddName, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
            txtClassAddName.focus();
            return;
        }
        Class classValue = new Class();
        classValue.setClassName(className);
        classValue.setClassId(classId);
        classValue.setClassOrder(intOrder);
        classValue.setStatus(status);
        classValue.setDescription(description);
        classValue.setCreateDate(new Date());

        utilsService.save(classValue);

        Events.sendEvent("onClick", (Button) ((Window) addClass.getParent()).getFellow("reloadData"), null);
        addClass.detach();

    }

    public void onClick$btnCancel() {
        addClass.detach();
    }

    public void onClick$reloadData() {
        search();
    }

}
