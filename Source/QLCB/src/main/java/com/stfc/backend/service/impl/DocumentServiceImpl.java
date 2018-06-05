/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.service.impl;

import com.stfc.backend.dao.DocumentDAO;
import com.stfc.backend.domain.Document;
import com.stfc.backend.service.DocumentService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author viettx
 */
@Service
public class DocumentServiceImpl implements DocumentService {

	private static final Logger logger = Logger.getLogger(DocumentServiceImpl.class);
	@Autowired
	DocumentDAO documentDAO;

	@Transactional
	@Override
	public void save(Document document) {
		documentDAO.save(document);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Document> search(Document document) {
		try {
			return documentDAO.search(document);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;

	}

	@Transactional(readOnly = true)
	@Override
	public List<Document> getAllBanner() {
		try {
			return documentDAO.getAllBanner();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	@Transactional
	@Override
	public void update(List<Document> documents) {
		// TODO Auto-generated method stub
		try {
			documentDAO.update(documents);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
	@Transactional(readOnly = true)
	@Override
	public List<Document> getAllDocumentByType() {
		// TODO Auto-generated method stub
		try {
			return documentDAO.getAllDocumentByType();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
