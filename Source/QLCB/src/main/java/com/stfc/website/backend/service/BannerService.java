package com.stfc.website.backend.service;

import java.util.List;

import com.stfc.website.backend.domain.Banner;

public interface BannerService {

	List<Banner> getAllBanner();
	
	List<Banner> search(Banner banner);
	
	void save(Banner banner);
}
