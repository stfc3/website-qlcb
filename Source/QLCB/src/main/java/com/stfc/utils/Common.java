/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.utils;

import com.stfc.website.Memory;
import com.stfc.website.bean.Post;
import com.stfc.website.bean.WidgetContent;
import com.stfc.website.bean.WidgetMapContent;
import com.stfc.website.domain.Category;
import com.stfc.website.domain.Param;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author daond
 */
public class Common {

    private static final Logger logger = Logger.getLogger(Common.class);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    public static Param getParamByKey(String paramKey) {
        if (Memory.getListParamCache().containsKey(paramKey)) {
            return Memory.getListParamCache().get(paramKey);
        }
        return null;
    }

    public static Category getCategoryById(Long categoryId) {
        if (Memory.getListCategoryCache().containsKey(categoryId)) {
            return Memory.getListCategoryCache().get(categoryId);
        }
        return null;
    }

    public List<Post> getPostByContent(WidgetMapContent wg, List<Post> lstPost) {
        List<Post> lstPostByContent = new ArrayList<>();
        if (wg != null && wg.getListContent() != null && !wg.getListContent().isEmpty() && lstPost != null && !lstPost.isEmpty()) {
            for (WidgetContent wc : wg.getListContent()) {
                for (Post p : lstPost) {
                    if (Constants.WIDGET_CONTENT_TYPE_CATEGORY.equals(wc.getWidgetType())
                            && Long.parseLong(wc.getWidgetContent()) == p.getCategoryId()) {
                        lstPostByContent.add(p);
                    }

                }
            }
        }
        return lstPostByContent;
    }

    public boolean checkNewsPost(Date postDate) {
        if (postDate != null) {
            Param param = Common.getParamByKey(Constants.KEY_COMPATE_NEW_POST);
            if (param != null && param.getParamValue() != null) {
                int dateCompate = Integer.parseInt(param.getParamValue());
                Date dt = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                c.add(Calendar.DATE, -dateCompate);
                dt = c.getTime();
                if (postDate.after(dt)) {
                    return true;
                }
            }
        }
        return false;
    }
}
