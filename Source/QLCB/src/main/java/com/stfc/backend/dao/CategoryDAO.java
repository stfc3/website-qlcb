package com.stfc.backend.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stfc.website.domain.Category;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author admin
 * @since 02/04/2018
 *
 */
@Repository
public class CategoryDAO {

    private static final Logger logger = Logger.getLogger(CategoryDAO.class);

    @Autowired
    SessionFactory sessionFactory;

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public  List<Category> getCategory() {
        try {
            StringBuilder sql= new StringBuilder("SELECT c.category_id as categoryId, c.category_parent as categoryParent, c.category_name as categoryName,");
            sql.append(" c.category_slug as categorySlug, c.category_order categoryOrder,  c.status as categoryStatus,");
            sql.append(" c.create_date as createDate, c.modified_date as modifiedDate, cp.category_name as categoryParentName");
            sql.append(" FROM stfc_categories c LEFT JOIN stfc_categories cp");
            sql.append(" ON c.category_parent=cp.category_id AND cp.status=1");
            sql.append(" WHERE c.status=1");
            sql.append(" ORDER BY c.category_name COLLATE utf8_persian_ci");
            Query query = getCurrentSession().createSQLQuery(sql.toString())
                    .addScalar("categoryId", StandardBasicTypes.LONG)
                    .addScalar("categoryParent", StandardBasicTypes.LONG)
                    .addScalar("categoryName", StandardBasicTypes.STRING)
                    .addScalar("categorySlug", StandardBasicTypes.STRING)
                    .addScalar("categoryOrder", StandardBasicTypes.INTEGER)
                    .addScalar("categoryStatus", StandardBasicTypes.INTEGER)
                    .addScalar("createDate", StandardBasicTypes.TIMESTAMP)
                    .addScalar("modifiedDate", StandardBasicTypes.TIMESTAMP)
                    .addScalar("categoryParentName", StandardBasicTypes.STRING)
                    .setResultTransformer(Transformers.aliasToBean(Category.class));
            List<Category> listReturnData = query.list();
            return listReturnData;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    public void saveOrUpdate(Category category){
        getCurrentSession().saveOrUpdate(category);
    }

}
