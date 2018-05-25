/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author viettx
 */
@Entity
@Table(name = "stfc_enroll_students")
public class Enroll {

    /**
     * Table: stfc_enroll_students Columns: student_id	bigint(20) AI PK
     * student_name	varchar(500) birthday	varchar(50) address	varchar(2000)
     * email	varchar(500) phone	varchar(20) class_id	bigint(20) register_date
     * timestamp create_date	timestamp
     *
     */
    @Column(name = "student_id", unique = false, nullable = false, insertable = true, updatable = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    @Column(name = "student_name", unique = false, nullable = false, insertable = true, updatable = true)
    private String studentName;
    @Column(name = "birthday", unique = false, nullable = true, insertable = true, updatable = true)
    private String birthday;
    @Column(name = "address", unique = false, nullable = true, insertable = true, updatable = true)
    private String address;
    @Column(name = "class_id", unique = false, nullable = false, insertable = true, updatable = true)
    private Long classId;
    @Column(name = "email", unique = false, nullable = false, insertable = true, updatable = true)
    private String email;
    @Column(name = "phone", unique = false, nullable = false, insertable = true, updatable = true)
    private String phone;
    @Column(name = "className", unique = false, nullable = false, insertable = true, updatable = true)
    private String className;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    

}
