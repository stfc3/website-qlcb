/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.utils;

import java.text.Normalizer;
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
    
    /*
     * Hàm convert tieng viet khong dau
     */

    public static String unAccent(String s) {
        String temp = Normalizer.normalize(s.toLowerCase(), Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replace("đ", "d");
    }
    public static String convertSlug(String input){
        String strReturn=unAccent(input);
        return strReturn.replaceAll(" ", "-");
    }
  
//    public static boolean validateString(String value,  String vstrPattern) {
//        Pattern pattern = Pattern.compile(vstrPattern);
//        return pattern.matcher(value).matches();
//
//    }
//
//    public static void main(String[] arg) {
//        System.out.println(convertSlug("Tôi là đông"));
////        ["<>#%{}|\^~\\\[\\\]`]
//        System.out.println(validateString("gdgh","[\"<>#%{}|\\^~\\\\\\[\\\\\\]`]"));
//    }

}
