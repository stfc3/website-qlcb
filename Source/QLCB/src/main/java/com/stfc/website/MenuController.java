/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website;

import com.stfc.backend.domain.Menu;
import com.stfc.backend.service.MenuService;
import com.stfc.utils.SpringConstant;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.zkoss.zhtml.B;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zhtml.I;
import org.zkoss.zhtml.Li;
import org.zkoss.zhtml.Ul;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.A;
import org.zkoss.zul.Label;

/**
 *
 * @author daond
 */
public class MenuController extends SelectorComposer<Div> {

    private static final Logger logger = Logger.getLogger(MenuController.class);
    @Wire
    Ul menu;

    @WireVariable
    MenuService menuService;
    private List<Menu> listMenu;

    @Override
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp);
        menuService = (MenuService) SpringUtil.getBean(SpringConstant.MENU_SERVICE);
        //Menu public
        listMenu = menuService.getMenuByType(0);
        createMenu();
    }

    private void createMenu() {
        buildHomeMenu();
        List<Menu> roots = getRoot();
        if (roots != null && !roots.isEmpty()) {
            for (Menu menuRoot : roots) {
                List<Menu> listMenuChild = getChild(menuRoot.getMenuId());
                if (listMenuChild == null || listMenuChild.isEmpty()) {
                    buildMenuNotChild(menuRoot, menu);
                } else {
                    Component comChild = buildMenuRootHasChild(menuRoot, menu);
                    createMenuChild(listMenuChild, comChild);
                }
            }
        }

    }
    private void createMenuChild(List<Menu> listMenuChild, Component parent){
        if (listMenuChild != null && !listMenuChild.isEmpty()) {
            for (Menu menuItem : listMenuChild) {
                List<Menu> listChild = getChild(menuItem.getMenuId());
                if (listChild == null || listChild.isEmpty()) {
                    buildMenuNotChild(menuItem, parent);
                } else {
                    Component comChild = buildMenuHasChild(menuItem, parent);
                    createMenuChild(listChild, comChild);
                }
            }
        }
    }

    private void buildHomeMenu() {
        Li liHome = new Li();
        liHome.setSclass("active");
        liHome.setParent(menu);

        A aHome = new A();
        aHome.setHref("/");
        aHome.setParent(liHome);

        I iHome = new I();
        iHome.setSclass("fa fa-home");
        iHome.setDynamicProperty("aria-hidden", "true");
        iHome.setStyle("font-size: 20px");
        iHome.setParent(aHome);
    }

    private void buildMenuNotChild(Menu menuItem, Component parent) {
        Li liItem = new Li();
        liItem.setParent(parent);

        A aItem = new A();
        aItem.setHref(menuItem.getMenuSlug());
        aItem.setParent(liItem);
        aItem.setLabel(menuItem.getMenuName());

    }

    private Component buildMenuRootHasChild(Menu menuItem, Component parent) {
        Li liItem = new Li();
        liItem.setParent(parent);
        
        org.zkoss.zhtml.A aItem = new org.zkoss.zhtml.A();
        aItem.setDynamicProperty("href", menuItem.getMenuSlug());
        aItem.setSclass("dropdown-toggle");
        aItem.setDynamicProperty("data-toggle", "dropdown");
        aItem.setParent(liItem);

        
        Label lblItem = new Label(menuItem.getMenuName());
        lblItem.setZclass("abc");
        lblItem.setParent(aItem);
        
        B bItem=new B();
        bItem.setSclass("caret");
        bItem.setParent(aItem);
        
        Ul ulItem = new Ul();
        ulItem.setSclass("dropdown-menu multi-level");
        ulItem.setParent(liItem);
        return ulItem;
    }
    private Component buildMenuHasChild(Menu menuItem, Component parent) {
        Li liItem = new Li();
        liItem.setSclass("dropdown-submenu");
        liItem.setParent(parent);
        
        org.zkoss.zhtml.A aItem = new org.zkoss.zhtml.A();
        aItem.setDynamicProperty("href", menuItem.getMenuSlug());
        aItem.setSclass("dropdown-toggle");
        aItem.setParent(liItem);

        
        Label lblItem = new Label(menuItem.getMenuName());
        lblItem.setZclass("abc");
        lblItem.setParent(aItem);
        
//        B bItem=new B();
//        bItem.setSclass("caret");
//        bItem.setParent(aItem);
        
        Ul ulItem = new Ul();
        ulItem.setSclass("dropdown-menu");
        ulItem.setParent(liItem);
        return ulItem;
    }

    private List<Menu> getRoot() {
        List<Menu> root = new ArrayList<>();
        if (listMenu != null && !listMenu.isEmpty()) {
            for (Menu menuRoot : listMenu) {
                if (menuRoot.getMenuParent() == null) {
                    root.add(menuRoot);
                }
            }
        }
        return root;
    }

    private List<Menu> getChild(Long parent) {
        List<Menu> child = new ArrayList<>();
        if (listMenu != null && !listMenu.isEmpty() && parent != null) {
            for (Menu menuItem : listMenu) {
                if (parent.equals(menuItem.getMenuParent())) {
                    child.add(menuItem);
                }
            }
        }
        return child;
    }

}
