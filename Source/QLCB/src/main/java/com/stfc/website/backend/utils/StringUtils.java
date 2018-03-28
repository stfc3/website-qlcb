/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.backend.utils;

/**
 *
 * @author admin
 */
public class StringUtils {

    public static boolean valiString(String value) {
        return !(value == null || "".equals(value));
    }

}
