package com.stfc.backend.controller;

import com.stfc.utils.Constants;
import com.stfc.website.Memory;
import com.stfc.website.bean.UserToken;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Menu;

public class NavbarComposer extends SelectorComposer<Component> {
    
    private Session session;
    UserToken userToken;
    @Wire
    Menu menuTitle;
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        session = Sessions.getCurrent();
        if (session.getAttribute(Constants.USER_TOKEN) != null) {
            userToken = (UserToken) session.getAttribute(Constants.USER_TOKEN);
            menuTitle.setTooltiptext(userToken.getFirstName() + " " + userToken.getLastName());
            menuTitle.setLabel("Welcome " + userToken.getUserName());
        }        
    }
    
    @Listen("onClick = #reloadCache")
    public void reloadCache() {
        Memory memory = new Memory();
        memory.reloadCache();
    }
    
    @Listen("onClick = #logOut")
    public void logOut() {
        session = Sessions.getCurrent();
        session.invalidate();
        Executions.sendRedirect(Constants.PAGE_LOGIN);
    }
}
