package com.stfc.backend.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stfc.backend.dao.BannerDAO;
import com.stfc.backend.domain.Banner;
import com.stfc.backend.service.BannerService;

@Service
public class BannerServiceImpl implements BannerService {
	
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    BannerDAO bannerDAO;

	@Override
	public List<Banner> getAllBanner() {
		// TODO Auto-generated method stub
		return bannerDAO.getAllBanner();
	}

	@Override
	public List<Banner> search(Banner banner) {
		// TODO Auto-generated method stub
		return bannerDAO.search(banner);
	}

	@Override
	public void save(Banner banner) {
		// TODO Auto-generated method stub
		bannerDAO.save(banner);
	}

}
