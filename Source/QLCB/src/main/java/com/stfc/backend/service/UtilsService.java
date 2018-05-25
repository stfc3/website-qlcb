/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.service;

import com.stfc.backend.domain.Enroll;
import com.stfc.backend.domain.FeedBack;
import java.util.List;

/**
 *
 * @author admin
 */
public interface UtilsService {

    List<Enroll> searchEnroll(Enroll enroll);

    List<FeedBack> onSearchFeedBack(FeedBack feedBack);
}
