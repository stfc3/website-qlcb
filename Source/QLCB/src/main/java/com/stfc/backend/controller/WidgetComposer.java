package com.stfc.backend.controller;

import com.stfc.website.Memory;
import com.stfc.website.domain.Widget;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;

public class WidgetComposer extends SelectorComposer<Component> {

    @Wire
    Grid listWidget;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        List<Widget> lstWidget = new ArrayList<>(Memory.listWidgetCache.values());
        ListModelList modelWidget=new ListModelList(lstWidget);
        listWidget.setModel(modelWidget);
    }

//    @Listen("onOpen = #taskpp")
//    public void toggleTaskPopup(OpenEvent event) {
//        toggleOpenClass(event.isOpen(), atask);
//    }
//
//    @Listen("onOpen = #notipp")
//    public void toggleNotiPopup(OpenEvent event) {
//        toggleOpenClass(event.isOpen(), anoti);
//    }
//
//    @Listen("onOpen = #msgpp")
//    public void toggleMsgPopup(OpenEvent event) {
//        toggleOpenClass(event.isOpen(), amsg);
//    }
    // Toggle open class to the component
}
