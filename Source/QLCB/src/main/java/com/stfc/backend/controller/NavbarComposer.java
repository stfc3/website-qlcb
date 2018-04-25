package com.stfc.backend.controller;

import com.stfc.website.Memory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

public class NavbarComposer extends SelectorComposer<Component> {


    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
    }

    @Listen("onClick = #reloadCache")
    public void reloadCache() {
        Memory memory=new Memory();
        memory.reloadCache();
    }
}
