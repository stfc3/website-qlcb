/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.backend.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Sessions;

import com.stfc.website.bean.ConfigEntity;

/**
 *
 * @author admin
 */
public class LoadProperties {

    private static final Logger LOGGER = Logger.getLogger(LoadProperties.class);

    public ConfigEntity loadConfig() {
        ConfigEntity entity = new ConfigEntity();
        try {
            Properties properties = new Properties();
//            String path = Sessions.getCurrent().getWebApp().getRealPath("") + "/WEB-INF/config/config.properties";
            String path = "E:\\Source\\website-qlcb\\Source\\QLCB\\src\\main\\resources\\config.properties";
            InputStream input = new FileInputStream(path);
            properties.load(new InputStreamReader(input, Charset.forName(Constant.UTF8)));

            entity.setTimeOut(Integer.valueOf(properties.getProperty(Constant.PROPERTIES_TIME)));
            entity.setRecipient(properties.getProperty(Constant.PROPERTIES_RECIPIENT));
            entity.setMailSend(properties.getProperty(Constant.PROPERTIES_MAIL));
            entity.setPassword(properties.getProperty(Constant.PROPERTIES_PASSWORD));
            entity.setTitle(properties.getProperty(Constant.PROPERTIES_TITLE));
            entity.setContent(properties.getProperty(Constant.PROPERTIES_CONTENT));
            entity.setAttachment(properties.getProperty(Constant.PROPERTIES_ATTACHMENT));

            entity.setHost(properties.getProperty(Constant.SMTP_HOST));
            entity.setPort(properties.getProperty(Constant.SMTP_PORT));
            entity.setStarttls(properties.getProperty(Constant.SMTP_STARTTLS));

            entity.setCc(properties.getProperty(Constant.MAIL_CC));
            entity.setBcc(properties.getProperty(Constant.MAIL_BCC));

        } catch (IOException | NumberFormatException e) {
            LOGGER.error(e.getMessage(), e);

        }
        return entity;
    }

}
