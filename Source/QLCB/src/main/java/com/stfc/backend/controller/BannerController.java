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
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.stfc.backend.domain.Banner;
import com.stfc.backend.entity.Data;
import com.stfc.backend.service.BannerService;
import com.stfc.utils.FunctionUtil;
import com.stfc.utils.SpringConstant;
import java.util.Date;

/**
 *
 * @author admin
 * @since 27/03/2018
 */
public class BannerController extends GenericForwardComposer<Component> {

    private static final Logger logger = Logger.getLogger(BannerController.class);

    @WireVariable
    protected BannerService bannerService;

    // private Listbox resultList;
    private ListModelList<Banner> listData = new ListModelList<>();

    private ListModelList listSearch;

    @Wire
    private Grid gridBanner;

    private Window bannerManager;

    @WireVariable
    private Textbox txtBannerName;

    @WireVariable
    private Combobox cbxStatus;

    @WireVariable
    private Datebox dtFromdate;

    @WireVariable
    private Datebox dtTodate;

    @WireVariable
    private Combobox cbType;

    private ListModelList<Object> listModelType = new ListModelList<>();

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

    public ListModelList<Banner> getListData() {
        return listData;
    }

    public void setListData(ListModelList<Banner> listData) {
        this.listData = listData;
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        bannerService = (BannerService) SpringUtil.getBean(SpringConstant.BANNER_SERVICE);
        search();

        List<Data> listDataType = FunctionUtil.createListTypeBanner();

        listModelType = new ListModelList<>(listDataType);
        cbType.setModel(listModelType);
    }

    public void onSearch(ForwardEvent event) {
        search();
    }

    private void search() {
        Banner banner = new Banner();
        if (txtBannerName != null) {
            banner.setBannerName(txtBannerName.getValue());
        }
        if (cbType != null && cbType.getSelectedItem() != null && !"-1".equals(cbType.getSelectedItem().getValue())) {
            banner.setBannerType(cbType.getSelectedItem().getValue());
        }
        if (dtFromdate != null) {
            banner.setEffectFromDate(dtFromdate.getValue());
        }
        if (dtTodate != null) {
            banner.setEffectToDate(dtTodate.getValue());
        }
        if (!"-1".equals(cbxStatus.getSelectedItem().getValue())) {
            banner.setBannerStatus(Integer.valueOf(cbxStatus.getSelectedItem().getValue()));
        }
        List<Banner> listBanner = bannerService.search(banner);

        listSearch = new ListModelList(listBanner);
        listSearch.setMultiple(true);
        gridBanner.setModel(listSearch);

    }

    public void onClick$btnAdd() {
        Banner banners = new Banner();
        Map<String, Object> arguments = new HashMap();
        arguments.put("banners", banners);
        final Window windownUpload = (Window) Executions.createComponents("/backend/manager/include/banner_add.zul",
                bannerManager, arguments);
        windownUpload.doModal();
        windownUpload.setBorder(true);
        windownUpload.setBorder("normal");
        windownUpload.setClosable(true);

    }

    public void onClick$btnReset() {
        txtBannerName.setValue("");
        dtFromdate.setValue(null);
        dtTodate.setValue(null);
        cbType.setSelectedIndex(0);
        cbxStatus.setSelectedIndex(0);
    }

    public void onEdit(ForwardEvent event) {
        Row rowSelected = (Row) event.getOrigin().getTarget().getParent().getParent();

        Banner banners = rowSelected.getValue();

        Map<String, Object> arguments = new HashMap();
        arguments.put("banners", banners);
        final Window windownUpload = (Window) Executions.createComponents("/backend/manager/include/banner_add.zul",
                bannerManager, arguments);
        windownUpload.doModal();
        windownUpload.setBorder(true);
        windownUpload.setBorder("normal");
        windownUpload.setClosable(true);
    }

    public void onLock(ForwardEvent event) {
        Row rowSelected = (Row) event.getOrigin().getTarget().getParent().getParent();
        Banner banner = rowSelected.getValue();
        String status;
        if (banner.getBannerStatus() == 1) {
            status = Labels.getLabel("user.lock").toLowerCase();
            banner.setBannerStatus(0);
        } else {
            status = Labels.getLabel("user.unlock").toLowerCase();
            banner.setBannerStatus(1);
        }
        
        Messagebox.show(Labels.getLabel("user.comfirm.lock", new String[]{status, banner.getBannerName()}),
                Labels.getLabel("user.comfirm"), Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
                new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                // TODO Auto-generated method stub
                if (Messagebox.ON_YES.equals(event.getName())) {
                    // userService.save(user);
                    banner.setModifiedDate(new Date());
                    bannerService.update(banner);
                    search();
                    String mess = status.substring(0, 1).toUpperCase() + status.substring(1);
                    Messagebox.show(
                            Labels.getLabel("banner.comfirm.lock.success",
                                    new String[]{mess, banner.getBannerName()}),
                            Labels.getLabel("NOTIFICATION"), Messagebox.OK, Messagebox.INFORMATION);
                }
            }

        });
    }

    public void onClick$reloadData() {
        search();
    }
}
