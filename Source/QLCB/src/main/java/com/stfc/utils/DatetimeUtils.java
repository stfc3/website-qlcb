/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author viettx
 */
public class DatetimeUtils {

    private static final Logger logger = Logger.getLogger(DatetimeUtils.class);

    public static String convertDateToString(Date pstrDate, String pstrPattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pstrPattern);
        try {
            if (pstrDate == null) {
                return "";
            }
            return dateFormat.format(pstrDate);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    public static int getTime(Date date, int type) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = 0;
        switch (type) {
            case 1:
                value = cal.get(Calendar.DATE);
                break;
            case 2:
                value = cal.get(Calendar.MONTH) + 1;
                break;
            case 3:
                value = cal.get(Calendar.YEAR);
                break;
            default:
                value = 0;
                break;
        }
        return value;

    }

    public static void main(String[] arg) {
        System.out.println(getTime(new Date(), 2));
    }
}
