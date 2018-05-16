package com.stfc.backend.controller;

import com.stfc.backend.domain.Document;
import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
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
import com.stfc.website.bean.UserToken;
import com.stfc.website.domain.Category;
import com.stfc.website.domain.Class;
import com.stfc.website.service.WidgetService;
import java.util.Date;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Vlayout;

public class AddDocumentController extends SelectorComposer<Component> {

    private static final Logger logger = Logger.getLogger(AddDocumentController.class);
    @Wire
    private Window addDocument;

    @WireVariable
    protected WidgetService widgetService;
    @WireVariable
    protected DocumentService documentService;

    private ListModelList<Object> listModelType = new ListModelList<>();
    private ListModelList<Category> listModelCategory = new ListModelList<>();
    private ListModelList<com.stfc.website.domain.Class> listModelClass = new ListModelList<>();

    @Wire
    private Textbox linkHidden;

    @Wire
    private Longbox txtDocumentId;
    @Wire
    private Intbox txtType;
    @Wire
    private Longbox txtCategory;
    @Wire
    private Intbox txtStatus;

    @Wire
    private Textbox txtDocumentName;

    @Wire
    private Combobox cbType;

    @Wire
    private Combobox cbCategory;

    @Wire
    private Combobox cbClass;
    @Wire
    private Vlayout layoutCat;
    @Wire
    private Vlayout layoutClass;

    @Wire
    private Label fileName;

    @Wire
    private Intbox txtOrder;

    @Wire
    private Textbox txtAuthor;

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

    public ListModelList<Class> getListModelClass() {
        return listModelClass;
    }

    public void setListModelClass(ListModelList<Class> listModelClass) {
        this.listModelClass = listModelClass;
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        widgetService = (WidgetService) SpringUtil.getBean(SpringConstant.WIDGET_SERVICE);
        documentService = (DocumentService) SpringUtil.getBean(SpringConstant.DOCUMENT_SERVICE);

        List<Data> listDataType = FunctionUtil.createListTypeDocument();
        listDataType.remove(0);
        List<Category> listDataCategory = widgetService.getAllCategory();
        List<com.stfc.website.domain.Class> listClass = widgetService.getAllClass();
        Category catDefault = new Category();
        catDefault.setCategoryId(-1l);
        catDefault.setCategoryName(Labels.getLabel("option"));
        listDataCategory.add(0, catDefault);
        listModelCategory = new ListModelList<>(listDataCategory);
        listModelClass = new ListModelList<>(listClass);
        listModelType = new ListModelList<>(listDataType);
        cbType.setModel(listModelType);
        if (txtType.getValue() != null) {

            cbType.setValue(FunctionUtil.getTypeDocument(txtType.getValue()));
        } else {

            cbType.setValue(FunctionUtil.getTypeDocument(1));

        }

        cbCategory.setModel(listModelCategory);
        if (txtCategory.getValue() != null) {
            cbCategory.setValue(getCatName(listDataCategory, txtCategory.getValue()));
        }
        cbClass.setModel(listModelClass);
        if (txtCategory.getValue() != null) {
            cbClass.setValue(getClassName(listClass, txtCategory.getValue()));
        }

        if (txtType.getValue() == null || txtType.getValue() == 1) {

            layoutCat.setVisible(false);
            layoutClass.setVisible(true);

        } else if (txtType.getValue() == 2) {
            layoutCat.setVisible(true);
            layoutClass.setVisible(false);
        }

    }

    @Listen("onClick = #btnCancel")
    public void cancel() {
        addDocument.detach();
    }

    /**
     *
     * @param event
     */
    @Listen("onClick = #btnSave")
    public void save() {
        try {
            Session session = Sessions.getCurrent();
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
            if (cbType.getSelectedItem() == null || cbType.getSelectedItem().getValue().equals(-1)) {
//                errType.setVisible(true);
//                errType.setValue(Labels.getLabel("document.error.empty.type"));
                Clients.showNotification(Labels.getLabel("document.error.empty.type"), Clients.NOTIFICATION_TYPE_ERROR, cbType, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
                cbType.focus();
                return;
            }
            if (!StringUtils.valiString(linkHidden.getValue())) {
                Clients.showNotification(Labels.getLabel("document.error.empty.upload"), Clients.NOTIFICATION_TYPE_ERROR, fileName, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
//                errPath.setValue(Labels.getLabel("document.error.empty.upload"));
//                errPath.setVisible(false);
                return;
            }
            if (cbType.getSelectedItem().getValue().equals(2)) {
                if (cbCategory.getSelectedItem() == null || cbCategory.getSelectedItem().getValue().equals(-1)) {
//                errCategory.setVisible(true);
//                errCategory.setValue(Labels.getLabel("document.error.empty.category"));
                    Clients.showNotification(Labels.getLabel("document.error.empty.category"), Clients.NOTIFICATION_TYPE_ERROR, cbCategory, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
                    cbCategory.focus();
                    return;
                }
            } else {
                if (cbClass.getSelectedItem() == null || cbClass.getSelectedItem().getValue().equals(-1)) {
//                errCategory.setVisible(true);
//                errCategory.setValue(Labels.getLabel("document.error.empty.category"));
                    Clients.showNotification(Labels.getLabel("document.error.empty.category"), Clients.NOTIFICATION_TYPE_ERROR, cbClass, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
                    cbClass.focus();
                    return;
                }
            }

            String documentName = txtDocumentName.getValue().trim();
            Integer type = cbType.getSelectedItem().getValue();
            String path = linkHidden.getValue().trim();
            Long category = null;

            if (type == 1) {
                category = cbClass.getSelectedItem().getValue();

            } else if (type == 2) {
                category = cbCategory.getSelectedItem().getValue();
            }
            String author = txtAuthor.getValue().trim();
            if (!StringUtils.valiString(author)) {
                UserToken userToken = (UserToken) session.getAttribute(Constants.USER_TOKEN);
                author = userToken.getUserName();
            }
            Integer order = txtOrder.getValue();
            Integer status = txtStatus.getValue();
            Long id = txtDocumentId.getValue();
            document.setDocumentId(id);
            document.setDocumentName(documentName);
            document.setDocumentType(type);
            document.setDocumentPath(path);
            document.setCategoryId(category);
            document.setAuthor(author);
            document.setDocumentOrder(order);
            document.setFileName(fileName.getValue());
            String titile = "";
            if (status != null) {
                document.setModifiedDate(new Date());
                document.setStatus(status);
                titile = Labels.getLabel("common.edit");
            } else {
                document.setModifiedDate(new Date());
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

    /**
     *
     * @param evt
     */
    @Listen("onUpload = #uploadbtn")
    public void uploadDocument(UploadEvent evt) {
        Session session = Sessions.getCurrent();
        org.zkoss.util.media.Media media = evt.getMedia();
//        if (StringUtils.validatePattern(media.getName())) {
//            errPath.setValue(Labels.getLabel("document.error.empty.pattern"));
//            errPath.setVisible(false);
//            return;
//        }
        FileUtils fileUtils = new FileUtils();
        LoadProperties properties = LoadProperties.getInstant();
        ConfigEntity entity = properties.loadConfig();
        fileUtils.setFilePathConfig(entity.getUploadDocument());
        fileUtils.saveFile(media, session, 1);
        fileName.setValue(media.getName());

        linkHidden.setValue(fileUtils.getFilePathOutput());
    }

    /**
     *
     * @param lstCat
     * @param catID
     * @return
     */
    private String getCatName(List<Category> lstCat, Long catID) {
        if (lstCat != null && !lstCat.isEmpty()) {
            for (Category category : lstCat) {
                if (catID.equals(category.getCategoryId())) {
                    return category.getCategoryName();
                }
            }
        }
        return "";
    }

    /**
     *
     * @param lstClass
     * @param classID
     * @return
     */
    private String getClassName(List<com.stfc.website.domain.Class> lstClass, Long classID) {
        if (lstClass != null && !lstClass.isEmpty()) {
            for (com.stfc.website.domain.Class clas : lstClass) {
                if (classID.equals(clas.getClassId())) {
                    return clas.getClassName();
                }
            }
        }
        return "";
    }

    @Listen("onChange = #cbType")
    public void changeType() {
        if (cbType.getSelectedItem() != null && !cbType.getSelectedItem().getValue().equals(-1l)
                && cbType.getSelectedItem().getValue().equals(1)) {
            layoutCat.setVisible(false);
            layoutClass.setVisible(true);
        } else if (cbType.getSelectedItem().getValue().equals(2)) {
            layoutCat.setVisible(true);
            layoutClass.setVisible(false);
        }
    }
}
