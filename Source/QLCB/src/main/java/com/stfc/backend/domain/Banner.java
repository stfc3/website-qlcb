/**
 * 
 */
package com.stfc.backend.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.stfc.backend.utils.FunctionUtil;

/**
 * @author admin
 *
 */

@Entity
@Table(name = "stfc_banner")
@NamedQueries({
		@NamedQuery(name = "Banner.GetAllBanner", query = "FROM Banner u order by createDate, modifiedDate desc") })
public class Banner {
	/*
	 * banner_id bigint(20) AI PK banner_name varchar(2000) banner_image
	 * varchar(2000) banner_url varchar(2000) banner_type int(11) banner_order
	 * int(11) banner_status int(11) effect_from_date datetime effect_to_date
	 * datetime create_date timestamp modified_date timestamp
	 */

	private Long bannerId;
	private String bannerName;
	private String bannerImage;
	private String bannerUrl;
	private Integer bannerType;
	private String bannerTypeName;
	private Integer bannerOrder;
	private Integer bannerStatus;
	private Date effectFromDate;
	private Date effectToDate;
	private Date createDate;
	private Date modifiedDate;

	/**
	 * @return the bannerId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "banner_id", unique = false, nullable = true, insertable = true, updatable = true)
	public Long getBannerId() {
		return bannerId;
	}

	/**
	 * @param bannerId
	 *            the bannerId to set
	 */
	public void setBannerId(Long bannerId) {
		this.bannerId = bannerId;
	}

	/**
	 * @return the bannerName
	 */
	@Column(name = "banner_name", unique = false, nullable = false, insertable = true, updatable = true)
	public String getBannerName() {
		return bannerName;
	}

	/**
	 * @param bannerName
	 *            the bannerName to set
	 */
	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	/**
	 * @return the bannerImage
	 */
	@Column(name = "banner_image", unique = true, nullable = true, insertable = true, updatable = true)
	public String getBannerImage() {
		return bannerImage;
	}

	/**
	 * @param bannerImage
	 *            the bannerImage to set
	 */
	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	/**
	 * @return the bannerUrl
	 */
	@Column(name = "banner_url", unique = true, nullable = true, insertable = true, updatable = true)
	public String getBannerUrl() {
		return bannerUrl;
	}

	/**
	 * @param bannerUrl
	 *            the bannerUrl to set
	 */
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	/**
	 * @return the bannerType
	 */
	@Column(name = "banner_type", unique = true, nullable = true, insertable = true, updatable = true)
	public Integer getBannerType() {
		return bannerType;
	}

	/**
	 * @param bannerType
	 *            the bannerType to set
	 */
	public void setBannerType(Integer bannerType) {
		this.bannerType = bannerType;
	}

	/**
	 * @return the bannerOrder
	 */
	@Column(name = "banner_order", unique = true, nullable = true, insertable = true, updatable = true)
	public Integer getBannerOrder() {
		return bannerOrder;
	}

	/**
	 * @param bannerOrder
	 *            the bannerOrder to set
	 */
	public void setBannerOrder(Integer bannerOrder) {
		this.bannerOrder = bannerOrder;
	}

	/**
	 * @return the bannerStatus
	 */
	@Column(name = "banner_status", unique = true, nullable = true, insertable = true, updatable = true)
	public Integer getBannerStatus() {
		return bannerStatus;
	}

	/**
	 * @param bannerStatus
	 *            the bannerStatus to set
	 */
	public void setBannerStatus(Integer bannerStatus) {
		this.bannerStatus = bannerStatus;
	}

	/**
	 * @return the effectFromDate
	 */
	@Column(name = "effect_from_date", unique = true, nullable = true, insertable = true, updatable = true)
	public Date getEffectFromDate() {
		return effectFromDate;
	}

	/**
	 * @param effectFromDate
	 *            the effectFromDate to set
	 */
	public void setEffectFromDate(Date effectFromDate) {
		this.effectFromDate = effectFromDate;
	}

	/**
	 * @return the effectToDate
	 */
	@Column(name = "effect_to_date", unique = true, nullable = true, insertable = true, updatable = true)
	public Date getEffectToDate() {
		return effectToDate;
	}

	/**
	 * @param effectToDate
	 *            the effectToDate to set
	 */
	public void setEffectToDate(Date effectToDate) {
		this.effectToDate = effectToDate;
	}

	/**
	 * @return the createDate
	 */
	@Column(name = "create_date", unique = true, nullable = true, insertable = true, updatable = true)
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the modifiedDate
	 */
	@Column(name = "modified_date", unique = true, nullable = true, insertable = true, updatable = true)
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate
	 *            the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	
	/**
	 * @return the bannerTypeName
	 */
	@Transient
	public String getBannerTypeName() {
		return FunctionUtil.getTypeName(bannerType);
	}

	/**
	 * @param bannerTypeName the bannerTypeName to set
	 */
	public void setBannerTypeName(String bannerTypeName) {
		this.bannerTypeName = bannerTypeName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Banner [bannerId=" + bannerId + ", bannerName=" + bannerName + ", bannerImage=" + bannerImage
				+ ", bannerUrl=" + bannerUrl + ", bannerType=" + bannerType + ", bannerOrder=" + bannerOrder
				+ ", bannerStatus=" + bannerStatus + ", effectFromDate=" + effectFromDate + ", effectToDate="
				+ effectToDate + ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bannerId == null) ? 0 : bannerId.hashCode());
		result = prime * result + ((bannerImage == null) ? 0 : bannerImage.hashCode());
		result = prime * result + ((bannerName == null) ? 0 : bannerName.hashCode());
		result = prime * result + ((bannerOrder == null) ? 0 : bannerOrder.hashCode());
		result = prime * result + ((bannerStatus == null) ? 0 : bannerStatus.hashCode());
		result = prime * result + ((bannerType == null) ? 0 : bannerType.hashCode());
		result = prime * result + ((bannerUrl == null) ? 0 : bannerUrl.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((effectFromDate == null) ? 0 : effectFromDate.hashCode());
		result = prime * result + ((effectToDate == null) ? 0 : effectToDate.hashCode());
		result = prime * result + ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Banner other = (Banner) obj;
		if (bannerId == null) {
			if (other.bannerId != null)
				return false;
		} else if (!bannerId.equals(other.bannerId))
			return false;
		if (bannerImage == null) {
			if (other.bannerImage != null)
				return false;
		} else if (!bannerImage.equals(other.bannerImage))
			return false;
		if (bannerName == null) {
			if (other.bannerName != null)
				return false;
		} else if (!bannerName.equals(other.bannerName))
			return false;
		if (bannerOrder == null) {
			if (other.bannerOrder != null)
				return false;
		} else if (!bannerOrder.equals(other.bannerOrder))
			return false;
		if (bannerStatus == null) {
			if (other.bannerStatus != null)
				return false;
		} else if (!bannerStatus.equals(other.bannerStatus))
			return false;
		if (bannerType == null) {
			if (other.bannerType != null)
				return false;
		} else if (!bannerType.equals(other.bannerType))
			return false;
		if (bannerUrl == null) {
			if (other.bannerUrl != null)
				return false;
		} else if (!bannerUrl.equals(other.bannerUrl))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (effectFromDate == null) {
			if (other.effectFromDate != null)
				return false;
		} else if (!effectFromDate.equals(other.effectFromDate))
			return false;
		if (effectToDate == null) {
			if (other.effectToDate != null)
				return false;
		} else if (!effectToDate.equals(other.effectToDate))
			return false;
		if (modifiedDate == null) {
			if (other.modifiedDate != null)
				return false;
		} else if (!modifiedDate.equals(other.modifiedDate))
			return false;
		return true;
	}

}
