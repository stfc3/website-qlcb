/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.controller;

import com.stfc.website.backend.service.UserService;
import com.stfc.utils.SpringConstant;
import com.stfc.website.backend.utils.StringUtils;
import com.stfc.website.domain.User;
import java.util.List;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

/**
 *
 * @author admin
 * @since 27/03/2018
 */
public class UserController extends GenericForwardComposer<Component> {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @WireVariable
    protected UserService userService;

    private Listbox resultList;

    private ListModelList<User> listData = new ListModelList<>();

    private ListModelList listSearch;

    @WireVariable
    private Textbox userName;

    @WireVariable
    private Textbox email;

    public ListModelList<User> getListData() {
        return listData;
    }

    public void setListData(ListModelList<User> listData) {
        this.listData = listData;
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        userService = (UserService) SpringUtil.getBean(SpringConstant.USER_SERVICE);
        onSearch();
    }

    private void onSearch() {
        User user = new User();
        if (userName != null) {
            user.setUserName(userName.getValue());
        }
        if (email != null) {
            user.setEmail(email.getValue());
        }
        List<User> listUser = userService.search(user);
        if (listUser != null && !listUser.isEmpty()) {
            listSearch = new ListModelList(listUser);
            listSearch.setMultiple(true);
            resultList.setModel(listSearch);
        }
    }

    public void onClick$btnAdd() {
        onSearch();
    }

    public void onClick$btnSearch() {
        onSearch();
    }

    public void onClick$btnReset() {
        userName.setValue("");
        email.setValue("");
    }
}
