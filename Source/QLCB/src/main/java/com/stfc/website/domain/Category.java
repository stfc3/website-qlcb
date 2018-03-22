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
@Table(name = "stfc_categories")
@NamedQuery(name = "Category.getAllCategory", query = "FROM Category u WHERE category_status = 1")
public class Category {

    private long categoryId;
    private String categoryName;
    private long categoryParent;
    private String categorySlug;
    private int categoryOrder;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "category_name")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Column(name = "category_parent")
    public long getCategoryParent() {
        return categoryParent;
    }

    public void setCategoryParent(long categoryParent) {
        this.categoryParent = categoryParent;
    }

    @Column(name = "category_slug")
    public String getCategorySlug() {
        return categorySlug;
    }

    public void setCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
    }

    @Column(name = "category_order")
    public int getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(int categoryOrder) {
        this.categoryOrder = categoryOrder;
    }

}
