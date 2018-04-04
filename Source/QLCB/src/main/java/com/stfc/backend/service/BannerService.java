package com.stfc.backend.service;

import java.util.List;

import com.stfc.backend.domain.Banner;

public interface BannerService {

    List<Banner> getAllBanner();

    List<Banner> search(Banner banner);

    void save(Banner banner);

    void update(Banner banner);
}
