/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.backend.excel.exception;

/**
 *
 * @author viettx
 */
public class WrongFileTemplateException extends ExcelReadingException {

    public WrongFileTemplateException(String message) {
        super(message);
    }
}
