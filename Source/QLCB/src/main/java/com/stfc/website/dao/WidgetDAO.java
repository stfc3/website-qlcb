/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.dao;

import com.stfc.website.domain.Widget;
import com.stfc.website.bean.WidgetContent;
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
 * @author dmin
 */
@Repository
public class WidgetDAO {

    private static final Logger logger = Logger.getLogger(WidgetDAO.class);

    @Autowired
    SessionFactory sessionFactory;

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public List<Widget> getAllWidget() {

        Query query = getCurrentSession().getNamedQuery("Widget.getAllWidget");
        return (List<Widget>) query.list();
    }

    public List<WidgetContent> getAllWidgetContent(List<Long> lstWidgetId) {
        try {
            if (lstWidgetId != null && !lstWidgetId.isEmpty()) {
                StringBuilder vstrSql = new StringBuilder("SELECT widget_content_id as widgetContentId, widget_id as widgetId, widget_content_name as widgetContentName,");
                vstrSql.append(" widget_content as widgetContent, widget_image as widgetImage, widget_content_order as widgetOrder, widget_content_type as widgetType");
                vstrSql.append(" FROM stfc_widget_content");
                vstrSql.append(" WHERE status = 1 AND widget_id IN (:lstWidgetId)");
                vstrSql.append(" ORDER BY widget_content_order ");
                Query query = getCurrentSession()
                        .createSQLQuery(vstrSql.toString())
                        .addScalar("widgetContentId", StandardBasicTypes.LONG)
                        .addScalar("widgetId", StandardBasicTypes.LONG)
                        .addScalar("widgetContentName", StandardBasicTypes.STRING)
                        .addScalar("widgetContent", StandardBasicTypes.STRING)
                        .addScalar("widgetImage", StandardBasicTypes.STRING)
                        .addScalar("widgetOrder", StandardBasicTypes.INTEGER)
                        .addScalar("widgetType", StandardBasicTypes.STRING)
                        .setResultTransformer(
                                Transformers.aliasToBean(WidgetContent.class));
                query.setParameterList("lstWidgetId", lstWidgetId);
                return (List<WidgetContent>) query.list();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
