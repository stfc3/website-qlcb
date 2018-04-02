package com.stfc.website.backend.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stfc.website.backend.domain.Banner;
import com.stfc.website.backend.utils.StringUtils;

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
				sql.append(" and banner_name like %:name%");
			}
			if (banner.getBannerType() != null) {
				sql.append(" and banner_type = :type");
			}
			if (banner.getBannerStatus() != null) {
				sql.append(" and banner_status = :status");
			}
			if (banner.getEffectFromDate() != null) {
				sql.append(" and effect_from_date >= :fromDate");
			}
			if (banner.getEffectToDate() != null) {
				sql.append(" and effect_to_date <= :toDate");
			}
			sql.append(" order by modified_date desc ");
			Query query = getCurrentSession().createSQLQuery(sql.toString())
					.addScalar("bannerId", StandardBasicTypes.LONG).addScalar("bannerName", StandardBasicTypes.STRING)
					.addScalar("bannerImage", StandardBasicTypes.STRING)
					.addScalar("bannerUrl", StandardBasicTypes.STRING)
					.addScalar("bannerOrder", StandardBasicTypes.INTEGER)
					.addScalar("bannerType", StandardBasicTypes.INTEGER)
					.addScalar("effectFromDate", StandardBasicTypes.DATE)
					.addScalar("effectToDate", StandardBasicTypes.DATE).addScalar("createDate", StandardBasicTypes.DATE)
					.addScalar("modifiedDate", StandardBasicTypes.DATE)
					.setResultTransformer(Transformers.aliasToBean(Banner.class));
			if (StringUtils.valiString(banner.getBannerName())) {
				query.setParameter("name", banner.getBannerName());
			}
			if (banner.getBannerType() != null) {
				query.setParameter("type", banner.getBannerType());
			}
			if (banner.getBannerStatus() != null) {
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

}
