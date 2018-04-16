/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.utils;

/**
 *
 * @author daond
 */
public class ValidateUtils {

    public static boolean isValidPhone(String phone) {
        phone = phone.replaceAll("\\ ", "");
        int check;
        try {
            if ("+84".equalsIgnoreCase(phone.substring(0, 3))) {
                if (phone.length() > 13 || phone.length() < 12) {
                    return false;
                }
                String phoneNumber = phone.substring(3, phone.length());
                check = Integer.parseInt(phoneNumber);
            } else if ("0".equalsIgnoreCase(phone.substring(0, 1))) {
                if (phone.length() > 12 || phone.length() < 10) {
                    return false;
                }
                check = Integer.parseInt(phone.substring(1, phone.length()));
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String phone = "+84 987654321";
        System.out.println("check: " + isValidPhone(phone));
    }

}
