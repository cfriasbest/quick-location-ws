package com.quick.location.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cfriasb
 */
@Slf4j
public abstract class AbstractController {

    @ExceptionHandler(Exception.class)
    @ResponseBody

    public ResponseEntity<AbstractException> handleException(Exception e) {
        log.debug("error", e);
        return new ResponseEntity<>(new AbstractException("ERROR", e.getLocalizedMessage()),
            HttpStatus.CONFLICT);
    }
}
