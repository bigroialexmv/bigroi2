package com.bigroi.shop.web.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bigroi.shop.helpers.LogHelper;
import com.bigroi.shop.model.User;

@Controller
@RequestMapping(path="registration")
public class RegistrationController {
	
	private Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public String showRegistration(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String register(@Valid User user, BindingResult bindingResult, @RequestParam("password") String password) {
		logger.info("Regestering user: " + user);
		logger.info("User password: " + password);
		if (password == null || password.trim().equals("") ) {
			bindingResult.addError(new ObjectError("password", "Password is required"));
		}
		if (bindingResult.hasErrors()) {
			LogHelper.logBindingResults(logger, bindingResult);
			return "registration";
		}
		
		return "registration";
	}

}
