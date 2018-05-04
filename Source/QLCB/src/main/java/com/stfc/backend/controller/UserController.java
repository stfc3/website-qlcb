/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.controller;

import com.stfc.backend.email.MailSend;
import com.stfc.backend.service.UserService;
import com.stfc.utils.EncryptUtil;
import com.stfc.utils.SpringConstant;
import com.stfc.website.domain.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Row;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox;
import org.zkoss.util.resource.Labels;
import org.zkoss.zul.Combobox;

/**
 *
 * @author admin
 * @since 27/03/2018
 */
public class UserController extends GenericForwardComposer<Component> {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @WireVariable
    protected UserService userService;

    // private Listbox resultList;
    private ListModelList<User> listData = new ListModelList<>();

    private ListModelList listSearch;

    @Wire
    private Grid gridUser;

    private Window userManager;

    @WireVariable
    private Textbox userName;

    @WireVariable
    private Textbox email;

    @WireVariable
    private Combobox cbxStatus;

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
        search();
    }

    public void onSearch(ForwardEvent event) {
        search();
    }

    private void search() {
        User user = new User();
        if (userName != null) {
            user.setUserName(userName.getValue());
        }
        if (email != null) {
            user.setEmail(email.getValue());
        }
        if (!"-1".equals(cbxStatus.getSelectedItem().getValue())) {
            user.setStatus(Integer.valueOf(cbxStatus.getSelectedItem().getValue()));
        }
        List<User> listUser = userService.search(user);
        if (listUser != null && !listUser.isEmpty()) {
            listSearch = new ListModelList(listUser);
            listSearch.setMultiple(true);
            gridUser.setModel(listSearch);
        }
    }

    public void onClick$btnAdd() {
        User user = new User();
        Map<String, Object> arguments = new HashMap();
        arguments.put("users", user);
        final Window windownUpload = (Window) Executions.createComponents("/backend/manager/include/add_user.zul",
                userManager, arguments);
        windownUpload.doModal();
        windownUpload.setBorder(true);
        windownUpload.setBorder("normal");
        windownUpload.setClosable(true);

    }

    public void onClick$btnReset() {
        userName.setValue("");
        email.setValue("");
        cbxStatus.setSelectedIndex(0);
    }

    /**
     *
     * @param event
     */
    public void onReset(ForwardEvent event) {
        Row rowSelected = (Row) event.getOrigin().getTarget().getParent().getParent();
        MailSend mailSend = new MailSend();
        User user = rowSelected.getValue();
        Messagebox.show(Labels.getLabel("user.reset.comfirm", new String[]{user.getUserName()}),
                Labels.getLabel("user.comfirm"), Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
                new EventListener() {

            String password = RandomStringUtils.randomAlphanumeric(6).toUpperCase();

            @Override
            public void onEvent(Event event) throws Exception {
                // TODO Auto-generated method stub
                if (Messagebox.ON_YES.equals(event.getName())) {
                    user.setPassword(EncryptUtil.encrypt(password));

                    // StringBuilder content = new
                    // StringBuilder("<h1>Xin
                    // chào : " + userName + "! Quản trị viên đã reset
                    // lại
                    // mật khẩu tại hệ thống cho bạn.</h1>");
                    // content.append("<br><h3>Mật khẩu được thay đổi
                    // lại: "
                    // + password + ".</h3>");
                    // content.append(" <br> Hãy đăng nhập vào hệ thống
                    // và
                    // đổi lại mật khẩu để đảm bảo an toàn");
                    mailSend.sendMail(user.getEmail(), Labels.getLabel("user.reset.password.success.content",
                            new String[]{user.getUserName(), password}), Labels.getLabel("user.reset.title.reset"));

                    Messagebox.show(
                            Labels.getLabel("user.reset.password.success", new String[]{user.getUserName()}),
                            Labels.getLabel("NOTIFICATION"), Messagebox.OK, Messagebox.INFORMATION);
                    userService.save(user);
                }
            }
        });

    }

    public void onEdit(ForwardEvent event) {
        Row rowSelected = (Row) event.getOrigin().getTarget().getParent().getParent();

        User user = rowSelected.getValue();

        Map<String, Object> arguments = new HashMap();
        arguments.put("users", user);
        final Window windownUpload = (Window) Executions.createComponents("/backend/manager/include/add_user.zul",
                userManager, arguments);
        windownUpload.doModal();
        windownUpload.setBorder(true);
        windownUpload.setBorder("normal");
        windownUpload.setClosable(true);
    }

    public void onLock(ForwardEvent event) {
        Row rowSelected = (Row) event.getOrigin().getTarget().getParent().getParent();
        User user = rowSelected.getValue();
        String status;
        if (user.getStatus() == 1) {
            status = Labels.getLabel("user.lock");
            user.setStatus(0);
        } else {
            status = Labels.getLabel("user.unlock");
            user.setStatus(1);
        }

        Messagebox.show(Labels.getLabel("user.comfirm.lock", new String[]{status, user.getUserName()}),
                Labels.getLabel("user.comfirm"), Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
                new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                // TODO Auto-generated method stub
                if (Messagebox.ON_YES.equals(event.getName())) {
                    userService.update(user);
                    search();
                    Messagebox.show(
                            Labels.getLabel("user.comfirm.lock.success", new String[]{status, user.getUserName()}),
                            Labels.getLabel("NOTIFICATION"), Messagebox.OK, Messagebox.INFORMATION);
                }
            }

        });
    }

    public void onClick$reloadData() {
        search();
//        onChange$cbBillCode();
    }
}
