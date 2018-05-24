package com.stfc.backend.controller;

import com.stfc.backend.domain.CategoryPost;
import com.stfc.backend.domain.Post;
import com.stfc.backend.service.PostService;
import com.stfc.utils.Constants;
import com.stfc.utils.SpringConstant;
import com.stfc.utils.StringUtils;
import com.stfc.website.Memory;
import com.stfc.website.bean.UserToken;
import com.stfc.website.domain.Category;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.zkforge.ckez.CKeditor;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class AddEditPostComposer extends SelectorComposer<Component> {

    @WireVariable
    PostService postService;
    @Wire
    Listbox category;
    @Wire
    Textbox postTitle, postSlug, postExcerpt;
    @Wire
    CKeditor postContent;
    @Wire
    Checkbox isPrivate, isPin;
    @Wire
    Datebox fromDate, toDate;
    @Wire
    Image imageFeature;
    @Wire
    Div main;
    private Session session;
    private boolean isPublish;
    private ListModelList<Category> modelCategory;
    private Long ERROR = -1l;
    UserToken userToken;

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
        if (session.getAttribute(Constants.USER_TOKEN) != null) {
            userToken = (UserToken) session.getAttribute(Constants.USER_TOKEN);
        } 
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
        if (!ERROR.equals(postId)) {
            saveCategoryPost(postId);
            clearInput();
        }
        
    }

    @Listen("onClick = #btnPublish")
    public void publish() {
        isPublish = true;
        Long postId = savePost(3);
        if (!ERROR.equals(postId)) {
            saveCategoryPost(postId);
            clearInput();
        }
    }

    private Long savePost(int status) {
        if (validateInput()) {
            int vintIsPin = 0;
            int vintPrivate = 0;
            if (isPin.isChecked()) {
                vintIsPin = 1;
            }
            if (isPrivate.isChecked()) {
                vintPrivate = 1;
            }
            Post post = new Post();
            post.setAuthor(userToken.getUserName());
            post.setPostTitle(postTitle.getValue());
            post.setPostExcerpt(postExcerpt.getValue());
            post.setPostContent(postContent.getValue());
            post.setIsPin(vintIsPin);
            post.setIsPrivate(vintPrivate);
            post.setFeaturedImage(imageFeature.getSrc());
            post.setPostSlug(Constants.PREFIX_SLUG_POST + postSlug.getValue());
            post.setPostStatus(status);
            if (isPublish) {
                post.setPostDate(new Date());
            }
            post.setFromDate(fromDate.getValue());
            post.setToDate(toDate.getValue());
            post.setCreateDate(new Date());
            post.setModifiedDate(new Date());
            return (Long) postService.save(post);
        }
        return ERROR;
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
        postExcerpt.setValue("");
        postContent.setValue("");
        isPrivate.setChecked(false);
        isPin.setChecked(false);
        fromDate.setValue(new Date());
        toDate.setValue(null);
        loadCategory();
        imageFeature.setSrc("");
    }

    @Listen("onClick = #addImage")
    public void addImage() {
        final Window windownUpload = (Window) Executions.createComponents("/backend/manager/browserImage.zul", main, null);
        windownUpload.doModal();
        windownUpload.setBorder(true);
        windownUpload.setBorder("normal");
        windownUpload.setClosable(true);
    }

    private boolean validateInput() {
        if (!StringUtils.valiString(postTitle.getValue())) {
            Clients.showNotification(Labels.getLabel("post.title.empty"), Clients.NOTIFICATION_TYPE_ERROR, postTitle, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
            return false;
        }
        if (!StringUtils.valiString(postSlug.getValue())) {
            Clients.showNotification(Labels.getLabel("post.slug.empty"), Clients.NOTIFICATION_TYPE_ERROR, postSlug, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
            return false;
        }
        if (!StringUtils.valiString(postContent.getValue())) {
            Clients.showNotification(Labels.getLabel("post.content.empty"), Clients.NOTIFICATION_TYPE_ERROR, postContent, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
            return false;
        }
        if (fromDate.getValue() == null) {
            Clients.showNotification(Labels.getLabel("post.fromdate.empty"), Clients.NOTIFICATION_TYPE_ERROR, fromDate, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
            return false;
        }
        if (toDate.getValue() != null && toDate.getValue().before(fromDate.getValue())) {
            Clients.showNotification(Labels.getLabel("post.fromdate.todate"), Clients.NOTIFICATION_TYPE_ERROR, fromDate, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
            return false;
        }
        if (modelCategory.getSelection().isEmpty()) {
            Clients.showNotification(Labels.getLabel("post.category.empty"), Clients.NOTIFICATION_TYPE_ERROR, category, Constants.MESSAGE_POSTION_END_CENTER, Constants.MESSAGE_TIME_CLOSE, Boolean.TRUE);
            return false;
        }
        return true;
    }

}
