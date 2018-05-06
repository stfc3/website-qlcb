/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author dmin
 */
public class WebsiteFilter implements Filter {

    private static final Logger logger = Logger.getLogger(WebsiteFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("WebsiteFilter INIT OK");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("WebsiteFilter start filter");
        HttpServletRequest req = null;
        if ((request instanceof HttpServletRequest)) {
            req = (HttpServletRequest) request;
        }
        if (req != null) {
            logger.info("ServletPath: " + req.getServletPath());
            req.setAttribute(Constants.STFC_URL_ATTRIBUTE, req.getServletPath());
        }

        chain.doFilter(request, response);
        logger.info("WebsiteFilter end filter");
    }

    @Override
    public void destroy() {
        logger.info("WebsiteFilter DESTTROY OK");
    }

}
