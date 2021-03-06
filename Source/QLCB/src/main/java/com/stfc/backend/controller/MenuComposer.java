package com.stfc.backend.controller;

import com.stfc.backend.domain.Menu;
import com.stfc.backend.service.CategoryService;
import com.stfc.backend.service.MenuService;
import com.stfc.backend.service.PostService;
import com.stfc.utils.Constants;
import com.stfc.utils.SpringConstant;
import com.stfc.utils.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;

public class MenuComposer extends SelectorComposer<Component> {

    @WireVariable
    MenuService menuService;
    @WireVariable
    PostService postService;
    @WireVariable
    CategoryService categoryService;
    @Wire
    Combobox menuType, menuParent, menuDataType, menuCategory, menuPost;
    @Wire
    Textbox menuName, menuLink;
    @Wire
    Intbox menuOrder;
    @Wire
    Div divCategory, divPost, divLink;
    @Wire
    Grid listMenu;
    //default is public
    private Integer intMenuType = 0;
    //default is category
    private Integer intDataType = 1;
//    private ListModelList modelMenu;
    private ListModelList modelCategory, modelPost;

    private Menu menuOption;

    private Menu menuSelected;

//    private boolean dataCategory = true;
    private boolean isAdd = true;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        menuService = (MenuService) SpringUtil.getBean(SpringConstant.MENU_SERVICE);
        postService = (PostService) SpringUtil.getBean(SpringConstant.POST_SERVICE);
        categoryService = (CategoryService) SpringUtil.getBean(SpringConstant.CATEGORY_SERVICE);
        loadMenu();

        modelCategory = new ListModelList(categoryService.getCategory());
        menuCategory.setModel(modelCategory);

        loadTypeData();

    }

    @Listen("onChange = #menuType")
    public void loadMenu() {
        intMenuType = Integer.valueOf(menuType.getSelectedItem().getValue());
        List<Menu> lstMenuCombo = menuService.getMenuByType(intMenuType);
        List<Menu> lstMenuGrid = new ArrayList<>(lstMenuCombo);

        menuOption = new Menu();
        menuOption.setMenuName(Labels.getLabel("option"));
        lstMenuCombo.add(0, menuOption);

        ListModelList modelMenuGrid = new ListModelList(lstMenuGrid);
        listMenu.setModel(modelMenuGrid);
        ListModelList modelMenuCombo = new ListModelList(lstMenuCombo);
        menuParent.setModel(modelMenuCombo);
        menuParent.setValue(Labels.getLabel("option"));

        modelPost = new ListModelList(postService.getPostByType(intMenuType));
        menuPost.setModel(modelPost);
    }

    @Listen("onClick = #btnSaveMenu")
    public void saveMenu() {

        if (validateInput()) {
            if (menuSelected == null) {
                menuSelected = new Menu();
            }
            menuSelected.setMenuName(menuName.getValue());
            menuSelected.setMenuOrder(menuOrder.getValue());
            if (!Labels.getLabel("option").equals(menuParent.getValue())) {
                menuSelected.setMenuParent(menuParent.getSelectedItem().getValue());
            } else {
                menuSelected.setMenuParent(null);
            }
            if (intDataType == 1) {
                menuSelected.setMenuSlug(menuCategory.getSelectedItem().getValue());
            } else if (intDataType == 2) {
                menuSelected.setMenuSlug(menuPost.getSelectedItem().getValue());
            } else if (intDataType == 3) {
                menuSelected.setMenuSlug(menuLink.getValue());
            }
            if (isAdd) {
                menuSelected.setMenuStatus(Constants.STATUS_ACTIVE);
                menuSelected.setCreateDate(new Date());
            }

            menuSelected.setMenuType(intMenuType);
            menuSelected.setModifiedDate(new Date());
            menuService.saveOrUpdate(menuSelected);
            clearInput();
        }
    }

    @Listen("onChange = #menuDataType")
    public void loadTypeData() {
        intDataType = Integer.valueOf(menuDataType.getSelectedItem().getValue());
        if (intDataType == 1) {
            divCategory.setVisible(true);
            divPost.setVisible(false);
            divLink.setVisible(false);
        } else if (intDataType == 2) {
            divCategory.setVisible(false);
            divLink.setVisible(false);
            divPost.setVisible(true);
        } else if (intDataType == 3) {
            divCategory.setVisible(false);
            divLink.setVisible(true);
            divPost.setVisible(false);
        }
    }

    @Listen("onDelete = #listMenu")
    public void onDelete(ForwardEvent event) {
        Messagebox.show(Labels.getLabel("message.confirm.delete.content"), Labels.getLabel("message.confirm.delete.title"), Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener() {
            @Override
            public void onEvent(Event e) {
                if (Messagebox.ON_YES.equals(e.getName())) {
                    Row rowSelected = (Row) event.getOrigin().getTarget().getParent().getParent();
                    Menu menu = rowSelected.getValue();
                    menu.setMenuStatus(Constants.STATUS_INACTIVE);
                    menu.setModifiedDate(new Date());
                    menuService.saveOrUpdate(menu);
                    loadMenu();
                }
            }
        });
    }

    @Listen("onEdit = #listMenu")
    public void onEdit(ForwardEvent event) {
        isAdd = false;
        Row rowSelected = (Row) event.getOrigin().getTarget().getParent().getParent();
        menuSelected = rowSelected.getValue();
        menuName.setValue(menuSelected.getMenuName());
        menuOrder.setValue(menuSelected.getMenuOrder());
        if (StringUtils.valiString(menuSelected.getMenuParentName())) {
            menuParent.setValue(menuSelected.getMenuParentName());
        } else {
            menuParent.setValue(Labels.getLabel("option"));
        }
        if (menuSelected.getMenuSlug().startsWith(Constants.PREFIX_SLUG_CATEGORY)) {
            menuDataType.setSelectedIndex(0);
            setSelectedCombo(menuCategory, menuSelected.getMenuSlug());
            loadTypeData();
        } else if (menuSelected.getMenuSlug().startsWith(Constants.PREFIX_SLUG_POST)) {
            setSelectedCombo(menuPost, menuSelected.getMenuSlug());
            menuDataType.setSelectedIndex(1);
            loadTypeData();
        } else {
            menuLink.setValue(menuSelected.getMenuSlug());
            menuDataType.setSelectedIndex(2);
            loadTypeData();
        }
    }

    @Listen("onClick = #btnReset")
    public void reset() {
        clearInput();
    }

    private boolean validateInput() {

        if (!StringUtils.valiString(menuName.getValue())) {
            Clients.showNotification(Labels.getLabel("menu.name.empty"), Clients.NOTIFICATION_TYPE_ERROR, menuName, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
            return false;
        }
        if (intDataType == 1) {
            if (!StringUtils.valiString(menuCategory.getValue())) {
                Clients.showNotification(Labels.getLabel("menu.category.empty"), Clients.NOTIFICATION_TYPE_ERROR, menuCategory, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
                return false;
            }
        } else if (intDataType == 2) {
            if (!StringUtils.valiString(menuPost.getValue())) {
                Clients.showNotification(Labels.getLabel("menu.post.empty"), Clients.NOTIFICATION_TYPE_ERROR, menuPost, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
                return false;
            }
        } else if (intDataType == 3) {
            if (!StringUtils.valiString(menuLink.getValue())) {
                Clients.showNotification(Labels.getLabel("menu.link.empty"), Clients.NOTIFICATION_TYPE_ERROR, menuLink, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
                return false;
            }
        }
        if (!isAdd) {
            if (menuSelected.getMenuId().equals(menuParent.getSelectedItem().getValue())) {
                Clients.showNotification(Labels.getLabel("menu.parent.myself"), Clients.NOTIFICATION_TYPE_ERROR, menuParent, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
                return false;
            }
        }
        return true;
    }

    private void setSelectedCombo(Combobox combo, String slug) {
        List<Comboitem> lstComboitem = combo.getItems();
        if (lstComboitem != null && !lstComboitem.isEmpty()) {
            for (Comboitem comboitem : lstComboitem) {
                if (slug.equals(comboitem.getValue())) {
                    combo.setSelectedItem(comboitem);
                    break;
                }
            }
        }
    }

    private void clearInput() {
        isAdd = true;
        menuSelected = null;
        menuName.setValue("");
        menuCategory.setValue("");
        menuPost.setValue("");
        menuLink.setValue("");
        menuOrder.setValue(null);
        loadMenu();
        loadTypeData();
    }
}
