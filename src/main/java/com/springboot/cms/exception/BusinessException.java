package com.springboot.cms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BusinessException extends Exception {

    private static final long serialVersionUID = 1L;

    public BusinessException(String message){
        super(message);
    }
}
