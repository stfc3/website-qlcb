package com.stfc.backend.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stfc.backend.domain.Banner;
import com.stfc.utils.StringUtils;
import com.stfc.utils.FunctionUtil;

/**
 *
 * @author admin
 * @since 02/04/2018
 *
 */
@Repository
public class BannerDAO {
    
    private static final Logger logger = Logger.getLogger(BannerDAO.class);
    
    @Autowired
    SessionFactory sessionFactory;
    
    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     *
     * @return
     */
    public List<Banner> getAllBanner() {
        try {
            Query query = getCurrentSession().getNamedQuery("Banner.GetAllBanner");
            List<Banner> listReturnData = query.list();
            return listReturnData;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        
        return null;
    }

    /**
     *
     * @param banner
     * @return
     */
//    public List<Banner> search(Banner banner) {
//
//        try {
//            StringBuilder sql = new StringBuilder("select bannerId, ");
//            sql.append("bannerName, bannerImage, ");
//            sql.append("bannerUrl, bannerType, ");
//            sql.append(" bannerOrder, bannerStatus, ");
//            sql.append(" effectFromDate, effectToDate, ");
//            sql.append(" createDate, modifiedDate");
//            sql.append(" from Banner where 1=1 ");
//            if (StringUtils.valiString(banner.getBannerName())) {
//                sql.append(" and bannerName like :name escape '/'");
//            }
//            if (banner.getBannerType() != null && banner.getBannerType() != -1) {
//                sql.append(" and bannerType = :type");
//            }
//            if (banner.getBannerStatus() != null && banner.getBannerStatus() != -1) {
//                sql.append(" and bannerStatus = :status");
//            }
//            if (banner.getEffectFromDate() != null) {
//                sql.append(" and effectFromDate >= :fromDate");
//            }
//            if (banner.getEffectToDate() != null) {
//                sql.append(" and effectToDate <= :toDate");
//            }
//            sql.append(" order by createDate, modifiedDate desc ");
//            Query query = getCurrentSession().createQuery(sql.toString())
////                    .addScalar("bannerId", StandardBasicTypes.LONG).addScalar("bannerName", StandardBasicTypes.STRING)
//                    //                    .addScalar("bannerImage", StandardBasicTypes.STRING)
//                    //                    .addScalar("bannerUrl", StandardBasicTypes.STRING)
//                    //                    .addScalar("bannerOrder", StandardBasicTypes.INTEGER)
//                    //                    .addScalar("bannerType", StandardBasicTypes.INTEGER)
//                    //                    .addScalar("effectFromDate", StandardBasicTypes.DATE)
//                    //                    .addScalar("effectToDate", StandardBasicTypes.DATE).addScalar("createDate", StandardBasicTypes.DATE)
//                    //                    .addScalar("modifiedDate", StandardBasicTypes.DATE)
//                    .setResultTransformer(Transformers.aliasToBean(Banner.class));
//            if (StringUtils.valiString(banner.getBannerName())) {
//                query.setParameter("name", "%" + FunctionUtil.escapeCharacter(banner.getBannerName()) + "%");
//            }
//            if (banner.getBannerType() != null && banner.getBannerType() != -1) {
//                query.setParameter("type", banner.getBannerType());
//            }
//            if (banner.getBannerStatus() != null && banner.getBannerStatus() != -1) {
//                query.setParameter("status", banner.getBannerStatus());
//            }
//            if (banner.getEffectFromDate() != null) {
//                query.setParameter("fromDate", banner.getEffectFromDate());
//            }
//            if (banner.getEffectToDate() != null) {
//                query.setParameter("toDate", banner.getEffectToDate());
//            }
//
//            List<Banner> listDataReturn = query.list();
//            return listDataReturn;
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return null;
//    }
    public List<Banner> search(Banner banner) {
        
        try {
            StringBuilder sql = new StringBuilder("select banner_id as bannerId, ");
            sql.append("banner_name as bannerName, banner_image as bannerImage, ");
            sql.append("banner_url  as bannerUrl, banner_type as bannerType, ");
            sql.append("banner_order  as bannerOrder, banner_status as bannerStatus, ");
            sql.append("effect_from_date  as effectFromDate, effect_to_date as effectToDate, ");
            sql.append("create_date  as createDate, modified_date as modifiedDate");
            sql.append(" from stfc_banner where 1=1 ");
            if (StringUtils.valiString(banner.getBannerName())) {
                sql.append(" and banner_name like :name escape '/'");
            }
            if (banner.getBannerType() != null && banner.getBannerType() != -1) {
                sql.append(" and banner_type = :type");
            }
            if (banner.getBannerStatus() != null && banner.getBannerStatus() != -1) {
                sql.append(" and banner_status = :status");
            }
            if (banner.getEffectFromDate() != null) {
                sql.append(" and DATE(effect_from_date) >= STR_TO_DATE(:fromDate , '%Y-%m-%d %H:%i:%s') ");
//                sql.append(" and STR_TO_DATE(effect_from_date, '%Y-%m-%d %H:%i:%s') <= STR_TO_DATE(:fromDate , '%Y-%m-%d %H:%i:%s')");
            }
            if (banner.getEffectToDate() != null) {
                sql.append(" and DATE(effect_to_date) <= STR_TO_DATE(:toDate, '%Y-%m-%d %H:%i:%s') ");
//                sql.append(" and STR_TO_DATE(effect_to_date, '%Y-%m-%d %H:%i:%s') >= STR_TO_DATE(:toDate, '%Y-%m-%d %H:%i:%s')");
            }
            sql.append(" order by modified_date desc ");
            logger.info("SQL:" +sql.toString());
            logger.info("Param FromDate: " + banner.getEffectFromDate());
            logger.info("Param ToDate: " + banner.getEffectToDate());
            Query query = getCurrentSession().createSQLQuery(sql.toString())
                    .addScalar("bannerId", StandardBasicTypes.LONG).addScalar("bannerName", StandardBasicTypes.STRING)
                    .addScalar("bannerImage", StandardBasicTypes.STRING)
                    .addScalar("bannerUrl", StandardBasicTypes.STRING)
                    .addScalar("bannerOrder", StandardBasicTypes.INTEGER)
                    .addScalar("bannerType", StandardBasicTypes.INTEGER)
                    .addScalar("bannerStatus", StandardBasicTypes.INTEGER)
                    .addScalar("effectFromDate", StandardBasicTypes.DATE)
                    .addScalar("effectToDate", StandardBasicTypes.DATE).addScalar("createDate", StandardBasicTypes.DATE)
                    .addScalar("modifiedDate", StandardBasicTypes.DATE)
                    .setResultTransformer(Transformers.aliasToBean(Banner.class));
            if (StringUtils.valiString(banner.getBannerName())) {
                query.setParameter("name", "%" + FunctionUtil.escapeCharacter(banner.getBannerName()) + "%");
            }
            if (banner.getBannerType() != null && banner.getBannerType() != -1) {
                query.setParameter("type", banner.getBannerType());
            }
            if (banner.getBannerStatus() != null && banner.getBannerStatus() != -1) {
                query.setParameter("status", banner.getBannerStatus());
            }
            if (banner.getEffectFromDate() != null) {
                query.setParameter("fromDate", banner.getEffectFromDate());
            }
            if (banner.getEffectToDate() != null) {
                query.setParameter("toDate", banner.getEffectToDate());
            }
            
            List<Banner> listDataReturn = query.list();
            return listDataReturn;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     *
     * @param banner
     */
    public void save(Banner banner) {
        getCurrentSession().saveOrUpdate(banner);
    }
    
    public void update(Banner banner) {
        getCurrentSession().update(banner);
    }
    
}
