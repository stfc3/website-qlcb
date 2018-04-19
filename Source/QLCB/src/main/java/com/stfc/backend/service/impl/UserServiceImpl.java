/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.service.impl;

import com.stfc.website.domain.User;
import com.stfc.backend.dao.UserDAO;
import com.stfc.backend.service.UserService;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    UserDAO userDAO;

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUser() {
        try {
            return userDAO.getAllUser();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Transactional
    @Override
    public void save(User user) {
        try {
            userDAO.save(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> search(User user) {
        try {
            return userDAO.search(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Transactional
    @Override
    public void update(User user) {
        userDAO.update(user);
    }

}
