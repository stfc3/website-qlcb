/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author viettx
 */
@Entity
@Table(name = "stfc_feedback")
public class FeedBack {

    /**
     * Table: stfc_feedback Columns: feedback_id	bigint(20) AI PK name
     * varchar(200) phone	varchar(20) email	varchar(100) content	text
     * feedback_date	timestamp create_date	timestamp
     *
     */
    @Column(name = "feedback_id", unique = false, nullable = false, insertable = true, updatable = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackID;
    @Column(name = "phone", unique = false, nullable = false, insertable = true, updatable = true)
    private String phone;
    @Column(name = "email", unique = false, nullable = false, insertable = true, updatable = true)
    private String email;
    @Column(name = "content", unique = false, nullable = false, insertable = true, updatable = true)
    private String content;
    @Column(name = "name", unique = false, nullable = false, insertable = true, updatable = true)
    private String name;
    @Column(name = "create_date", unique = false, nullable = false, insertable = true, updatable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;
    @Transient
    private int index;

    public Long getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(Long feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
    

}
