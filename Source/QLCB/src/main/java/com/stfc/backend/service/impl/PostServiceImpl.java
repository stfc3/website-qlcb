package com.stfc.backend.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stfc.backend.dao.PostDAO;
import com.stfc.backend.domain.CategoryPost;
import com.stfc.backend.domain.Post;
import com.stfc.backend.service.PostService;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostServiceImpl implements PostService {

    private static final Logger logger = Logger.getLogger(PostServiceImpl.class);
    @Autowired
    PostDAO postDAO;

    @Transactional
    @Override
    public void update(Post post) {
        postDAO.update(post);
    }
    @Transactional
    @Override
    public Serializable save(Post post) {
        return postDAO.save(post);
    }

    @Transactional
    @Override
    public void saveCategoryPost(CategoryPost categoryPost) {
        postDAO.saveCategoryPost(categoryPost);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Post> getPostByType(int isPrivate) {
        return postDAO.getPostByType(isPrivate);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Post> searchPost(String postTitle, Integer postStatus, Date fromDate, Date toDate) {
        return postDAO.searchPost(postTitle, postStatus, fromDate, toDate);
    }

}
