/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website;

import com.stfc.backend.service.UserService;
import com.stfc.utils.Constants;
import com.stfc.utils.EncryptUtil;
import com.stfc.utils.SpringConstant;
import com.stfc.website.bean.UserToken;
import com.stfc.website.service.WidgetService;
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

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        try {
            session = (Session) Sessions.getCurrent();
            widgetService = (WidgetService) SpringUtil.getBean(SpringConstant.WIDGET_SERVICE);
            if (session.getAttribute(Constants.USER_TOKEN) != null) {
                Executions.sendRedirect(Constants.BACKEND_PAGE_HOME);
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
            } else if (!EncryptUtil.encrypt(vstrPassword).equals(vuser.getPassword())) {
                error.setValue(Labels.getLabel("login.error"));
            } else {
//                UserToken userToken = new UserToken();
//                userToken.setUserName(vuser.getUserName());
//                userToken.setFirstName(vuser.getFirstName());
//                userToken.setLastName(vuser.getLastName());
//                userToken.setEmail(vuser.getEmail());
//                userToken.setRole(vuser.getRole());

                session.setAttribute(Constants.USER_TOKEN, vuser);
                Executions.sendRedirect(Constants.BACKEND_PAGE_HOME);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
