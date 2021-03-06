/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website;

import com.stfc.utils.Constants;
import com.stfc.utils.EncryptUtil;
import com.stfc.utils.SpringConstant;
import com.stfc.utils.StringUtils;
import com.stfc.website.bean.UserToken;
import com.stfc.website.service.WidgetService;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

/**
 *
 * @author daond
 */
public class LoginController extends SelectorComposer<Component> {

    private static final Logger logger = Logger.getLogger(LoginController.class);
    @Wire
    Textbox username;
    @Wire
    Textbox password;
    @Wire
    Label error;

    private Session session;
    @WireVariable
    protected WidgetService widgetService;

    @Wire
    private Textbox txtUserName;

    //Change Pasowrd
    @Wire
    private Textbox txtOldPassword;
    @Wire
    private Textbox txtNewPassword;
    @Wire
    private Textbox txtConfirmPassword;
    @Wire
    private Textbox captcha;
    @Wire
    private Label mesg;
    @Wire
    Div changePassword;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        try {
            session = (Session) Sessions.getCurrent();
            widgetService = (WidgetService) SpringUtil.getBean(SpringConstant.WIDGET_SERVICE);

            if (session.getAttribute(Constants.USER_TOKEN) != null) {
                String urlRedirect = (String) session.getAttribute(Constants.STFC_URL_REQUEST);
                if (StringUtils.valiString(urlRedirect)) {
                    Executions.sendRedirect(urlRedirect);
                } else {
                    Executions.sendRedirect(Constants.BACKEND_PAGE_HOME);
                }

            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Listen("onClick = #login; onOK = #password")
    public void login() {
        try {
            String vstrUserName = username.getValue();
            String vstrPassword = password.getValue();
            //check database
            UserToken vuser = widgetService.getUserByUserName(vstrUserName);
            if (vuser == null) {
                error.setValue(Labels.getLabel("login.error"));
            } else if (vuser.getStatus() != null && vuser.getStatus() != 1) {
                error.setValue(Labels.getLabel("login.lock.user.message"));
            } else if (!EncryptUtil.encrypt(vstrPassword).equals(vuser.getPassword())) {
                error.setValue(Labels.getLabel("login.error"));
            } else {
                session.setAttribute(Constants.USER_TOKEN, vuser);
                String urlRedirect = (String) session.getAttribute(Constants.STFC_URL_REQUEST);
                if (StringUtils.valiString(urlRedirect)) {
                    Executions.sendRedirect(urlRedirect);
                } else {
                    Executions.sendRedirect(Constants.BACKEND_PAGE_HOME);
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Listen("onClick = #changePassword")
    public void changePassword() throws IOException {
        Executions.sendRedirect(Constants.PAGE_CHANGE_PASSWORD);
    }

    @Listen("onClick = #btnchangePassword")
    public void updatePassowrd() {
        String vstrUserName = txtUserName.getValue();
        String vstrOldPassword = txtOldPassword.getValue();
        String vstrNewPassword = txtNewPassword.getValue();
        String vstrConfirmPassword = txtConfirmPassword.getValue();
        UserToken vuser = widgetService.getUserByUserName(vstrUserName);

        if (captcha.getValue() == null || "".equals(captcha.getValue())) {
            mesg.setValue(Labels.getLabel("login.change.password.captcha.empty"));
        }
        if (vuser == null) {
            mesg.setValue(Labels.getLabel("login.error"));
        } else if (vuser.getStatus() != null && vuser.getStatus() != 1) {
            mesg.setValue(Labels.getLabel("login.lock.user.message"));
        } else if (!EncryptUtil.encrypt(vstrOldPassword).equals(vuser.getPassword())) {
            mesg.setValue(Labels.getLabel("login.old.password.error"));
//        } else if (!vstrNewPassword.matches(Constants.PASSWORD_PATTERN)) {
//            mesg.setValue(Labels.getLabel("login.new.password.error"));
        } else if (!vstrNewPassword.equals(vstrConfirmPassword)) {
            mesg.setValue(Labels.getLabel("login.confirm.password.error"));
        } else {
            try {
                vuser.setPassword(EncryptUtil.encrypt(vstrNewPassword));
                widgetService.changePassword(vuser);
                session.invalidate();
                Executions.sendRedirect(Constants.PAGE_LOGIN);
//                mesgChangePassSuccess.setValue(Labels.getLabel("login.change.password.success"));
            } catch (Exception e) {
                mesg.setValue(Labels.getLabel("login.change.password.fail"));
            }

        }
    }
}
