/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website;

import com.stfc.utils.Constants;
import com.stfc.website.bean.Banner;
import com.stfc.website.domain.Widget;
import com.stfc.website.bean.WidgetContent;
import com.stfc.website.bean.WidgetMapContent;
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
    public static Map<Long, WidgetMapContent> listWidgetMapContentCache;
    public static List<Long> lstWidgetId = new ArrayList<>();
    public static List<Long> lstCategoryId = new ArrayList<>();

    public static Map<Long, Banner> listBannerCache;

    /**
     * Ham start up dashboard
     */
    public void startup() {
        try {
            logger.info("==============STARTUP MEMORY==============");
            loadBanner();
            loadWidget();
            loadWidgetContent();
            loadWidgetMapContent();
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
        listBannerCache.clear();
        listWidgetCache.clear();
        listWidgetContentCache.clear();
        listWidgetMapContentCache.clear();
        lstWidgetId.clear();
        lstCategoryId.clear();
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
        loadCategoryId();
    }

    public static void loadCategoryId() {
        List<WidgetContent> vlstWidgetContent = new ArrayList<>(listWidgetContentCache.values());
        for (WidgetContent wc : vlstWidgetContent) {
            if (Constants.WIDGET_CONTENT_TYPE_CATEGORY.equals(wc.getWidgetType())) {
                lstCategoryId.add(Long.parseLong(wc.getWidgetContent()));
            }
        }
    }

    public static void loadWidgetMapContent() {
        if (listWidgetCache == null || listWidgetCache.isEmpty()) {
            loadWidget();
        }
        if (listWidgetContentCache == null || listWidgetContentCache.isEmpty()) {
            loadWidgetContent();
        }
        List<Widget> vlstWidget = new ArrayList<>(listWidgetCache.values());
        List<WidgetContent> vlstWidgetContent = new ArrayList<>(listWidgetContentCache.values());
        List<WidgetMapContent> vlstWidgetMapContent = new ArrayList<>();;
        List<WidgetContent> vlstWidgetContentByWidget;
        WidgetMapContent wmc;
        for (Widget wg : vlstWidget) {
            vlstWidgetContentByWidget = new ArrayList<>();
            for (int i = 0; i < vlstWidgetContent.size(); i++) {
                if (vlstWidgetContent.get(i).getWidgetId() == wg.getWidgetId()) {
                    vlstWidgetContentByWidget.add(vlstWidgetContent.get(i));
                }
            }
            wmc = new WidgetMapContent();
            wmc.setWidgetId(wg.getWidgetId());
            wmc.setWidgetCode(wg.getWidgetCode());
            wmc.setWidgetName(wg.getWidgetName());
            wmc.setWidgetType(wg.getWidgetType());
            wmc.setWidgetPosition(wg.getWidgetPosition());
            wmc.setOrder(wg.getOrder());
            wmc.setListContent(vlstWidgetContentByWidget);
            vlstWidgetMapContent.add(wmc);
        }
        if (vlstWidgetMapContent != null && !vlstWidgetMapContent.isEmpty()) {
            listWidgetMapContentCache = vlstWidgetMapContent.stream().collect(Collectors.toMap(WidgetMapContent::getWidgetId, widgetMapContent -> widgetMapContent));
        }
    }

    public static void loadBanner() {
        List<Banner> vlstBanner;
        vlstBanner = widgetService.getBanner();
        if (vlstBanner != null) {
            listBannerCache = vlstBanner.stream().collect(Collectors.toMap(Banner::getPostId, banner -> banner));
        } else {
            listBannerCache = new HashMap<>();
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

    public static Map<Long, WidgetMapContent> getListWidgetMapContentCache() {
        if (listWidgetMapContentCache == null || listWidgetMapContentCache.isEmpty()) {
            loadWidgetMapContent();
        }
        return listWidgetMapContentCache;
    }

    public static void setListWidgetMapContentCache(Map<Long, WidgetMapContent> listWidgetMapContentCache) {
        Memory.listWidgetMapContentCache = listWidgetMapContentCache;
    }

    public static List<Long> getLstCategoryId() {
        if (lstCategoryId == null && lstCategoryId.isEmpty()) {
            loadCategoryId();
        }
        return lstCategoryId;
    }

    public static void setLstCategoryId(List<Long> lstCategoryId) {
        Memory.lstCategoryId = lstCategoryId;
    }

    public static Map<Long, Banner> getListBannerCache() {
        if (listBannerCache == null && listBannerCache.isEmpty()) {
            loadBanner();
        }
        return listBannerCache;
    }

    public static void setListBannerCache(Map<Long, Banner> listBannerCache) {
        Memory.listBannerCache = listBannerCache;
    }

}
