package com.stfc.backend.controller;

import com.stfc.backend.service.CategoryService;
import com.stfc.utils.SpringConstant;
import com.stfc.utils.StringUtils;
import com.stfc.website.domain.Category;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;

public class CategoryComposer extends SelectorComposer<Component> {

    @WireVariable
    CategoryService categoryService;
    @Wire
    Combobox  categoryParent;
    @Wire
    Textbox categoryName, categorySlug;
    @Wire
    Grid listCategory;
    private String prefixSlug="/category/";


    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        categoryService = (CategoryService) SpringUtil.getBean(SpringConstant.CATEGORY_SERVICE);
        loadCategory();

    }

    public void loadCategory() {
        List<Category> lstCategoryParent = categoryService.getCategory();
        List<Category> lstCategoryGrid = new ArrayList<>(lstCategoryParent);

        Category categoryOption = new Category();
        categoryOption.setCategoryName(Labels.getLabel("option"));
        lstCategoryParent.add(0, categoryOption);

        ListModelList modelCategoryGrid = new ListModelList(lstCategoryGrid);
        listCategory.setModel(modelCategoryGrid);
        ListModelList modelCategoryParent = new ListModelList(lstCategoryParent);
        categoryParent.setModel(modelCategoryParent);
    }
    @Listen("onChange = #categoryName")
    public void fillSlug() {
        categorySlug.setValue(StringUtils.convertSlug(categoryName.getValue()));
    }

    @Listen("onClick = #btnSave")
    public void saveCategory() {

        Category category = new Category();
        category.setCategoryName(categoryName.getValue());
        if (!Labels.getLabel("option").equals(categoryParent.getValue()) ) {
            category.setCategoryParent(categoryParent.getSelectedItem().getValue());
        }
        category.setCategorySlug(prefixSlug+categorySlug.getValue());
        category.setCategoryStatus(1);
        category.setCreateDate(new Date());
        category.setModifiedDate(new Date());
        categoryService.saveOrUpdate(category);
        loadCategory();

    }
}
