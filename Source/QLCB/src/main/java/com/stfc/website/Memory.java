/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website;

import com.stfc.website.domain.Widget;
import com.stfc.website.service.WidgetService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;

/**
 *
 * @author dmin
 */
public class Memory {

    private static final Logger logger = Logger.getLogger(Memory.class);

    private static WidgetService widgetService;


    //list Vi tri
    public static Map<Long, Widget> listWidgetCache;

    /**
     * Ham start up dashboard
     */
    public void startup() {
        try {
            logger.info("==============STARTUP MEMORY==============");
            loadWidget();
        } catch (Throwable ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * Ham reload cache
     */
    public void reloadCache() {
        try {
        } catch (Throwable ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * Ham shut down
     */
    public void shutdown() {
        logger.info("==============SHUTDOWN MEMORY==============");
        clearCache();
    }

    /**
     * Ham xoa cache
     */
    private synchronized void clearCache() {
        listWidgetCache.clear();
    }


    public static void loadWidget() {
        List<Widget> vlstWidget;
        vlstWidget = widgetService.getAllWidget();
        if (vlstWidget != null) {
            listWidgetCache = vlstWidget.stream().collect(Collectors.toMap(Widget::getWidgetId, widget -> widget));
        } else {
            listWidgetCache = new HashMap<>();
        }
    }

    public static Map<Long, Widget> getListWidgetCache() {
        return listWidgetCache;
    }

    public static void setListWidgetCache(Map<Long, Widget> listWidgetCache) {
        Memory.listWidgetCache = listWidgetCache;
    }

    public static WidgetService getWidgetService() {
        return widgetService;
    }

    public static void setWidgetService(WidgetService widgetService) {
        Memory.widgetService = widgetService;
    }

    


}
