package com.stfc.backend.controller;

import com.stfc.backend.domain.Banner;
import java.util.List;
import java.util.Date;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.stfc.backend.entity.Data;
import com.stfc.backend.service.BannerService;
import com.stfc.utils.Constants;
import com.stfc.utils.FileUtils;
import com.stfc.utils.FunctionUtil;
import com.stfc.utils.SpringConstant;
import com.stfc.utils.StringUtils;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;

public class AddBannerController extends SelectorComposer<Component> {

    private static final Logger logger = Logger.getLogger(AddBannerController.class);
    @Wire
    private Window addBanner;
    @WireVariable
    protected BannerService bannerService;

    private ListModelList<Data> listModelType = new ListModelList<>();

    @Wire
    private Combobox cbType;

    @Wire
    private Textbox linkImageHidden;

    @Wire
    private Longbox txtBannerId;

    @Wire
    private Textbox txtBannerName;

    @Wire
    private Textbox txtURL;

    @Wire
    private Intbox txtOrder;

    @Wire
    private Datebox dtFromdate;

    @Wire
    private Datebox dtToDate;

//    @WireVariable
//    private Label errBannerName;
//    @WireVariable
//    private Label errURL;
//    
//    @WireVariable
//    private Label errUserRole;
//    
//    @WireVariable
//    private Label errType;
    @Wire
    private Intbox txtStatus;

    @Wire
    private Intbox txtType;

    @Wire
    private Image pics;

//    @WireVariable
//    private Intbox txtStatus;
    /**
     * @return the listModelType
     */
    public ListModelList<Data> getListModelType() {
        return listModelType;
    }

    /**
     * @param listModelType the listModelType to set
     */
    public void setListModelType(ListModelList<Data> listModelType) {
        this.listModelType = listModelType;
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        bannerService = (BannerService) SpringUtil.getBean(SpringConstant.BANNER_SERVICE);
        List<Data> listDataType = FunctionUtil.createListTypeBanner();

        listModelType = new ListModelList<>(listDataType);
        cbType.setModel(listModelType);
        if (txtType.getValue() != null) {
            cbType.setValue(FunctionUtil.getTypeName(txtType.getValue()));
        }

    }

    @Listen("onClick = #btnCancel")
    public void close() {
        addBanner.detach();
    }

    /**
     *
     * @param event
     */
    @Listen("onClick = #btnSave")
    public void save() {
        try {

            String pathImage = linkImageHidden.getValue().trim();
            String name = txtBannerName.getValue();
            Integer order = txtOrder.getValue();
            Integer type = cbType.getSelectedItem().getValue();
            String url = txtURL.getValue().trim();
            Long id = txtBannerId.getValue();
            Date fromDate = dtFromdate.getValue();
            Date toDate = dtToDate.getValue();
            Integer status = txtStatus.getValue();
            Banner banner = new Banner();

            if (!StringUtils.valiString(name)) {
//                errBannerName.setValue(Labels.getLabel("banner.error.empty.name"));
//                errBannerName.setVisible(false);
                txtBannerName.focus();
                Clients.showNotification(Labels.getLabel("banner.error.empty.name"), Clients.NOTIFICATION_TYPE_ERROR, txtBannerName, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
                return;
            }
            if (name.getBytes().length > 2000) {
//                errBannerName.setValue(Labels.getLabel("banner.error.max.name"));
//                errBannerName.setVisible(false);
                Clients.showNotification(Labels.getLabel("banner.error.max.name"), Clients.NOTIFICATION_TYPE_ERROR, txtBannerName, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
                txtBannerName.focus();
                return;
            }
            if (type == -1) {
//                errType.setVisible(true);
//                errType.setValue(Labels.getLabel("banner.error.type.select"));
                Clients.showNotification(Labels.getLabel("banner.error.type.select"), Clients.NOTIFICATION_TYPE_ERROR, cbType, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
                cbType.focus();
                return;
            }
            if (url.length() > 2000) {
//                errURL.setValue(Labels.getLabel("banner.error.max.url"));
//                errURL.setVisible(false);
                Clients.showNotification(Labels.getLabel("banner.error.max.url"), Clients.NOTIFICATION_TYPE_ERROR, txtURL, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
                txtURL.focus();
                return;
            }
            if (fromDate != null && toDate != null) {
                if (fromDate.after(toDate)) {
                    Clients.showNotification(Labels.getLabel("banner.error.date"), Clients.NOTIFICATION_TYPE_ERROR, dtFromdate, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
                    dtFromdate.focus();
                    return;
                }
            }
            banner.setBannerImage(pathImage);
            banner.setBannerName(name);
            banner.setBannerOrder(order);
            banner.setBannerType(type);
            banner.setBannerUrl(url);
            banner.setBannerId(id);
            banner.setEffectFromDate(fromDate);
            banner.setEffectToDate(toDate);

            String titile = "";
            if (status != null) {
                banner.setModifiedDate(new Date());
                banner.setBannerStatus(status);
                titile = Labels.getLabel("common.edit");
            } else {
                banner.setModifiedDate(new Date());
                banner.setCreateDate(new Date());
                banner.setBannerStatus(1);
                titile = Labels.getLabel("add");

            }
            bannerService.save(banner);
//            Messagebox.show(
//                    Labels.getLabel("banner.action.success", new String[]{titile}),
//                    Labels.getLabel("notification"), Messagebox.OK, Messagebox.INFORMATION);
            Events.sendEvent("onClick", (Button) ((Window) addBanner.getParent()).getFellow("reloadData"), null);
            addBanner.detach();
            reset();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     *
     */
    private void reset() {
        linkImageHidden.setValue("");
        txtBannerName.setValue("");
        txtOrder.setValue(null);
        cbType.setSelectedIndex(0);
        txtURL.setValue(null);
        txtBannerId.setValue(null);
        dtFromdate.setValue(null);
        dtToDate.setValue(null);
    }
    @Listen("onUpload = #uploadbtn")
    public void uploadImage(UploadEvent evt) {
        Session session = Sessions.getCurrent();
        org.zkoss.util.media.Media media = evt.getMedia();
//		org.zkoss.zul.Image image = new org.zkoss.zul.Image();
        pics.setContent((org.zkoss.image.Image) media);
        FileUtils fileUtils = new FileUtils();
        fileUtils.saveFile(media, session, 0);
        linkImageHidden.setValue(fileUtils.getFilePathOutput());
    }

    @Listen("onChange = #txtBannerName")
    public void loadUrl() {
        txtURL.setValue(txtBannerName.getValue());
    }
}
