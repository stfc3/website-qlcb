/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author daond
 */
@Entity
@Table(name = "stfc_class")
@NamedQuery(name = "Class.getAllClass", query = "FROM Class u WHERE status = 1 ORDER BY class_order, create_date")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long classId;
    @Column(name = "class_name")
    private String className;
    @Column(name = "class_order")
    private Integer classOrder;
    @Column(name = "status")
    private Integer status;
    @Column(name = "create_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP) 
    private Date createDate;
    @Column(name = "description")
    private String description;


    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getClassOrder() {
        return classOrder;
    }

    public void setClassOrder(Integer classOrder) {
        this.classOrder = classOrder;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
