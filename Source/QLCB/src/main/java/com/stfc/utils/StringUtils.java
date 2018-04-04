/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.utils;

import java.util.regex.Pattern;

/**
 *
 * @author admin
 */
public class StringUtils {

    private static final String PATTERN = "^[a-z0-9._]{1,100}$";

    public static boolean valiString(String value) {
        return !(value == null || "".equals(value));
    }

    public static boolean validatePattern(String value) {
        Pattern pattern = Pattern.compile(PATTERN);
        return pattern.matcher(value).matches();

    }

    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
  

    public static void main(String[] arg) {
        String s = "viettx_06@gmail.com@vnpay.vn";
        System.out.println(isValidEmailAddress(s));
    }

}
