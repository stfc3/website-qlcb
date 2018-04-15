/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.dao;

import com.stfc.backend.domain.Banner;
import com.stfc.backend.domain.Document;
import com.stfc.utils.FunctionUtil;
import com.stfc.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author viettx
 */
@Repository
public class DocumentDAO {

    private static final Logger logger = Logger.getLogger(DocumentDAO.class);

    @Autowired
    SessionFactory sessionFactory;

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     *
     * @return
     */
    public List<Document> getAllBanner() {
        try {
            Query query = getCurrentSession().getNamedQuery("Document.GetAllDocument");
            List<Document> listReturnData = query.list();
            return listReturnData;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    public List<Document> search(Document document) {
        List<Document> listData = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("select d ");
//            sql.append("documentName, documentType, documentPath, categoryId");
//            sql.append("author, documentOrder, status, createDate,");
//            sql.append("modifiedDate from Document d where 1=1");
            sql.append(" from Document d where 1=1");
            if (StringUtils.valiString(document.getDocumentName())) {
                sql.append(" and d.documentName like :name escape '/'");
            }
            if (document.getDocumentType() != -1) {
                sql.append(" and d.documentType = :type");
            }
            if (document.getCategoryId() != -1) {
                sql.append(" and d.categoryId = :category");
            }
            if (document.getStatus() != -1) {
                sql.append(" and d.status = :status");
            }
            sql.append(" order by d.createDate, d.modifiedDate");
            Query query = getCurrentSession().createQuery(sql.toString());

            if (StringUtils.valiString(document.getDocumentName())) {
                query.setParameter("name", "%" + FunctionUtil.escapeCharacter(document.getDocumentName()) + "%");
            }
            if (document.getDocumentType() != -1) {
                query.setParameter("type", document.getDocumentType());
            }
            if (document.getCategoryId() != -1) {
                query.setParameter("category", document.getCategoryId());
            }
            if (document.getStatus() != -1) {
                query.setParameter("status", document.getStatus());
            }
            listData = query.list();
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return listData;
    }

    public void save(Document document) {
        getCurrentSession().saveOrUpdate(document);
    }

}
