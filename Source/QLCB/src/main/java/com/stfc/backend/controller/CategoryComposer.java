package com.stfc.backend.controller;

import com.stfc.backend.service.CategoryService;
import com.stfc.utils.Constants;
import com.stfc.utils.SpringConstant;
import com.stfc.utils.StringUtils;
import com.stfc.website.domain.Category;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;

public class CategoryComposer extends GenericForwardComposer<Component> {

    @WireVariable
    CategoryService categoryService;
    @Wire
    Combobox categoryParent;
    @Wire
    Textbox categoryName, categorySlug;
    @Wire
    Grid listCategory;
    private final String prefixSlug = "/category/";
    private Category categorySelected;
    private boolean isAdd = true;

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
        categoryParent.setValue(Labels.getLabel("option"));
    }

    public void onChange$categoryName() {
        categorySlug.setValue(StringUtils.convertSlug(categoryName.getValue()));
    }

    public void onClick$btnSave() {

        if (categorySelected == null) {
            categorySelected = new Category();
        }
        categorySelected.setCategoryName(categoryName.getValue());
        if (!Labels.getLabel("option").equals(categoryParent.getValue())) {
            categorySelected.setCategoryParent(categoryParent.getSelectedItem().getValue());
        }
        categorySelected.setCategorySlug(prefixSlug + categorySlug.getValue());
        if (isAdd) {
            categorySelected.setCategoryStatus(Constants.STATUS_ACTIVE);
            categorySelected.setCreateDate(new Date());
        }
        categorySelected.setModifiedDate(new Date());
        categoryService.saveOrUpdate(categorySelected);
        clearInput();

    }

    public void onClick$btnReset() {
        categorySelected = null;
        clearInput();
    }

    public void onEdit(ForwardEvent event) {
        isAdd = false;
        Row rowSelected = (Row) event.getOrigin().getTarget().getParent().getParent();
        categorySelected = rowSelected.getValue();
        categoryName.setValue(categorySelected.getCategoryName());
        categorySlug.setValue(categorySelected.getCategorySlug().replace(prefixSlug, ""));
        if (categorySelected.getCategoryParent() != null) {
            categoryParent.setValue(categorySelected.getCategoryParentName());
        } else {
            categoryParent.setValue(Labels.getLabel("option"));
        }

    }

    private void clearInput() {
        isAdd = true;
        categoryName.setValue("");
        categorySlug.setValue("");
        loadCategory();
    }

    public void onDelete(ForwardEvent event) {
        Messagebox.show(Labels.getLabel("message.confirm.delete.content"), Labels.getLabel("message.confirm.delete.title"), Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener() {
            @Override
            public void onEvent(Event e) {
                if (Messagebox.ON_YES.equals(e.getName())) {
                    Row rowSelected = (Row) event.getOrigin().getTarget().getParent().getParent();
                    Category category = rowSelected.getValue();
                    category.setCategoryStatus(Constants.STATUS_INACTIVE);
                    category.setCreateDate(new Date());
                    categoryService.saveOrUpdate(category);
                    loadCategory();
                }
            }
        });
    }
}
