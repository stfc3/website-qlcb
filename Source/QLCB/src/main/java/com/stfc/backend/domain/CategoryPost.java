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
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author dmin
 */
@Entity
@Table(name = "stfc_category_post")
public class CategoryPost implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "map_id", unique = false, nullable = true)
    private Long categoryPostId;
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "post_status")
    private Integer status;
    @Column(name = "create_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;

    public Long getCategoryPostId() {
        return categoryPostId;
    }

    public void setCategoryPostId(Long categoryPostId) {
        this.categoryPostId = categoryPostId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
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

}
