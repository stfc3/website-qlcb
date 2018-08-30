/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.controller;

import com.stfc.utils.Constants;
import com.stfc.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.apache.log4j.Logger;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.LabelElement;

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
    private String divSelected;
    private String fileSelection;

    @Wire
    public Textbox txtSearch;

    @Wire
    public Div divImage;

    @Wire
    public Menupopup contextMenu;

    @Wire
    public Textbox txtLink;

    public Object[] getListImage() {
        return listImage;
    }

    public void setListImage(Object[] listImage) {
        this.listImage = listImage;
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        String path = session.getWebApp().getRealPath("/images");
        divSelected = path;
        File directory = new File(path);
        List<File> listFile = FileUtils.findFile(txtSearch.getValue(), directory);
        buildListImage(listFile);
        txtLink.setValue(shortNamePath(divSelected));

    }

    @Override
    public ComponentInfo doBeforeCompose(Page page, Component parent, ComponentInfo compInfo) {
        session = Sessions.getCurrent();
//        List<String> listFile = FileUtils.getImages(divSelected);
//        logger.info(listFile.toString());
//        setListImage(listFile.toArray());
//        page.setAttribute("listImage", listImage);
        return super.doBeforeCompose(page, parent, compInfo);
    }

    @Listen("onSelectImage = #winImage")
    public void onSelectImage(ForwardEvent event) {
        Image imageSelected = (Image) event.getOrigin().getTarget();
        srcSelected = imageSelected.getSrc();
    }

    @Listen("onClick = #btnOK")
    public void onOK() {
        ((Image) winImage.getParent().getFellow("imageFeature")).setSrc(srcSelected);
        winImage.detach();
    }

    @Listen("onUpload = #btnUpload")
    public void onUpload(UploadEvent evt) {
        Session session = Sessions.getCurrent();
        Media media = evt.getMedia();
        boolean isCheck = false;
        for (int i = 0; i < Constants.formatImage.length; i++) {
            if (media.getName().endsWith(Constants.formatImage[i])) {
                isCheck = true;
            }
        }
        if (!isCheck) {
            Messagebox.show(
                    Labels.getLabel("upload.image.error"),
                    Labels.getLabel("notification"), Messagebox.OK, Messagebox.INFORMATION);
            return;
        }
        FileUtils fileUtils = new FileUtils();
        fileUtils.uploadImage(media, session, divSelected);
        File directory = new File(divSelected);
        List<File> listFile = FileUtils.findFile(txtSearch.getValue(), directory);
        buildListImage(listFile);
    }

    @Listen("onChange = #txtSearch")
    public void onSearch() {
        search();
    }

    void buildListImage(List<File> objects) {
        divImage.getChildren().clear();
        if (objects != null && !objects.isEmpty()) {
            for (File object : objects) {

                Menupopup menupopup = new Menupopup();
                Menuitem itemCreate = new Menuitem();
                itemCreate.setId("create");
                itemCreate.setLabel("New folder");

                Menuitem itemRename = new Menuitem();
                itemRename.setHoverImage("/images/" + object.getName());
//                itemRename.setAttribute("ref", this)
                itemRename.setId("rename");
                itemRename.setLabel("Rename");

                Menuitem itemDelete = new Menuitem();
                itemDelete.setId("delete");
                itemDelete.setHoverImage("/images/" + object.getName());
                itemDelete.setLabel("Delete");
                menupopup.appendChild(itemCreate);
                menupopup.appendChild(itemRename);
                menupopup.appendChild(itemDelete);

                Div parent = new Div();
                parent.setSclass("imageDiv");
                parent.setContext(menupopup);

                Image image = new Image();
                image.setWidth("100%");
                image.setHeight("100%");
                if (object.isDirectory()) {
                    image.setAttribute("FILE", "FILE");
                    image.setSrc("/images/FOLDER.jpg");
                    image.addEventListener(Events.ON_DOUBLE_CLICK, new ItemOnDoubleClickListener());
                    image.addEventListener(Events.ON_CLICK, new ItemOnClickListener());
//                    image.addEventListener(Events.ON_FOCUS, new ItemFocusListener());
//                    File[] list = object.listFiles();
//                    buildListImage(Arrays.asList(list));
                } else {
                    image.setAttribute("DIRECTORY", "DIRECTORY");
                    image.setSrc(shortNamePath(object.getAbsolutePath()));
                    image.addEventListener(Events.ON_CLICK, new ItemOnClickListener());
                    image.addEventListener(Events.ON_FOCUS, new ItemFocusListener());
                }
                Div item = new Div();
                item.setId(object.getName());
                item.setSclass("title-image");

                Textbox label = new Textbox();
                label.setId("/images/" + object.getName());
                label.setValue(String.valueOf(object.getName()));
                label.setSclass("txt-disable");
                label.setReadonly(true);
                item.appendChild(label);

                parent.appendChild(image);
                parent.appendChild(item);
                divImage.appendChild(parent);

            }
        } else {
//            Div parent = new Div();
//            parent.setSclass("imageDiv");
//            parent.setContext("contextMenu");
            divImage.setWidth("90%");
            divImage.setHeight("80%");
//            divImage.appendChild(parent);
        }

    }

    class ItemFocusListener implements EventListener<Event> {

        @Override
        public void onEvent(Event t) throws Exception {
            Image imageSelected = (Image) t.getTarget();
            imageSelected.focus();
            imageSelected.setSclass("image-select");
            srcSelected = imageSelected.getSrc();

            Div parent = (Div) imageSelected.getParent();
            Div textbox = (Div) parent.getLastChild();
//                divSelected += textbox.getId();
            fileSelection = textbox.getId();
        }

    }

    class ItemOnClickListener implements EventListener<Event> {

        @Override
        public void onEvent(Event event) {
            try {
                cleanFocus();
                Image imageSelected = (Image) event.getTarget();
                imageSelected.focus();
                imageSelected.setSclass("image-select");
                srcSelected = imageSelected.getSrc();

                Div parent = (Div) imageSelected.getParent();
                Div textbox = (Div) parent.getLastChild();
//                divSelected += textbox.getId();
                fileSelection = textbox.getId();
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    class ItemOnDoubleClickListener implements EventListener<Event> {

        @Override
        public void onEvent(Event event) {
            try {
//        String message = "Clicked on " + ((LabelElement) event.getTarget()).getLabel();
//                Clients.showNotification("Double click " + event.getTarget().getId(), "info", null, null, 1000);
                cleanFocus();
                Image imageSelected = (Image) event.getTarget();
                imageSelected.focus();
                imageSelected.setSclass("image-select");
//                srcSelected = imageSelected.getSrc();
//                divSelected = imageSelected.getSrc();
                String item = imageSelected.getSrc();

                Div parent = (Div) imageSelected.getParent();
                parent.focus();
                Div textbox = (Div) parent.getLastChild();
                divSelected += File.separator + textbox.getId();
                reload(divSelected);
                txtLink.setValue(shortNamePath(divSelected));
                fileSelection = textbox.getId();
//                srcSelected = imageSelected.getSrc();
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    class EnterListener implements EventListener<Event> {

        @Override
        public void onEvent(Event t) throws Exception {
            Textbox imageSelected = (Textbox) t.getTarget();
            File fileItem = new File(divSelected + File.separator + fileSelection);
            String[] array = fileSelection.split("\\.");
            String extentFile = "";
            if (array.length == 2) {
                extentFile = "." + array[1];
            }
            fileItem.renameTo(new File(divSelected + File.separator + imageSelected.getValue().trim() + extentFile));
            reload(divSelected);
            fileSelection = "";
        }

    }

    @Listen("onClick = menuitem")
    public void menuAction(MouseEvent event) {
//        logger.info(session);
        String id = ((LabelElement) event.getTarget()).getId();
//        Clients.showNotification(message,"info",null,null,1000);
        if ("create".equals(id)) {
            createNewFile();
        } else if ("delete".equals(id)) {
            deleteFile();
        } else if ("rename".equals(id)) {
            rename();
        }
    }

    private void reload(String item) {
//        String path = "/images/";
//        if (StringUtils.valiString(item)) {
//            path += item;
//        }
        File directory = new File(divSelected);
        List<File> listFile = FileUtils.findFile(txtSearch.getValue(), directory);
        setListImage(listFile.toArray());
        buildListImage(listFile);
    }

    @Listen("onClick = #btnBack")
    public void onBack() {
//        String path = session.getWebApp().getRealPath("/images/");
//        if (StringUtils.valiString(divSelected)) {
//            path += divSelected;
//        }
        String root = session.getWebApp().getRealPath("/images");
        if (divSelected.startsWith(root) && !divSelected.equals(root)) {
            File directory = new File(divSelected);
            File parent = directory.getParentFile();
            divSelected = parent.getAbsolutePath();
            List<File> listFile = FileUtils.findFile(txtSearch.getValue(), parent);
            setListImage(listFile.toArray());
            buildListImage(listFile);
            txtLink.setValue(shortNamePath(divSelected));
        }
    }

    private String shortNamePath(String pathFull) {
        String root = session.getWebApp().getRealPath("/images");
        return pathFull.replace(root, "/images").replaceAll("\\\\", "/");
    }

    @Listen("onClick = #btnRenameFile")
    public void rename() {
        List<Component> listItem = divImage.getChildren();
        if (listItem != null && !listItem.isEmpty()) {
            for (Component component : listItem) {
                Div item = (Div) component.getLastChild();
                if (fileSelection != null && fileSelection.equals(item.getId())) {
                    Textbox children = (Textbox) item.getLastChild();
                    children.setReadonly(false);
                    children.focus();
                    String[] array = fileSelection.split("\\.");
                    if (array.length > 0) {
                        children.setValue(fileSelection.split("\\.")[0]);
                    }
                    children.setSclass("txt-enable");
                    children.addEventListener(Events.ON_OK, new EnterListener());

                }
            }
        }
    }

    public void enterSearch() {
        txtSearch.addEventListener(Events.ON_OK, new EnterSearchListener());
    }

    class EnterSearchListener implements EventListener<Event> {

        @Override
        public void onEvent(Event t) throws Exception {
            search();
        }

    }

    private void search() {
        session = Sessions.getCurrent();
        File directory = new File(divSelected);
        List<File> listFile = FileUtils.findFile(txtSearch.getValue(), directory);
        setListImage(listFile.toArray());
        buildListImage(listFile);
    }

    @Listen("onClick = #btnCreateFile")
    public void createNewFile() {
        String newFileName = "New folder";
//            String path = session.getWebApp().getRealPath("/images/");
//            if (StringUtils.valiString(divSelected)) {
//                path += divSelected;
//            }
        File directory = new File(divSelected);
        File newFolder = new File(directory + File.separator + newFileName);
        if (!newFolder.exists()) {
            newFolder.mkdirs();
        } else {
            List<File> listFile = FileUtils.find(newFileName, directory);
            int countFile = listFile.size();
            newFileName = newFileName + " (" + countFile + ")";
            newFolder = new File(directory + File.separator + newFileName);
            newFolder.mkdirs();
        }
        List<File> listFile = FileUtils.findFile(txtSearch.getValue(), directory);
        buildListImage(listFile);
        fileSelection = newFolder.getName();
        rename();
    }

    @Listen("onClick = #btnDeleteFile")
    public void deleteFile() {

        try {
            //            String path = session.getWebApp().getRealPath("/images/");
            File fileSelect = new File(divSelected + File.separator + fileSelection);
            boolean delete = false;
//        if (fileSelect.exists()) {
//            delete = fileSelect.delete();
//        }
            delete = Files.deleteIfExists(fileSelect.toPath());
            reload(divSelected);
            logger.info("Delete file: " + delete);
        } catch (IOException ex) {
            logger.error(ex.getMessage(),ex);
        }
    }

    void cleanFocus() {
        List<Component> listItem = divImage.getChildren();
        if (listItem != null && !listItem.isEmpty()) {
            listItem.stream().map((component) -> (Image) component.getFirstChild()).forEachOrdered((item) -> {
                item.setSclass("image-deselect");
            });
        }
    }
}
