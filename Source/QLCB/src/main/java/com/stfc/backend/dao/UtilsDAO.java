/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.dao;

import com.stfc.backend.domain.Enroll;
import com.stfc.backend.domain.FeedBack;
import com.stfc.utils.FunctionUtil;
import com.stfc.utils.StringUtils;
import java.util.List;
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
public class UtilsDAO {

    private static final Logger logger = Logger.getLogger(UtilsDAO.class);

    @Autowired
    SessionFactory sessionFactory;

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     *
     * @param enroll
     * @return
     */
    public List<Enroll> search(Enroll enroll) {
        try {
            StringBuilder builder = new StringBuilder("select student_id as studentId, ");
            builder.append("student_name as studentName, ");
            builder.append("email as email, phone as phone, ");
            builder.append("s.class_id as classId, c.class_name as className from stfc_enroll_students s ");
            builder.append("left join stfc_class c on s.class_id = c.class_id ");
            builder.append("where 1=1 ");
            if (StringUtils.valiString(enroll.getStudentName())) {
                builder.append(" and student_name like :studentName escape '/'");
            }
            if (StringUtils.valiString(enroll.getEmail())) {
                builder.append(" and email = :email");
            }
            if (StringUtils.valiString(enroll.getPhone())) {
                builder.append(" and phone = :phone");
            }
            if (enroll.getClassId() != null) {
                builder.append(" and s.class_id = :classId");
            }
            builder.append(" order by s.create_date desc");
            Query query = getCurrentSession().createSQLQuery(builder.toString())
                    .addScalar("studentId", StandardBasicTypes.LONG)
                    .addScalar("studentName", StandardBasicTypes.STRING)
                    .addScalar("email", StandardBasicTypes.STRING)
                    .addScalar("phone", StandardBasicTypes.STRING)
                    .addScalar("classId", StandardBasicTypes.LONG)
                    .addScalar("className", StandardBasicTypes.STRING)
                    .setResultTransformer(
                            Transformers.aliasToBean(Enroll.class));
            if (StringUtils.valiString(enroll.getStudentName())) {
                query.setParameter("studentName", "%" + FunctionUtil.escapeCharacter(enroll.getStudentName()) + "%");
            }
            if (StringUtils.valiString(enroll.getEmail())) {
                query.setParameter("email", enroll.getEmail());
            }
            if (StringUtils.valiString(enroll.getPhone())) {
                query.setParameter("phone", enroll.getPhone());
            }
            if (enroll.getClassId() != null) {
                query.setParameter("classId", enroll.getClassId());
            }
            List<Enroll> listReturn = query.list();
            return listReturn;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public List<FeedBack> onSearchFeedBack(FeedBack feedBack) {

        try {
            StringBuilder builder = new StringBuilder("select f from FeedBack f where 1=1 ");
            if (StringUtils.valiString(feedBack.getName())) {
                builder.append(" and f.name = :name");
            }
            if (StringUtils.valiString(feedBack.getPhone())) {
                builder.append(" and f.phone = :phone");
            }
            if (StringUtils.valiString(feedBack.getEmail())) {
                builder.append(" and f.email = :email");
            }
            builder.append(" order by createDate desc");
            Query query = getCurrentSession().createQuery(builder.toString());

            if (StringUtils.valiString(feedBack.getName())) {
                query.setParameter("name", feedBack.getName());
            }
            if (StringUtils.valiString(feedBack.getPhone())) {
                query.setParameter("phone", feedBack.getPhone());
            }
            if (StringUtils.valiString(feedBack.getEmail())) {
                query.setParameter("email", feedBack.getEmail());
            }
            List<FeedBack> listData = query.list();
            return listData;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
