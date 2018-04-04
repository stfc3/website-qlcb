/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.service;

import com.stfc.website.domain.User;
import java.util.List;

/**
 *
 * @author admin
 */
public interface UserService {

    List<User> getAllUser();

    List<User> search(User user);

    void save(User user);

    void update(User user);
}
