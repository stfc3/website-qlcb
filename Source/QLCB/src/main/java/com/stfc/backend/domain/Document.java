/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.domain;

import com.stfc.utils.StringUtils;
import java.io.File;
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
import javax.persistence.Transient;

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
    private Integer documentType;
    private String documentTypeName;
    private String documentPath;
    private String fileName;
    private Long categoryId;
    private String categoryName;
    private String author;
    private Integer documentOrder;
    private Integer status;
    private Date createDate;
    private Date modifiedDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id", unique = true, nullable = true)
    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    @Column(name = "document_name", unique = false, nullable = false)
    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    @Column(name = "document_type", unique = true, nullable = false)
    public Integer getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Integer documentType) {
        this.documentType = documentType;
    }

    @Column(name = "document_path", unique = true, nullable = true)
    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    @Column(name = "category_id", unique = true, nullable = false)
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "author", unique = true, nullable = true)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "document_order", unique = false, nullable = true)
    public Integer getDocumentOrder() {
        return documentOrder;
    }

    public void setDocumentOrder(Integer documentOrder) {
        this.documentOrder = documentOrder;
    }

    @Column(name = "status", unique = false, nullable = false)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "create_date", unique = false, nullable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "modified_date", unique = false, nullable = true)
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * @return the fileName
     */
    @Transient
    public String getFileName() {
        if (StringUtils.valiString(documentPath)) {
            String[] arr = documentPath.split(File.separator);
            fileName = arr[arr.length - 1];
        }

        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Transient
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Transient
    public String getDocumentTypeName() {
        return documentTypeName;
    }

    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    @Override
    public String toString() {
        return "Document{" + "documentId=" + documentId + ", documentName="
                + documentName + ", documentType=" + documentType
                + ", documentTypeName=" + documentTypeName
                + ", documentPath=" + documentPath + ", fileName="
                + fileName + ", categoryId=" + categoryId
                + ", categoryName=" + categoryName + ", author="
                + author + ", documentOrder=" + documentOrder
                + ", status=" + status + ", createDate="
                + createDate + ", modifiedDate=" + modifiedDate + '}';
    }

}
