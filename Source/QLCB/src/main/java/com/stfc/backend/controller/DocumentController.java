/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.stfc.backend.domain.Banner;
import com.stfc.backend.domain.Document;
import com.stfc.backend.entity.Data;
import com.stfc.backend.service.BannerService;
import com.stfc.backend.service.DocumentService;
import com.stfc.utils.Constant;
import com.stfc.utils.FunctionUtil;
import com.stfc.utils.SpringConstant;
import com.stfc.website.domain.Category;
import com.stfc.website.service.WidgetService;

/**
 *
 * @author admin
 * @since 27/03/2018
 */
public class DocumentController extends GenericForwardComposer<Component> {

    private static final Logger logger = Logger.getLogger(DocumentController.class);

    @WireVariable
    protected DocumentService documentService;

    @WireVariable
    protected WidgetService widgetService;

    // private Listbox resultList;
    private ListModelList<Document> listData = new ListModelList<>();

    private ListModelList listSearch;

    @Wire
    private Grid gridDocument;

    private Window documentManager;

    @WireVariable
    private Textbox txtDocName;

    @WireVariable
    private Combobox cbxStatus;

    @WireVariable
    private Combobox cbCategory;

    @WireVariable
    private Combobox cbType;

    private ListModelList<Object> listModelType = new ListModelList<>();
    private ListModelList<Category> listModelCategory = new ListModelList<>();

    /**
     * @return the listModelType
     */
    public ListModelList<Object> getListModelType() {
        return listModelType;
    }

    /**
     * @param listModelType the listModelType to set
     */
    public void setListModelType(ListModelList<Object> listModelType) {
        this.listModelType = listModelType;
    }

    public ListModelList<Document> getListData() {
        return listData;
    }

    public void setListData(ListModelList<Document> listData) {
        this.listData = listData;
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        documentService = (DocumentService) SpringUtil.getBean(SpringConstant.DOCUMENT_SERVICE);
        widgetService = (WidgetService) SpringUtil.getBean(SpringConstant.WIDGET_SERVICE);
        search();
        //
        List<Data> listDataType = FunctionUtil.createListTypeDocument();
        List<Category> listDataCategory = widgetService.getAllCategory();
        Category catDefault = new Category();
        catDefault.setCategoryId(-1l);
        catDefault.setCategoryName(Labels.getLabel("option"));
        listDataCategory.add(0, catDefault);
        listModelCategory = new ListModelList<>(listDataCategory);
        cbCategory.setModel(listModelCategory);

        listModelType = new ListModelList<>(listDataType);
        cbType.setModel(listModelType);
    }

    public void onSearch(ForwardEvent event) {
        search();
    }

    private void search() {
        Document document = new Document();
        if (txtDocName != null) {
            document.setDocumentName(txtDocName.getValue());
        }
        if (cbType != null && cbType.getSelectedItem() != null && !Constant.DEFAULT_VALUE.equals(cbType.getSelectedItem().getValue())) {
            document.setDocumentType(cbType.getSelectedItem().getValue());
        }
        if (cbCategory != null && cbCategory.getSelectedItem() != null
                && !Constant.DEFAULT_VALUE.equals(cbCategory.getSelectedItem().getValue())) {
            document.setCategoryId(cbCategory.getSelectedItem().getValue());
        }
        if (cbxStatus != null && !Constant.DEFAULT_VALUE.equals(cbxStatus.getSelectedItem().getValue())) {
            document.setStatus(Integer.valueOf(cbxStatus.getSelectedItem().getValue()));
        }
        List<Document> listDocument = documentService.search(document);

        listSearch = new ListModelList(listDocument);
        listSearch.setMultiple(true);
        gridDocument.setModel(listSearch);

    }

    public void onClick$btnAdd() {
        Document document = new Document();
        Map<String, Object> arguments = new HashMap();
        arguments.put("document", document);
        final Window windownUpload = (Window) Executions.createComponents("/backend/manager/include/document_add.zul",
                documentManager, arguments);
        windownUpload.doModal();
        windownUpload.setBorder(true);
        windownUpload.setBorder("normal");
        windownUpload.setClosable(true);

    }

    public void onClick$btnReset() {
        txtDocName.setValue("");
        cbCategory.setSelectedIndex(0);
        cbType.setSelectedIndex(0);
        cbxStatus.setSelectedIndex(0);
    }

    public void onEdit(ForwardEvent event) {
        Row rowSelected = (Row) event.getOrigin().getTarget().getParent().getParent();

        Document document = rowSelected.getValue();

        Map<String, Object> arguments = new HashMap();
        arguments.put("document", document);
        final Window windownUpload = (Window) Executions.createComponents("/backend/manager/include/document_add.zul",
                documentManager, arguments);
        windownUpload.doModal();
        windownUpload.setBorder(true);
        windownUpload.setBorder("normal");
        windownUpload.setClosable(true);
    }

    public void onLock(ForwardEvent event) {
        Row rowSelected = (Row) event.getOrigin().getTarget().getParent().getParent();
        Document document = rowSelected.getValue();

        String status;
        if (document.getStatus() == 1) {
            status = Labels.getLabel("user.lock").toLowerCase();
            document.setStatus(0);
        } else {
            status = Labels.getLabel("user.unlock").toLowerCase();
            document.setStatus(1);
        }

        Messagebox.show(Labels.getLabel("document.comfirm.lock", new String[]{status, document.getDocumentName()}),
                Labels.getLabel("user.comfirm"), Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
                new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                // TODO Auto-generated method stub
                if (Messagebox.ON_YES.equals(event.getName())) {
                    // userService.save(user);
//                    bannerService.update(banner);
                    logger.info("Document controller: " + document.toString());
                    documentService.save(document);
                    search();
                    String mess = status.substring(0, 1).toUpperCase() + status.substring(1);
                    Messagebox.show(
                            Labels.getLabel("document.comfirm.lock.success",
                                    new String[]{mess, document.getDocumentName()}),
                            Labels.getLabel("NOTIFICATION"), Messagebox.OK, Messagebox.INFORMATION);
                }
            }

        });
    }

    public ListModelList<Category> getListModelCategory() {
        return listModelCategory;
    }

    public void setListModelCategory(ListModelList<Category> listModelCategory) {
        this.listModelCategory = listModelCategory;
    }
}
