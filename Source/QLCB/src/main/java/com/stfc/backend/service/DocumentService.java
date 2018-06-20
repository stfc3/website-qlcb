/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.service;

import com.stfc.backend.domain.Document;
import java.util.List;

/**
 *
 * @author viettx
 */
public interface DocumentService {

	public void save(Document document);

	public List<Document> search(Document document);

	public List<Document> getAllBanner();

	public void update(List<Document> documents);

	public List<Document> getAllDocumentByType();
        
        public void delete(Document document);

}
