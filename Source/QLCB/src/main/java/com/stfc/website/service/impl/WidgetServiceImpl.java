/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.service.impl;

import com.stfc.website.dao.WidgetDAO;
import com.stfc.website.domain.Widget;
import com.stfc.website.bean.WidgetContent;
import com.stfc.website.service.WidgetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dmin
 */
@Service
public class WidgetServiceImpl implements WidgetService {

    @Autowired
    private WidgetDAO widgetDAO;


    @Transactional(readOnly = true)
    @Override
    public List<Widget> getAllWidget() {
        return widgetDAO.getAllWidget();
    }

    @Transactional(readOnly = true)
    @Override
    public List<WidgetContent> getAllWidgetContent(List<Long> lstWidgetId) {
        return widgetDAO.getAllWidgetContent(lstWidgetId);
    }

}
