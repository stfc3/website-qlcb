/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.bean;

/**
 *
 * @author daond
 */

public class WidgetContent {

    private long widgetContentId;
    private long widgetId;
    private String widgetContentName;
    private String widgetContent;
    private String widgetImage;
    private int widgetOrder;
    private String widgetType;
    private String detailMoreSlug;

    public long getWidgetContentId() {
        return widgetContentId;
    }

    public void setWidgetContentId(long widgetContentId) {
        this.widgetContentId = widgetContentId;
    }

    public long getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(long widgetId) {
        this.widgetId = widgetId;
    }

    public String getWidgetContentName() {
        return widgetContentName;
    }

    public void setWidgetContentName(String widgetContentName) {
        this.widgetContentName = widgetContentName;
    }

    public String getWidgetContent() {
        return widgetContent;
    }

    public void setWidgetContent(String widgetContent) {
        this.widgetContent = widgetContent;
    }

    public String getWidgetImage() {
        return widgetImage;
    }

    public void setWidgetImage(String widgetImage) {
        this.widgetImage = widgetImage;
    }

    public int getWidgetOrder() {
        return widgetOrder;
    }

    public void setWidgetOrder(int widgetOrder) {
        this.widgetOrder = widgetOrder;
    }

    public String getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(String widgetType) {
        this.widgetType = widgetType;
    }

    public String getDetailMoreSlug() {
        return detailMoreSlug;
    }

    public void setDetailMoreSlug(String detailMoreSlug) {
        this.detailMoreSlug = detailMoreSlug;
    }

}
