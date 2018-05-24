package com.stfc.backend.service;

import com.stfc.backend.domain.CategoryPost;
import com.stfc.backend.domain.Post;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface PostService {

    List<Post> getPostByType(int isPrivate);

    void update(Post post);

    Serializable save(Post post);

    void saveCategoryPost(CategoryPost categoryPost);

    public List<Post> searchPost(String postTitle, Integer postStatus, Date fromDate, Date toDate);
}
