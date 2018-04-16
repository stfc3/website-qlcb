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
import javax.xml.transform.Result;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
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
        logger.info("Document: " + document.toString());
        List<Document> listData = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("select d.document_id as documentId, ");
            sql.append("d.document_name as documentName, d.document_type as documentType, d.document_path as documentPath, d.category_id as categoryId,");
            sql.append("c.category_name as categoryName,");
            sql.append("d.author as author, d.document_order as documentOrder,d.status as status,d.create_date as createDate,");
            sql.append("d.modified_date as modifiedDate ");
            sql.append(" from stfc_document d left join stfc_categories c ");
            sql.append(" on d.category_id = c.category_id  where 1=1 ");
            if (StringUtils.valiString(document.getDocumentName())) {
                sql.append(" and d.document_name like :name escape '/'");
            }
            if (document.getDocumentType() != null && document.getDocumentType() != -1) {
                sql.append(" and d.document_type = :type");
            }
            if (document.getCategoryId() != null && document.getCategoryId() != -1) {
                sql.append(" and d.category_id = :category");
            }
            if (document.getStatus() != null && document.getStatus() != -1) {
                sql.append(" and d.status = :status");
            }
            sql.append(" order by d.create_date, d.modified_date");
            Query query = getCurrentSession().createSQLQuery(sql.toString())
                    .addScalar("documentId", StandardBasicTypes.LONG)
                    .addScalar("documentName", StandardBasicTypes.STRING)
                    .addScalar("documentType", StandardBasicTypes.INTEGER)
                    .addScalar("documentPath", StandardBasicTypes.STRING)
                    .addScalar("categoryId", StandardBasicTypes.LONG)
                    .addScalar("categoryName", StandardBasicTypes.STRING)
                    .addScalar("author", StandardBasicTypes.STRING)
                    .addScalar("documentOrder", StandardBasicTypes.INTEGER)
                    .addScalar("status", StandardBasicTypes.INTEGER)
                    .addScalar("createDate", StandardBasicTypes.DATE)
                    .addScalar("modifiedDate", StandardBasicTypes.DATE)
                    .setResultTransformer(Transformers.aliasToBean(Document.class));

            if (StringUtils.valiString(document.getDocumentName())) {
                query.setParameter("name", "%" + FunctionUtil.escapeCharacter(document.getDocumentName()) + "%");
            }
            if (document.getDocumentType() != null && document.getDocumentType() != -1) {
                query.setParameter("type", document.getDocumentType());
            }
            if (document.getCategoryId() != null && document.getCategoryId() != -1) {
                query.setParameter("category", document.getCategoryId());
            }
            if (document.getStatus() != null && document.getStatus() != -1) {
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
        logger.info("Document DAO: " + document.toString());
    }

}