/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author daond
 */
@Entity
@Table(name = "stfc_widget_content")
@NamedQuery(name = "WidgetContent.getAllWidgetContent", query = "FROM WidgetContent u WHERE widgetId in :lstWidgetID status = 1 ORDER BY order")
public class WidgetContent {

    private long widgetContentId;
    private long widgetId;
    private long categoryId;
    private String widgetContentTitle;
    private String widgetContent;
    private String widgetImage;
    private int order;
    private int status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "widget_content_id")
    public long getWidgetContentId() {
        return widgetContentId;
    }

    public void setWidgetContentId(long widgetContentId) {
        this.widgetContentId = widgetContentId;
    }

    @Column(name = "widget_id")
    public long getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(long widgetId) {
        this.widgetId = widgetId;
    }

    @Column(name = "category_id")
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "widget_content_title")
    public String getWidgetContentTitle() {
        return widgetContentTitle;
    }

    public void setWidgetContentTitle(String widgetContentTitle) {
        this.widgetContentTitle = widgetContentTitle;
    }

    @Column(name = "widget_content")
    public String getWidgetContent() {
        return widgetContent;
    }

    public void setWidgetContent(String widgetContent) {
        this.widgetContent = widgetContent;
    }

    @Column(name = "widget_image")
    public String getWidgetImage() {
        return widgetImage;
    }

    public void setWidgetImage(String widgetImage) {
        this.widgetImage = widgetImage;
    }

    @Column(name = "widget_content_order")
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
