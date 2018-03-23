/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.utils;

import com.stfc.website.Memory;
import com.stfc.website.domain.Category;
import com.stfc.website.domain.Param;
import org.apache.log4j.Logger;

/**
 *
 * @author daond
 */
public class Common {

    private static final Logger logger = Logger.getLogger(Common.class);

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
}
