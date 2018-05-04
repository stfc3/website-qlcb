/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.dao;

import com.stfc.utils.FunctionUtil;
import com.stfc.utils.StringUtils;
import com.stfc.website.domain.User;
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
 * @author admin
 */
@Repository
public class UserDAO {

    private static final Logger logger = Logger.getLogger(UserDAO.class);

    @Autowired
    SessionFactory sessionFactory;

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public List<User> getAllUser() {
        try {
            Query query = getCurrentSession().getNamedQuery("User.GetAllUser");
            List<User> listReturnData = query.list();
            return listReturnData;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     *
     * @param user
     * @return user_id bigint(20) AI PK user_name varchar(100) first_name
     * varchar(100) last_name varchar(100) email varchar(255) birthday date
     * password varchar(100) role int(11) create_date timestamp
     */
    public List<User> search(User user) {
        try {
            StringBuilder builder = new StringBuilder("select user_id as userId, user_name as userName, ");
            builder.append("first_name as firstName, last_name as lastName, email as email");
            builder.append(", birthday as birthday, password as password, role as role, create_date as createDate, status as status");
            builder.append(" from stfc_users where 1 = 1");
            if (StringUtils.valiString(user.getUserName())) {
                builder.append(" and user_name like :userName escape '/'");
            }
            if (StringUtils.valiString(user.getEmail())) {
                builder.append(" and email = :email");
            }
            if (user.getStatus() != null && user.getStatus() != -1) {
                builder.append(" and status = :status");
            }
            builder.append(" order by create_date desc");
            Query query = getCurrentSession().createSQLQuery(builder.toString())
                    .addScalar("userId", StandardBasicTypes.LONG)
                    .addScalar("userName", StandardBasicTypes.STRING)
                    .addScalar("firstName", StandardBasicTypes.STRING)
                    .addScalar("lastName", StandardBasicTypes.STRING)
                    .addScalar("email", StandardBasicTypes.STRING)
                    .addScalar("birthday", StandardBasicTypes.DATE)
                    .addScalar("password", StandardBasicTypes.STRING)
                    .addScalar("role", StandardBasicTypes.INTEGER)
                    .addScalar("createDate", StandardBasicTypes.DATE)
                    .addScalar("status", StandardBasicTypes.INTEGER)
                    .setResultTransformer(
                            Transformers.aliasToBean(User.class));
            if (StringUtils.valiString(user.getUserName())) {
                query.setParameter("userName", "%" + FunctionUtil.escapeCharacter(user.getUserName()) + "%");
            }
            if (StringUtils.valiString(user.getEmail())) {
                query.setParameter("email", user.getEmail());
            }
            if (user.getStatus() != null && user.getStatus() != -1) {
                query.setParameter("status", user.getStatus());
            }
            List<User> listData = query.list();
            return listData;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public void save(User user) {
        getCurrentSession().saveOrUpdate(user);
    }

    public void update(User user) {
        getCurrentSession().update(user);
    }
}
