package com.stfc.backend.controller;

import com.stfc.utils.Constants;
import com.stfc.website.Memory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

public class NavbarComposer extends SelectorComposer<Component> {

    private Session session;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
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
