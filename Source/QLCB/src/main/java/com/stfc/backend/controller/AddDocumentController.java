package com.stfc.backend.controller;

import com.stfc.backend.domain.Document;
import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.stfc.backend.entity.Data;
import com.stfc.backend.service.DocumentService;
import com.stfc.utils.Constants;
import com.stfc.utils.FileUtils;
import com.stfc.utils.FunctionUtil;
import com.stfc.utils.LoadProperties;
import com.stfc.utils.SpringConstant;
import com.stfc.utils.StringUtils;
import com.stfc.website.bean.ConfigEntity;
import com.stfc.website.domain.Category;
import com.stfc.website.service.WidgetService;
import java.util.Date;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;

public class AddDocumentController extends GenericForwardComposer<Component> {

    private static final Logger logger = Logger.getLogger(AddDocumentController.class);
    private Window addDocument;

    @WireVariable
    protected WidgetService widgetService;
    @WireVariable
    protected DocumentService documentService;

    private ListModelList<Object> listModelType = new ListModelList<>();
    private ListModelList<Category> listModelCategory = new ListModelList<>();

    @WireVariable
    private Textbox linkHidden;

    @WireVariable
    private Longbox txtDocumentId;
    @WireVariable
    private Intbox txtType;
    @WireVariable
    private Textbox txtCategory;
    @WireVariable
    private Intbox txtStatus;

    @WireVariable
    private Textbox txtDocumentName;

    @WireVariable
    private Combobox cbType;

    @WireVariable
    private Combobox cbCategory;

    @WireVariable
    private Label fileName;

    @WireVariable
    private Intbox txtOrder;

    @WireVariable
    private Textbox txtAuthor;

    @WireVariable
    private Label errDocumentName;

    @WireVariable
    private Label errPath;

    @WireVariable
    private Label errType;

    @WireVariable
    private Label errCategory;

    public ListModelList<Object> getListModelType() {
        return listModelType;
    }

    public void setListModelType(ListModelList<Object> listModelType) {
        this.listModelType = listModelType;
    }

    public ListModelList<Category> getListModelCategory() {
        return listModelCategory;
    }

    public void setListModelCategory(ListModelList<Category> listModelCategory) {
        this.listModelCategory = listModelCategory;
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        widgetService = (WidgetService) SpringUtil.getBean(SpringConstant.WIDGET_SERVICE);
        documentService = (DocumentService) SpringUtil.getBean(SpringConstant.DOCUMENT_SERVICE);
//        List<Data> listDataType = FunctionUtil.createListTypeBanner();
//
//        listModelType = new ListModelList<>(listDataType);
//        cbType.setModel(listModelType);
//        cbType.setValue(FunctionUtil.getTypeName(txtType.getValue()));

        List<Data> listDataType = FunctionUtil.createListTypeDocument();
        List<Category> listDataCategory = widgetService.getAllCategory();
        Category catDefault = new Category();
        catDefault.setCategoryId(-1l);
        catDefault.setCategoryName(Labels.getLabel("option"));
        listDataCategory.add(0, catDefault);
        listModelCategory = new ListModelList<>(listDataCategory);
        cbCategory.setModel(listModelCategory);
        cbCategory.setValue(txtCategory.getValue());

        listModelType = new ListModelList<>(listDataType);
        cbType.setModel(listModelType);
        if (txtType.getValue() != null) {
            cbType.setValue(FunctionUtil.getTypeDocument(txtType.getValue()));
        }

    }

    public void onClick$btnCancel() {
        addDocument.detach();
    }

    /**
     *
     * @param event
     */
    public void onSave(ForwardEvent event) {
        try {

            Document document = new Document();

//
            if (!StringUtils.valiString(txtDocumentName.getValue())) {
//                errDocumentName.setValue(Labels.getLabel("document.error.empty.name"));
//                errDocumentName.setVisible(false);
                Clients.showNotification(Labels.getLabel("document.error.empty.name"), Clients.NOTIFICATION_TYPE_ERROR, txtDocumentName, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
                txtDocumentName.focus();
                return;
            }

            if (txtDocumentName.getValue().getBytes().length > 255) {
//                errDocumentName.setValue(Labels.getLabel("document.error.max.name"));
                Clients.showNotification(Labels.getLabel("document.error.max.name"), Clients.NOTIFICATION_TYPE_ERROR, txtDocumentName, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
//                errDocumentName.setVisible(false);
                txtDocumentName.focus();
                return;
            }
             if (cbType.getSelectedItem() == null) {
//                errType.setVisible(true);
//                errType.setValue(Labels.getLabel("document.error.empty.type"));
                Clients.showNotification(Labels.getLabel("document.error.empty.type"), Clients.NOTIFICATION_TYPE_ERROR, cbType, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
                cbType.focus();
                return;
            }
            if (!StringUtils.valiString(linkHidden.getValue())) {
                errPath.setValue(Labels.getLabel("document.error.empty.upload"));
                errPath.setVisible(false);
                return;
            }
           
            if (cbCategory.getSelectedItem() == null) {
//                errCategory.setVisible(true);
//                errCategory.setValue(Labels.getLabel("document.error.empty.category"));
                Clients.showNotification(Labels.getLabel("document.error.empty.category"), Clients.NOTIFICATION_TYPE_ERROR, cbCategory, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
                cbCategory.focus();
                return;
            }

            String documentName = txtDocumentName.getValue().trim();
            Integer type = cbType.getSelectedItem().getValue();
            String path = linkHidden.getValue().trim();
            Long category = cbCategory.getSelectedItem().getValue();
            String author = txtAuthor.getValue().trim();
            if (!StringUtils.valiString(author)) {
                author = String.valueOf(session.getAttribute(Constants.USER_TOKEN));
            }
            Integer order = txtOrder.getValue();
            Integer status = txtStatus.getValue();
            Long id = txtDocumentId.getValue();
            document.setDocumentId(category);
            document.setDocumentName(documentName);
            document.setDocumentType(type);
            document.setDocumentPath(path);
            document.setCategoryId(category);
            document.setAuthor(author);
            document.setDocumentOrder(order);
            String titile = "";
            if (status != null) {
                document.setModifiedDate(new Date());
                document.setStatus(status);
                titile = Labels.getLabel("common.edit");
            } else {
                document.setCreateDate(new Date());
                document.setStatus(1);
                titile = Labels.getLabel("add");
//
            }
            documentService.save(document);
//            Messagebox.show(
//                    Labels.getLabel("document.action.success", new String[]{titile}),
//                    Labels.getLabel("notification"), Messagebox.OK, Messagebox.INFORMATION);
            reset();
            Events.sendEvent("onClick", (Button) ((Window) addDocument.getParent()).getFellow("reloadData"), null);
            addDocument.detach();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     *
     */
    private void reset() {
        linkHidden.setValue("");
        txtDocumentName.setValue("");
        txtOrder.setValue(null);
        cbType.setSelectedIndex(0);
        cbCategory.setSelectedIndex(0);
//        txtURL.setValue(null);
        fileName.setValue("");
        txtDocumentId.setValue(null);
        txtAuthor.setValue("");

    }

    public void onUpload$uploadbtn(UploadEvent evt) {

        org.zkoss.util.media.Media media = evt.getMedia();
        if (StringUtils.validatePattern(media.getName())) {
            errPath.setValue(Labels.getLabel("document.error.empty.pattern"));
            errPath.setVisible(false);
            return;
        }
        FileUtils fileUtils = new FileUtils();
        LoadProperties properties = LoadProperties.getInstant();
        ConfigEntity entity = properties.loadConfig();
        fileUtils.setFilePathConfig(entity.getUploadDocument());
        fileUtils.saveFile(media);
        fileName.setValue(media.getName());
        linkHidden.setValue(fileUtils.getFilePathOutput());
    }
}
