package com.stfc.backend.controller;

import com.stfc.backend.domain.CategoryPost;
import com.stfc.backend.domain.Post;
import com.stfc.backend.service.PostService;
import com.stfc.utils.Constants;
import com.stfc.utils.SpringConstant;
import com.stfc.utils.StringUtils;
import com.stfc.website.Memory;
import com.stfc.website.domain.Category;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.zkforge.ckez.CKeditor;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

public class PostComposer extends SelectorComposer<Component> {

    @WireVariable
    PostService postService;
    @Wire
    Listbox category;
    @Wire
    Textbox postTitle, postSlug;
    @Wire
    CKeditor postContent;
    @Wire
    Checkbox isPrivate, isPin;
    @Wire
    Datebox fromDate, toDate;
    @Wire
    Image image;
    private Session session;
    private boolean isPublish;
    private ListModelList<Category> modelCategory;

    public ListModelList<Category> getModelCategory() {
        return modelCategory;
    }

    public void setModelCategory(ListModelList<Category> modelCategory) {
        this.modelCategory = modelCategory;
    }
    
    

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        session = Sessions.getCurrent();
//        if (session.getAttribute(Constants.USER_TOKEN) == null) {
//            Executions.sendRedirect(Constants.PAGE_LOGIN);
//        } else {
//            UserToken userToken = (UserToken) session.getAttribute(Constants.USER_TOKEN);
//            if (userToken != null) {
//            }
//        }
        postService = (PostService) SpringUtil.getBean(SpringConstant.POST_SERVICE);
        loadCategory();

    }

    public void loadCategory() {
        List<Category> lstCategory = new ArrayList<>(Memory.listCategoryCache.values());

        modelCategory = new ListModelList(lstCategory);
        modelCategory.setMultiple(true);
        category.setModel(modelCategory);
    }

    @Listen("onChange = #postTitle")
    public void fillSlug() {
        postSlug.setValue(StringUtils.convertSlug(postTitle.getValue()));
    }

    @Listen("onClick = #btnSave")
    public void save() {
        isPublish = false;
        Long postId = savePost(1);
        saveCategoryPost(postId);
    }

    @Listen("onClick = #btnPublish")
    public void publish() {
        isPublish = true;
        Long postId = savePost(3);
        saveCategoryPost(postId);
    }

    private Long savePost(int status) {
        int vintIsPin = 0;
        int vintPrivate = 0;
        if (isPin.isChecked()) {
            vintIsPin = 1;
        }
        if (isPrivate.isChecked()) {
            vintPrivate = 1;
        }
        Post post = new Post();
        post.setAuthor("guest");
        post.setPostTitle(postTitle.getValue());
        post.setPostExcerpt("");
        post.setPostContent(postContent.getValue());
        post.setIsPin(vintIsPin);
        post.setIsPrivate(vintPrivate);
        post.setFeaturedImage(image.getSrc());
        post.setPostSlug(Constants.prefixSlugPost + postSlug.getValue());
        post.setPostStatus(status);
        if (isPublish) {
            post.setPostDate(new Date());
        }
        post.setFromDate(fromDate.getValue());
        post.setToDate(toDate.getValue());
        post.setCreateDate(new Date());
        post.setModifiedDate(new Date());
        postService.saveOrUpdate(post);
        clearInput();
        return postService.getId().longValue();
    }

    private void saveCategoryPost(Long postId) {
        List<Category> lstCate = new ArrayList<>(modelCategory.getSelection());
        if (!lstCate.isEmpty()) {
            for (Category vcategory : lstCate) {
                CategoryPost categoryPost = new CategoryPost();
                categoryPost.setCategoryId(vcategory.getCategoryId());
                categoryPost.setPostId(postId);
                categoryPost.setStatus(1);
                categoryPost.setCreateDate(new Date());
                postService.saveCategoryPost(categoryPost);
            }
        }
    }

    private void clearInput() {
        postTitle.setValue("");
        postSlug.setValue("");
        postContent.setValue("");
        isPrivate.setChecked(false);
        isPin.setChecked(false);
        fromDate.setValue(new Date());
        toDate.setValue(null);
        loadCategory();
        image.setSrc("");
    }

}
