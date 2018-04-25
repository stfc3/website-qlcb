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
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author dmin
 */
@Entity
@Table(name = "stfc_posts")
@NamedQuery(name = "Post.getPostByType", query = "FROM Post p WHERE p.postStatus = 3 AND isPrivate = :isPrivate")
public class Post implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", unique = false, nullable = true)
    private Long postId;
    @Column(name = "author")
    private String author;
    @Column(name = "post_title")
    private String postTitle;
    @Column(name = "post_excerpt")
    private String postExcerpt;
    @Column(name = "post_content")
    private String postContent;
    @Column(name = "post_tag")
    private String postTag;
    @Column(name = "is_pin")
    private Integer isPin;
    @Column(name = "is_private")
    private Integer isPrivate;
    @Column(name = "featured_image")
    private String featuredImage;
    @Column(name = "post_slug")
    private String postSlug;
    @Column(name = "post_order")
    private Integer postOrder;
    @Column(name = "post_status")
    private Integer postStatus;
    @Column(name = "post_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date postDate;
    @Column(name = "effect_from_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fromDate;
    @Column(name = "effect_to_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date toDate;
    @Column(name = "create_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "modified_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date modifiedDate;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostExcerpt() {
        return postExcerpt;
    }

    public void setPostExcerpt(String postExcerpt) {
        this.postExcerpt = postExcerpt;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostTag() {
        return postTag;
    }

    public void setPostTag(String postTag) {
        this.postTag = postTag;
    }

    public Integer getIsPin() {
        return isPin;
    }

    public void setIsPin(Integer isPin) {
        this.isPin = isPin;
    }

    public Integer getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Integer isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public String getPostSlug() {
        return postSlug;
    }

    public void setPostSlug(String postSlug) {
        this.postSlug = postSlug;
    }

    public Integer getPostOrder() {
        return postOrder;
    }

    public void setPostOrder(Integer postOrder) {
        this.postOrder = postOrder;
    }

    public Integer getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(Integer postStatus) {
        this.postStatus = postStatus;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    
}
