package com.bigroi.shop.web.exception.handler;


import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DataAccessException.class)
	public String handleDataAccessException(DataAccessException e, Model model) {
		model.addAttribute("errorMessage", "Failed to access database");
		model.addAttribute("errorMessageDetails", e.getMessage());
		return "errors/error";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, Model model) {
		model.addAttribute("errorMessage", "Error");
		model.addAttribute("errorMessageDetails", e.getMessage());
		return "errors/error";
	}

}
