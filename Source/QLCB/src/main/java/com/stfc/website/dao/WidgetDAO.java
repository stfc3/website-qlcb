/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.dao;

import com.stfc.website.bean.Banner;
import com.stfc.website.bean.Document;
import com.stfc.website.bean.Post;
import com.stfc.website.bean.UserToken;
import com.stfc.website.domain.Widget;
import com.stfc.website.domain.Class;
import com.stfc.website.bean.WidgetContent;
import com.stfc.website.domain.Category;
import com.stfc.website.domain.EnrollStudent;
import com.stfc.website.domain.Param;
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
                vstrSql.append(" WHERE stfc_widget_content.status = 1 AND widget_id IN (:lstWidgetId)");
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

    public List<Post> getPost(List<Long> lstCategoryId, int isPrivate) {
        try {
            if (lstCategoryId != null && !lstCategoryId.isEmpty()) {
                StringBuilder vstrSql = new StringBuilder();
                vstrSql.append("SELECT p.post_id as postId, p.author as author, p.post_title as postTitle, p.post_excerpt as postExcerpt,");
                vstrSql.append(" p.post_content as postContent, p.post_tag as postTag, m.category_id as categoryId, p.is_pin as isPin,");
                vstrSql.append(" p.featured_image as featuredImage, p.post_slug as postSlug, coalesce(p.post_order,0) as postOrder, p.post_date as postDate");
                vstrSql.append(" FROM stfc_posts p INNER JOIN stfc_category_post m ON p.post_id = m.post_id");
                vstrSql.append(" INNER JOIN stfc_categories c ON m.category_id = c.category_id");
                vstrSql.append(" WHERE m.category_id IN (:lstCategoryId)");
                vstrSql.append(" AND p.post_status = 3");
                vstrSql.append(" AND m.post_status = 1");
                vstrSql.append(" AND c.status = 1");
                vstrSql.append(" AND p.is_private IN (:isPrivate, 2)");
                vstrSql.append(" and p.effect_from_date <= sysdate()");
                vstrSql.append(" and (effect_to_date >= sysdate() or effect_to_date is null)");
                vstrSql.append(" and p.post_date <= sysdate()");
                vstrSql.append(" ORDER BY p.is_pin desc, p.post_date desc");
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
                query.setParameter("isPrivate", isPrivate);
                return (List<Post>) query.list();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public List<Post> getPostByCategoryId(Long categoryId, int limitPost, int isPrivate) {
        try {
            StringBuilder vstrSql = new StringBuilder();
            vstrSql.append("SELECT p.post_id as postId, p.author as author, p.post_title as postTitle, p.post_excerpt as postExcerpt,");
            vstrSql.append(" p.post_content as postContent, p.post_tag as postTag, m.category_id as categoryId, p.is_pin as isPin,");
            vstrSql.append(" p.featured_image as featuredImage, p.post_slug as postSlug, coalesce(p.post_order,0) as postOrder, p.post_date as postDate");
            vstrSql.append(" FROM stfc_posts p INNER JOIN stfc_category_post m ON p.post_id = m.post_id");
            vstrSql.append(" INNER JOIN stfc_categories c ON m.category_id = c.category_id");
            vstrSql.append(" WHERE m.category_id IN (:categoryId)");
            vstrSql.append(" AND p.post_status = 3");
            vstrSql.append(" AND m.post_status = 1");
            vstrSql.append(" AND c.status = 1");
            vstrSql.append(" AND p.is_private IN (:isPrivate, 2)");
            vstrSql.append(" and p.effect_from_date <= sysdate()");
            vstrSql.append(" and (effect_to_date >= sysdate() or effect_to_date is null)");
            vstrSql.append(" and p.post_date <= sysdate()");
            vstrSql.append(" ORDER BY p.is_pin desc, p.post_date desc ");
            if (limitPost > 0) {
                vstrSql.append(" LIMIT " + String.valueOf(limitPost));
            }
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
            query.setParameter("categoryId", categoryId);
            query.setParameter("isPrivate", isPrivate);
            return (List<Post>) query.list();
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

    public List<Param> getAllParam() {
        Query query = getCurrentSession().getNamedQuery("Param.getAllParam");
        return (List<Param>) query.list();
    }

    public List<Category> getAllCategory() {
        Query query = getCurrentSession().getNamedQuery("Category.getAllCategory");
        return (List<Category>) query.list();
    }

    public List<Post> getPostBySlug(String postSlug) {
        try {
            StringBuilder vstrSql = new StringBuilder();
            vstrSql.append("SELECT p.post_id as postId, p.author as author, p.post_title as postTitle, p.post_excerpt as postExcerpt,");
            vstrSql.append(" p.post_content as postContent, p.post_tag as postTag, m.category_id as categoryId, p.is_pin as isPin,");
            vstrSql.append(" p.featured_image as featuredImage, p.post_slug as postSlug, coalesce(p.post_order,0) as postOrder, p.post_date as postDate");
            vstrSql.append(" FROM stfc_posts p INNER JOIN stfc_category_post m ON p.post_id = m.post_id");
            vstrSql.append(" INNER JOIN stfc_categories c ON m.category_id = c.category_id");
            vstrSql.append(" WHERE p.post_slug = :postSlug");
            vstrSql.append(" AND p.post_status = 3");
            vstrSql.append(" AND m.post_status = 1");
            vstrSql.append(" AND c.status = 1");
            vstrSql.append(" and p.effect_from_date <= sysdate()");
            vstrSql.append(" and (effect_to_date >= sysdate() or effect_to_date is null)");
            vstrSql.append(" and p.post_date <= sysdate()");
            vstrSql.append(" ORDER BY p.is_pin desc, p.post_date desc ");
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
            query.setParameter("postSlug", postSlug);
            return (List<Post>) query.list();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public List<Post> getPostByCategorySlug(String categorySlug, int limitPost) {
        try {
            StringBuilder vstrSql = new StringBuilder();
            vstrSql.append("SELECT p.post_id as postId, p.author as author, p.post_title as postTitle, p.post_excerpt as postExcerpt,");
            vstrSql.append(" p.post_content as postContent, p.post_tag as postTag, m.category_id as categoryId, p.is_pin as isPin,");
            vstrSql.append(" p.featured_image as featuredImage, p.post_slug as postSlug, coalesce(p.post_order,0) as postOrder, p.post_date as postDate");
            vstrSql.append(" FROM stfc_posts p INNER JOIN stfc_category_post m ON p.post_id = m.post_id");
            vstrSql.append(" INNER JOIN stfc_categories c on c.category_id = m.category_id");
            vstrSql.append(" WHERE c.category_slug = :categorySlug");
            vstrSql.append(" AND p.post_status = 3");
            vstrSql.append(" AND m.post_status = 1");
            vstrSql.append(" AND c.status = 1");
            vstrSql.append(" and p.effect_from_date <= sysdate()");
            vstrSql.append(" and (effect_to_date >= sysdate() or effect_to_date is null)");
            vstrSql.append(" and p.post_date <= sysdate()");
            vstrSql.append(" ORDER BY p.is_pin desc, p.post_date desc ");
            if (limitPost > 0) {
                vstrSql.append(" LIMIT " + String.valueOf(limitPost));
            }
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
            query.setParameter("categorySlug", categorySlug);
            return (List<Post>) query.list();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public List<Post> getPostByCategoryIdRelated(Long categoryId, int limitPost, Long postId) {
        try {
            StringBuilder vstrSql = new StringBuilder();
            vstrSql.append("SELECT p.post_id as postId, p.author as author, p.post_title as postTitle, p.post_excerpt as postExcerpt,");
            vstrSql.append(" p.post_content as postContent, p.post_tag as postTag, m.category_id as categoryId, p.is_pin as isPin,");
            vstrSql.append(" p.featured_image as featuredImage, p.post_slug as postSlug, coalesce(p.post_order,0) as postOrder, p.post_date as postDate");
            vstrSql.append(" FROM stfc_posts p INNER JOIN stfc_category_post m ON p.post_id = m.post_id");
            vstrSql.append(" INNER JOIN stfc_categories c ON m.category_id = c.category_id");
            vstrSql.append(" WHERE m.category_id IN (:categoryId)");
            vstrSql.append(" AND p.post_status = 3");
            vstrSql.append(" AND m.post_status = 1");
            vstrSql.append(" AND c.status = 1");
            vstrSql.append(" AND p.post_id NOT IN (:postId)");
            vstrSql.append(" and p.effect_from_date <= sysdate()");
            vstrSql.append(" and (effect_to_date >= sysdate() or effect_to_date is null)");
            vstrSql.append(" and p.post_date <= sysdate()");
            vstrSql.append(" ORDER BY p.is_pin desc, p.post_date desc ");
            if (limitPost > 0) {
                vstrSql.append(" LIMIT " + String.valueOf(limitPost));
            }
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
            query.setParameter("categoryId", categoryId);
            query.setParameter("postId", postId);
            return (List<Post>) query.list();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public List<Document> getDocument() {
        try {
            StringBuilder vstrSql = new StringBuilder();
            vstrSql.append("select d.document_id as documentId, d.document_name as documentName, d.document_type as documentType,");
            vstrSql.append(" d.document_path as documentPath, d.category_id as categoryId, c.category_name as categoryName, d.author as author");
            vstrSql.append(" from stfc_document d");
            vstrSql.append(" inner join stfc_categories c on d.category_id = c.category_id");
            vstrSql.append(" where d.status = 1 and c.status = 1");
            vstrSql.append(" ORDER BY d.document_order, d.modified_date, d.create_date ");
            Query query = getCurrentSession()
                    .createSQLQuery(vstrSql.toString())
                    .addScalar("documentId", StandardBasicTypes.LONG)
                    .addScalar("documentName", StandardBasicTypes.STRING)
                    .addScalar("documentType", StandardBasicTypes.INTEGER)
                    .addScalar("documentPath", StandardBasicTypes.STRING)
                    .addScalar("categoryId", StandardBasicTypes.LONG)
                    .addScalar("categoryName", StandardBasicTypes.STRING)
                    .addScalar("author", StandardBasicTypes.STRING)
                    .setResultTransformer(
                            Transformers.aliasToBean(Document.class));
            return (List<Document>) query.list();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public List<Category> getCategoryDocument() {
        try {
            StringBuilder vstrSql = new StringBuilder();
            vstrSql.append("select category_id as categoryId, category_name categoryName, category_parent as categoryParent,");
            vstrSql.append(" category_slug categorySlug, category_order as categoryOrder");
            vstrSql.append(" from stfc_categories");
            vstrSql.append(" where category_id in (");
            vstrSql.append(" select distinct (category_id) from stfc_document");
            vstrSql.append(" )");
            Query query = getCurrentSession()
                    .createSQLQuery(vstrSql.toString())
                    .addScalar("categoryId", StandardBasicTypes.LONG)
                    .addScalar("categoryName", StandardBasicTypes.STRING)
                    .addScalar("categoryParent", StandardBasicTypes.LONG)
                    .addScalar("categorySlug", StandardBasicTypes.STRING)
                    .addScalar("categoryOrder", StandardBasicTypes.INTEGER)
                    .setResultTransformer(
                            Transformers.aliasToBean(Category.class));
            return (List<Category>) query.list();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public List<Class> getAllClass() {
        try {
            Query query = getCurrentSession().getNamedQuery("Class.getAllClass");
            return (List<Class>) query.list();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public void insertStudent(EnrollStudent student) {
        try {
            getCurrentSession().saveOrUpdate(student);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public UserToken getUserByUserName(String userName) {
        if (userName != null) {
            try {
                StringBuilder vstrSql = new StringBuilder();
                vstrSql.append("SELECT user_name as userName, first_name as firstName, last_name as lastName, password as password, email as email, role as role");
                vstrSql.append(" FROM stfc_users");
                vstrSql.append(" WHERE user_name = :userName ");
                Query query = getCurrentSession()
                        .createSQLQuery(vstrSql.toString())
                        .addScalar("userName", StandardBasicTypes.STRING)
                        .addScalar("firstName", StandardBasicTypes.STRING)
                        .addScalar("lastName", StandardBasicTypes.STRING)
                        .addScalar("password", StandardBasicTypes.STRING)
                        .addScalar("email", StandardBasicTypes.STRING)
                        .addScalar("role", StandardBasicTypes.INTEGER)
                        .setResultTransformer(
                                Transformers.aliasToBean(UserToken.class));
                query.setParameter("userName", userName);
                List lstUsers = query.list();
                if (lstUsers != null && !lstUsers.isEmpty()) {
                    return (UserToken) lstUsers.get(0);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return null;
    }
}
