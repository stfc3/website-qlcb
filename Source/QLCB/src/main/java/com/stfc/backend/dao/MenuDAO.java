package com.stfc.backend.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stfc.backend.domain.Menu;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author admin
 * @since 02/04/2018
 *
 */
@Repository
public class MenuDAO {

    private static final Logger logger = Logger.getLogger(MenuDAO.class);

    @Autowired
    SessionFactory sessionFactory;

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public List<Menu> getMenuByType(Integer menuType) {
        try {
            StringBuilder sql= new StringBuilder("SELECT m.menu_id as menuId, m.menu_parent as menuParent, m.menu_name as menuName,");
            sql.append(" m.menu_slug as menuSlug, m.menu_order menuOrder, m.menu_type as menuType, m.status as menuStatus,");
            sql.append(" m.create_date as createDate, m.modified_date as modifiedDate, mp.menu_name as menuParentName");
            sql.append(" FROM stfc_menu m LEFT JOIN stfc_menu mp");
            sql.append(" ON m.menu_parent=mp.menu_id AND m.menu_type=mp.menu_type AND m.status=1 AND mp.status=1");
            sql.append(" WHERE m.menu_type = :menuType");
            sql.append(" ORDER BY m.menu_order");
            Query query = getCurrentSession().createSQLQuery(sql.toString())
                    .addScalar("menuId", StandardBasicTypes.LONG)
                    .addScalar("menuParent", StandardBasicTypes.LONG)
                    .addScalar("menuName", StandardBasicTypes.STRING)
                    .addScalar("menuSlug", StandardBasicTypes.STRING)
                    .addScalar("menuOrder", StandardBasicTypes.INTEGER)
                    .addScalar("menuType", StandardBasicTypes.INTEGER)
                    .addScalar("menuStatus", StandardBasicTypes.INTEGER)
                    .addScalar("createDate", StandardBasicTypes.TIMESTAMP)
                    .addScalar("modifiedDate", StandardBasicTypes.TIMESTAMP)
                    .addScalar("menuParentName", StandardBasicTypes.STRING)
                    .setResultTransformer(Transformers.aliasToBean(Menu.class));
            query.setParameter("menuType", menuType);
            List<Menu> listReturnData = query.list();
            return listReturnData;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    public void saveOrUpdate(Menu menu){
        getCurrentSession().saveOrUpdate(menu);
    }

}
