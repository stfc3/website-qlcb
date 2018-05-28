package com.stfc.backend.service;

import com.stfc.backend.domain.CategoryPost;
import com.stfc.backend.domain.Post;
import com.stfc.website.domain.Category;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface PostService {

    List<Post> getPostByType(int isPrivate);

    void update(Post post);

    Serializable save(Post post);

    void saveCategoryPost(CategoryPost categoryPost);
    
    void deleteCategoryByPostId(Long postId);
    
    List<Category> getCategoryIdByPostId(Long postId);

    List<Post> searchPost(String postTitle, Integer postStatus, Date fromDate, Date toDate);
}
