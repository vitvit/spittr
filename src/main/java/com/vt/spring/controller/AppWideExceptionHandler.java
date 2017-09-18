package com.vt.spring.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import exception.DuplicateSpittrException;

@ControllerAdvice
public class AppWideExceptionHandler {

	@ExceptionHandler(DuplicateSpittrException.class)
	public String duplicateSprittrHandler() {
		return "error/duplicate";
	}
}
