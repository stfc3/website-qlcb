/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.controller;

import com.stfc.utils.FileUtils;
import java.util.List;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Image;
import org.zkoss.zul.Window;

/**
 *
 * @author dmin
 */
public class BrowserImage extends SelectorComposer<Component> {
    
    private static final Logger logger = Logger.getLogger(BrowserImage.class);
    @Wire
    Window winImage;
    
    Object[] listImage;
    private Session session;
    private String srcSelected;
    
    public Object[] getListImage() {
        return listImage;
    }
    
    public void setListImage(Object[] listImage) {
        this.listImage = listImage;
    }
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
    }
    
    @Override
    public ComponentInfo doBeforeCompose(Page page, Component parent, ComponentInfo compInfo) {
        session = Sessions.getCurrent();
        List<String> listFile = FileUtils.getImages(session.getWebApp().getRealPath("/images"));
        logger.info(listFile.toString());
        setListImage(listFile.toArray());
        page.setAttribute("listImage", listImage);
        return super.doBeforeCompose(page, parent, compInfo);
    }
    
    @Listen("onSelectImage = #winImage")
    public void onSelectImage(ForwardEvent event) {
        Image imageSelected = (Image) event.getOrigin().getTarget();
        srcSelected = imageSelected.getSrc();
    }
    @Listen("onClick = #btnOK")
    public void onOK() {
        ((Image)winImage.getParent().getFellow("imageFeature")).setSrc(srcSelected);
        winImage.detach();
    }
}
