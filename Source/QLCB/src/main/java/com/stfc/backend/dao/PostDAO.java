package com.stfc.backend.dao;

import com.stfc.backend.domain.CategoryPost;
import com.stfc.backend.domain.Post;
import com.stfc.utils.FunctionUtil;
import com.stfc.website.domain.Category;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author admin
 * @since 02/04/2018
 *
 */
@Repository
public class PostDAO {

    private static final Logger logger = Logger.getLogger(PostDAO.class);

    @Autowired
    SessionFactory sessionFactory;

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public List<Post> getPostByType(int isPrivate) {
        Query query = getCurrentSession().getNamedQuery("Post.getPostByType");
        query.setParameter("isPrivate", isPrivate);
        List<Post> listReturnData = query.list();
        return listReturnData;

    }

    public List<Post> searchPost(String postTitle, Integer postStatus, Date fromDate, Date toDate) {
        StringBuilder sql = new StringBuilder("SELECT p.post_id as postId, p.author as author, p.post_title as postTitle, p.post_excerpt as postExcerpt, p.post_content as postContent");
        sql.append(" ,p.is_pin as isPin, p.is_private as isPrivate, p.featured_image as featuredImage, p.post_slug as postSlug, p.post_status as postStatus, p.post_date as postDate");
        sql.append(" ,p.effect_from_date as fromDate, p.effect_to_date as toDate, p.create_date as createDate, p.modified_date modifiedDate, GROUP_CONCAT(c.category_name) as categoryName");
        sql.append(" FROM stfc_posts p, stfc_categories c, stfc_category_post cp");
        sql.append(" WHERE p.post_id=cp.post_id and cp.category_id=c.category_id");

        if (postTitle != null) {
            sql.append(" AND p.post_title like :postTitle escape '/'");
        }
        if (postStatus != null) {
            sql.append(" AND p.post_status = :postStatus");
        }
        if (fromDate != null) {
            sql.append(" AND p.create_date >= :fromDate");
        }
        if (toDate != null) {
            sql.append(" AND p.create_date <= :toDate");
        }
        sql.append(" GROUP BY p.post_id");
        sql.append(" ORDER BY p.create_date DESC");
        Query query = getCurrentSession().createSQLQuery(sql.toString())
                .addScalar("postId", StandardBasicTypes.LONG)
                .addScalar("author", StandardBasicTypes.STRING)
                .addScalar("postTitle", StandardBasicTypes.STRING)
                .addScalar("postExcerpt", StandardBasicTypes.STRING)
                .addScalar("postContent", StandardBasicTypes.STRING)
                .addScalar("isPin", StandardBasicTypes.INTEGER)
                .addScalar("isPrivate", StandardBasicTypes.INTEGER)
                .addScalar("featuredImage", StandardBasicTypes.STRING)
                .addScalar("postSlug", StandardBasicTypes.STRING)
                .addScalar("postStatus", StandardBasicTypes.INTEGER)
                .addScalar("postDate", StandardBasicTypes.DATE)
                .addScalar("fromDate", StandardBasicTypes.DATE)
                .addScalar("toDate", StandardBasicTypes.DATE)
                .addScalar("createDate", StandardBasicTypes.DATE)
                .addScalar("modifiedDate", StandardBasicTypes.DATE)
                .addScalar("categoryName", StandardBasicTypes.STRING)
                .setResultTransformer(Transformers.aliasToBean(Post.class));
        if (postTitle != null) {
            query.setParameter("postTitle", "%" + FunctionUtil.escapeCharacter(postTitle) + "%");
        }
        if (postStatus != null) {
            query.setParameter("postStatus", postStatus);
        }
        if (fromDate != null) {
            query.setParameter("fromDate", fromDate);
        }
        if (toDate != null) {
            query.setParameter("toDate", toDate);
        }
        List<Post> listReturnData = query.list();
        return listReturnData;

    }

    public void update(Post post) {
        getCurrentSession().saveOrUpdate(post);
    }

    public Serializable save(Post post) {
        return getCurrentSession().save(post);
    }

    public void saveCategoryPost(CategoryPost categoryPost) {
        getCurrentSession().save(categoryPost);
    }

    public void deleteCategoryByPostId(Long postId) {
        String sql = "DELETE CategoryPost WHERE postId = :postId";
        Query query = getCurrentSession().createQuery(sql);
        query.setParameter("postId", postId);
        query.executeUpdate();
    }

    public List<Category> getCategoryIdByPostId(Long postId) {
        String sql = "SELECT c FROM Category c, CategoryPost cp WHERE cp.categoryId=c.categoryId AND cp.postId = :postId";
        Query query = getCurrentSession().createQuery(sql);
        query.setParameter("postId", postId);
        List<Category> listCategory = query.list();
        return listCategory;
    }

}
