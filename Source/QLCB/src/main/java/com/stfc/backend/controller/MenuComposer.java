package com.stfc.backend.controller;

import com.stfc.backend.domain.Menu;
import com.stfc.backend.service.MenuService;
import com.stfc.backend.service.PostService;
import com.stfc.utils.Constants;
import com.stfc.utils.SpringConstant;
import com.stfc.utils.StringUtils;
import com.stfc.website.Memory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;

public class MenuComposer extends SelectorComposer<Component> {

    @WireVariable
    MenuService menuService;
    @WireVariable
    PostService postService;
    @Wire
    Combobox menuType, menuParent, menuDataType, menuCategory, menuPost;
    @Wire
    Textbox menuName;
    @Wire
    Div divCategory, divPost;
    @Wire
    Grid listMenu;
    //default is public
    private Integer intMenuType = 0;
    //default is category
    private Integer intDataType = 1;
//    private ListModelList modelMenu;
    private ListModelList modelCategory, modelPost;

    private Menu menuOption;

    private boolean dataCategory = true;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        menuService = (MenuService) SpringUtil.getBean(SpringConstant.MENU_SERVICE);
        postService = (PostService) SpringUtil.getBean(SpringConstant.POST_SERVICE);
        loadMenu();

        modelCategory = new ListModelList(Memory.listCategoryCache.values());
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
            Menu menu = new Menu();
            menu.setMenuName(menuName.getValue());
            if (!Labels.getLabel("option").equals(menuParent.getValue())) {
                menu.setMenuParent(menuParent.getSelectedItem().getValue());
            }
            if (dataCategory) {
                menu.setMenuSlug(menuCategory.getSelectedItem().getValue());
            } else {
                menu.setMenuSlug(menuPost.getSelectedItem().getValue());
            }
            menu.setMenuStatus(Constants.STATUS_ACTIVE);
            menu.setMenuType(intMenuType);
            menu.setCreateDate(new Date());
            menu.setModifiedDate(new Date());
            menuService.saveOrUpdate(menu);
            loadMenu();
        }
    }

    @Listen("onChange = #menuDataType")
    public void loadTypeData() {
        intDataType = Integer.valueOf(menuDataType.getSelectedItem().getValue());
        if (intDataType == 1) {
            divCategory.setVisible(true);
            divPost.setVisible(false);
            dataCategory = true;
        } else {
            divCategory.setVisible(false);
            divPost.setVisible(true);
            dataCategory = false;
        }
    }

    private boolean validateInput() {

        if (!StringUtils.valiString(menuName.getValue())) {
            Clients.showNotification(Labels.getLabel("menu.name.empty"), Clients.NOTIFICATION_TYPE_ERROR, menuName, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
            return false;
        }
        if (dataCategory) {
            if (!StringUtils.valiString(menuCategory.getValue())) {
                Clients.showNotification(Labels.getLabel("menu.category.empty"), Clients.NOTIFICATION_TYPE_ERROR, menuCategory, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
                return false;
            }
        } else {
            if (!StringUtils.valiString(menuPost.getValue())) {
                Clients.showNotification(Labels.getLabel("menu.post.empty"), Clients.NOTIFICATION_TYPE_ERROR, menuPost, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
                return false;
            }
        }
        return true;
    }
}
