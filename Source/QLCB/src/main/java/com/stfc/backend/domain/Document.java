/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author viettx
 */
@Entity
@Table(name = "stfc_document")
@NamedQueries({
    @NamedQuery(name = "Document.GetAllDocument", query = "FROM Document u order by createDate, modifiedDate desc")})
public class Document implements Serializable {

    /**
     * Table: stfc_document Columns: document_id	bigint(20) AI PK document_name
     * varchar(255) document_type	int(11) document_path	varchar(255) category_id
     * bigint(20) author	varchar(100) document_order	int(11) status	int(11)
     * create_date	timestamp modified_date	timestamp
     *
     */
    private Long documentId;
    private String documentName;
    private int documentType;
    private String documentPath;
    private int categoryId;
    private String author;
    private int documentOrder;
    private int status;
    private Date createDate;
    private Date modifiedDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id", unique = true, nullable = true, insertable = true, updatable = true)
    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    @Column(name = "document_name", unique = false, nullable = false, insertable = true, updatable = true)
    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    @Column(name = "document_type", unique = true, nullable = false, insertable = true, updatable = true)
    public int getDocumentType() {
        return documentType;
    }

    public void setDocumentType(int documentType) {
        this.documentType = documentType;
    }

    @Column(name = "document_path", unique = true, nullable = true, insertable = true, updatable = true)
    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    @Column(name = "category_id", unique = true, nullable = false, insertable = true, updatable = true)
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "author", unique = true, nullable = true, insertable = true, updatable = true)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "document_order", unique = false, nullable = true, insertable = true, updatable = true)
    public int getDocumentOrder() {
        return documentOrder;
    }

    public void setDocumentOrder(int documentOrder) {
        this.documentOrder = documentOrder;
    }

    @Column(name = "status", unique = false, nullable = false, insertable = true, updatable = true)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Column(name = "create_date", unique = false, nullable = false, insertable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "modified_date", unique = false, nullable = false, insertable = true, updatable = true)
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

}
