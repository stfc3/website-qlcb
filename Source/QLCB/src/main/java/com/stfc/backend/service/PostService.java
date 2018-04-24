package com.stfc.backend.service;

import com.stfc.backend.domain.CategoryPost;
import com.stfc.backend.domain.Post;
import java.math.BigInteger;
import java.util.List;

public interface PostService {

//    List<Menu> getMenuByType(Integer type);
    List<Post> getPostByType(int isPrivate);

    void saveOrUpdate(Post post);

    void saveCategoryPost(CategoryPost categoryPost);

    BigInteger getId();
}
