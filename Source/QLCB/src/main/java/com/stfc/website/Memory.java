/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website;

import com.stfc.website.domain.Widget;
import com.stfc.website.domain.WidgetContent;
import com.stfc.website.service.WidgetService;
import java.util.ArrayList;
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

    public static WidgetService getWidgetService() {
        return widgetService;
    }

    public static void setWidgetService(WidgetService widgetService) {
        Memory.widgetService = widgetService;
    }
    //list
    public static Map<Long, Widget> listWidgetCache;
    public static Map<Long, WidgetContent> listWidgetContentCache;

    /**
     * Ham start up dashboard
     */
    public void startup() {
        try {
            logger.info("==============STARTUP MEMORY==============");
            loadWidget();
            loadWidgetContent();
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
        listWidgetContentCache.clear();
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

    public static void loadWidgetContent() {
        if (listWidgetCache == null || listWidgetCache.isEmpty()) {
            loadWidget();
        }
        List<Long> lstWidgetId = new ArrayList<>();
        List<Widget> vlstWidget = new ArrayList<>(listWidgetCache.values());
        for (Widget wg : vlstWidget) {
            lstWidgetId.add(wg.getWidgetId());
        }
        List<WidgetContent> vlstWidgetContent;
        vlstWidgetContent = widgetService.getAllWidgetContent(lstWidgetId);
        if (vlstWidgetContent != null) {
            listWidgetContentCache = vlstWidgetContent.stream().collect(Collectors.toMap(WidgetContent::getWidgetContentId, widgetContent -> widgetContent));
        } else {
            listWidgetContentCache = new HashMap<>();
        }
    }

    public static Map<Long, Widget> getListWidgetCache() {
        if (listWidgetCache == null || listWidgetCache.isEmpty()) {
            loadWidget();
        }
        return listWidgetCache;
    }

    public static void setListWidgetCache(Map<Long, Widget> listWidgetCache) {
        Memory.listWidgetCache = listWidgetCache;
    }

    public static Map<Long, WidgetContent> getListWidgetContentCache() {
        if (listWidgetContentCache == null || listWidgetContentCache.isEmpty()) {
            loadWidgetContent();
        }
        return listWidgetContentCache;
    }

    public static void setListWidgetContentCache(Map<Long, WidgetContent> listWidgetContentCache) {
        Memory.listWidgetContentCache = listWidgetContentCache;
    }

}
