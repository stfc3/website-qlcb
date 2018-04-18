/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.domain;

import java.io.Serializable;
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
@Table(name = "stfc_widget")
@NamedQuery(name = "Widget.getAllWidget", query = "FROM Widget u WHERE status = 1 ORDER BY order")
public class Widget implements Serializable {

    private Long widgetId;
    private String widgetCode;
    private String widgetName;
    private String widgetType;
    private String widgetPosition;
    private int order;
    private String description;
    private int status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "widget_id")
    public Long getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(Long widgetId) {
        this.widgetId = widgetId;
    }

    @Column(name = "widget_code")
    public String getWidgetCode() {
        return widgetCode;
    }

    public void setWidgetCode(String widgetCode) {
        this.widgetCode = widgetCode;
    }

    @Column(name = "widget_name")
    public String getWidgetName() {
        return widgetName;
    }

    public void setWidgetName(String widgetName) {
        this.widgetName = widgetName;
    }

    @Column(name = "widget_type")
    public String getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(String widgetType) {
        this.widgetType = widgetType;
    }

    @Column(name = "widget_position")
    public String getWidgetPosition() {
        return widgetPosition;
    }

    public void setWidgetPosition(String widgetPosition) {
        this.widgetPosition = widgetPosition;
    }

    @Column(name = "widget_order")
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
