/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.service;

import com.stfc.website.domain.Widget;
import com.stfc.website.bean.WidgetContent;
import java.util.List;

/**
 *
 * @author dmin
 */
public interface WidgetService {

    List<Widget> getAllWidget();
    List<WidgetContent> getAllWidgetContent(List<Long> lstWidgetId);

}
