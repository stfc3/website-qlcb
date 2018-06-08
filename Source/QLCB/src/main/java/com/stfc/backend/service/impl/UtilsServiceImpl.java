package com.stfc.backend.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stfc.backend.dao.UtilsDAO;
import com.stfc.backend.domain.Enroll;
import com.stfc.backend.domain.FeedBack;
import com.stfc.backend.service.UtilsService;
import com.stfc.website.domain.Class;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UtilsServiceImpl implements UtilsService {

    private static final Logger logger = Logger.getLogger(UtilsServiceImpl.class);
    @Autowired
    UtilsDAO utilsDAO;

    @Transactional(readOnly = true)
    @Override
    public List<Enroll> searchEnroll(Enroll enroll) {
        try {
            List<Enroll> list = utilsDAO.search(enroll);
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<FeedBack> onSearchFeedBack(FeedBack feedBack) {
        try {
            List<FeedBack> list = utilsDAO.onSearchFeedBack(feedBack);
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Transactional(readOnly = true)
	@Override
	public List<Class> search(Class value) {
		try {
            List<Class> list = utilsDAO.search(value);
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
	}
    @Transactional
	@Override
	public void save(Class value) {
		// TODO Auto-generated method stub
    	utilsDAO.save(value);
	}

}
