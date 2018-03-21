/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.dao;

import com.stfc.website.bean.Banner;
import com.stfc.website.bean.Post;
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
                vstrSql.append(" , COALESCE(stfc_categories.category_slug,'') as detailMoreSlug ");
                vstrSql.append(" FROM stfc_widget_content left join stfc_categories on stfc_widget_content.widget_content = stfc_categories.category_id ");
//                vstrSql.append(" FROM stfc_widget_content");
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
                        .addScalar("detailMoreSlug", StandardBasicTypes.STRING)
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

    public List<Post> getPost(List<Long> lstCategoryId) {
        try {
            if (lstCategoryId != null && !lstCategoryId.isEmpty()) {
                StringBuilder vstrSql = new StringBuilder();
                vstrSql.append("SELECT p.post_id as postId, p.author as author, p.post_title as postTitle, p.post_excerpt as postExcerpt,");
                vstrSql.append(" p.post_content as postContent, p.post_tag as postTag, m.category_id as categoryId, p.is_pin as isPin,");
                vstrSql.append(" p.featured_image as featuredImage, p.post_slug as postSlug, p.post_order as postOrder, p.post_date as postDate");
                vstrSql.append(" FROM stfc_posts p INNER JOIN stfc_category_post m ON p.post_id = m.post_id");
                vstrSql.append(" WHERE m.category_id IN (:lstCategoryId)");
                vstrSql.append(" AND p.post_status = 3");
                vstrSql.append(" and p.effect_from_date <= sysdate()");
                vstrSql.append(" and p.effect_to_date >= sysdate()");
                vstrSql.append(" ORDER BY p.is_pin desc, p.post_order, p.create_date ");
                Query query = getCurrentSession()
                        .createSQLQuery(vstrSql.toString())
                        .addScalar("postId", StandardBasicTypes.LONG)
                        .addScalar("author", StandardBasicTypes.STRING)
                        .addScalar("postTitle", StandardBasicTypes.STRING)
                        .addScalar("postExcerpt", StandardBasicTypes.STRING)
                        .addScalar("postContent", StandardBasicTypes.STRING)
                        .addScalar("postTag", StandardBasicTypes.STRING)
                        .addScalar("categoryId", StandardBasicTypes.LONG)
                        .addScalar("isPin", StandardBasicTypes.INTEGER)
                        .addScalar("featuredImage", StandardBasicTypes.STRING)
                        .addScalar("postSlug", StandardBasicTypes.STRING)
                        .addScalar("postOrder", StandardBasicTypes.INTEGER)
                        .addScalar("postDate", StandardBasicTypes.DATE)
                        .setResultTransformer(
                                Transformers.aliasToBean(Post.class));
                query.setParameterList("lstCategoryId", lstCategoryId);
                return (List<Post>) query.list();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public List<Banner> getBanner() {
        try {
            StringBuilder vstrSql = new StringBuilder();
            vstrSql.append(" SELECT banner_id as postId, banner_name as bannerName, banner_image as bannerImage,");
            vstrSql.append(" banner_url as bannerUrl, banner_type as bannerType, banner_order as bannerOrder ");
            vstrSql.append(" FROM stfc_banner");
            vstrSql.append(" WHERE banner_status = 1  ");
            vstrSql.append(" AND effect_from_date <= sysdate()");
            vstrSql.append(" AND (effect_to_date >= sysdate() or effect_to_date is null)");
            vstrSql.append(" ORDER BY banner_order ");
            Query query = getCurrentSession()
                    .createSQLQuery(vstrSql.toString())
                    .addScalar("postId", StandardBasicTypes.LONG)
                    .addScalar("bannerName", StandardBasicTypes.STRING)
                    .addScalar("bannerImage", StandardBasicTypes.STRING)
                    .addScalar("bannerUrl", StandardBasicTypes.STRING)
                    .addScalar("bannerType", StandardBasicTypes.INTEGER)
                    .addScalar("bannerOrder", StandardBasicTypes.INTEGER)
                    .setResultTransformer(
                            Transformers.aliasToBean(Banner.class));
            return (List<Banner>) query.list();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
