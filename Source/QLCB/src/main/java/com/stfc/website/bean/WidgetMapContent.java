/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.bean;

import java.util.List;

/**
 *
 * @author daond
 */
public class WidgetMapContent {
    private long widgetId;
    private String widgetCode;
    private String widgetName;
    private String widgetType;
    private String widgetPosition;
    private int order;
    private String description;
    private int status;
    private List<WidgetContent> listContent;

    public long getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(long widgetId) {
        this.widgetId = widgetId;
    }

    public String getWidgetCode() {
        return widgetCode;
    }

    public void setWidgetCode(String widgetCode) {
        this.widgetCode = widgetCode;
    }

    public String getWidgetName() {
        return widgetName;
    }

    public void setWidgetName(String widgetName) {
        this.widgetName = widgetName;
    }

    public String getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(String widgetType) {
        this.widgetType = widgetType;
    }

    public String getWidgetPosition() {
        return widgetPosition;
    }

    public void setWidgetPosition(String widgetPosition) {
        this.widgetPosition = widgetPosition;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<WidgetContent> getListContent() {
        return listContent;
    }

    public void setListContent(List<WidgetContent> listContent) {
        this.listContent = listContent;
    }
    
}
