package com.neko.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
 * @author cfriasb
 *
 */
public abstract class AbstractController {

	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<AbstractException> handleException(Exception e) {
	 return new ResponseEntity<AbstractException>(new AbstractException("ERROR", e.getMessage()), HttpStatus.CONFLICT);
	}
}
