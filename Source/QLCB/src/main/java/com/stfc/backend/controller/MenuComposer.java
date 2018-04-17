package com.stfc.backend.controller;

import com.stfc.backend.domain.Menu;
import com.stfc.backend.service.MenuService;
import com.stfc.utils.SpringConstant;
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
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;

public class MenuComposer extends SelectorComposer<Component> {

    @WireVariable
    MenuService menuService;
    @Wire
    Combobox menuType, menuParent, menuDataType, menuCategory, menuPost;
    @Wire
    Textbox menuName;
    @Wire
    Grid listMenu;
    //default is public
    private Integer intMenuType = 1;
//    private ListModelList modelMenu;
    private ListModelList modelCategory;

    private Menu menuOption;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        menuService = (MenuService) SpringUtil.getBean(SpringConstant.MENU_SERVICE);
        loadMenu();

        modelCategory = new ListModelList(Memory.listCategoryCache.values());
        menuCategory.setModel(modelCategory);

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
    }

    @Listen("onClick = #btnSaveMenu")
    public void saveMenu() {

        Menu menu = new Menu();
        menu.setMenuName(menuName.getValue());
        if (!Labels.getLabel("option").equals(menuParent.getValue()) ) {
            menu.setMenuParent(menuParent.getSelectedItem().getValue());
        }
        menu.setMenuSlug(menuCategory.getSelectedItem().getValue());
        menu.setMenuStatus(1);
        menu.setMenuType(intMenuType);
        menu.setCreateDate(new Date());
        menu.setModifiedDate(new Date());
        menuService.saveOrUpdate(menu);
        loadMenu();

    }
}
