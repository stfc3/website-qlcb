package com.stfc.backend.controller;

import com.stfc.backend.domain.Banner;
import java.util.List;
import java.util.Date;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.stfc.backend.entity.Data;
import com.stfc.backend.service.BannerService;
import com.stfc.utils.FileUtils;
import com.stfc.utils.FunctionUtil;
import com.stfc.utils.SpringConstant;
import com.stfc.utils.StringUtils;
import org.zkoss.util.resource.Labels;
import org.zkoss.zul.Messagebox;

public class AddBannerController extends GenericForwardComposer<Component> {

    private static final Logger logger = Logger.getLogger(AddBannerController.class);
    private Window addBanner;
    @WireVariable
    protected BannerService bannerService;

    private ListModelList<Data> listModelType = new ListModelList<>();

    @WireVariable
    private Combobox cbType;

    @WireVariable
    private Textbox linkImageHidden;

    @WireVariable
    private Longbox txtBannerId;

    @WireVariable
    private Textbox txtBannerName;

    @WireVariable
    private Textbox txtURL;

    @WireVariable
    private Intbox txtOrder;

    @WireVariable
    private Datebox dtFromdate;

    @WireVariable
    private Datebox dtToDate;

    @WireVariable
    private Label errBannerName;

    @WireVariable
    private Label errURL;
//    
//    @WireVariable
//    private Label errUserRole;
//    
    @WireVariable
    private Label errType;
    @WireVariable
    private Intbox txtStatus;

    @WireVariable
    private Intbox txtType;

    @WireVariable
    private Image pics;

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
        cbType.setValue(FunctionUtil.getTypeName(txtType.getValue()));

    }

    public void onClick$btnCancel() {
        addBanner.detach();
    }

    /**
     *
     * @param event
     */
    public void onSave(ForwardEvent event) {
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
                errBannerName.setValue(Labels.getLabel("banner.error.empty.name"));
                errBannerName.setVisible(false);
                txtBannerName.focus();
                return;
            }
            if (name.getBytes().length > 2000) {
                errBannerName.setValue(Labels.getLabel("banner.error.max.name"));
                errBannerName.setVisible(false);
                txtBannerName.focus();
                return;
            }
            if (type == -1) {
                errType.setVisible(true);
                errType.setValue(Labels.getLabel("banner.error.type.select"));
                cbType.focus();
                return;
            }
            if (url.length() > 2000) {
                errURL.setValue(Labels.getLabel("banner.error.max.url"));
                errURL.setVisible(false);
                txtURL.focus();
                return;
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
                banner.setCreateDate(new Date());
                banner.setBannerStatus(1);
                titile = Labels.getLabel("add");

            }
            bannerService.save(banner);
            Messagebox.show(
                    Labels.getLabel("banner.action.success", new String[]{titile}),
                    Labels.getLabel("notification"), Messagebox.OK, Messagebox.INFORMATION);
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

    public void onUpload$uploadbtn(UploadEvent evt) {

        org.zkoss.util.media.Media media = evt.getMedia();
//		org.zkoss.zul.Image image = new org.zkoss.zul.Image();
        pics.setContent((org.zkoss.image.Image) media);
        FileUtils fileUtils = new FileUtils();
        fileUtils.saveFile(media);
        linkImageHidden.setValue(fileUtils.getFilePathOutput());
    }
}
