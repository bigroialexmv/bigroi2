package com.bigroi.shop.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bigroi.shop.helpers.LogHelper;
import com.bigroi.shop.model.UserRegistrationData;
import com.bigroi.shop.model.User;
import com.bigroi.shop.model.validation.RegistrationValidator;
import com.bigroi.shop.service.UserRegistrationService;

@Controller
@RequestMapping(path="registration")
public class UserRegistrationController {
	
	private Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);
	
	@Autowired
	private UserRegistrationService userRegistrationService;
	
	@Autowired
	private RegistrationValidator registrationValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.addValidators(registrationValidator);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String showRegistration(Model model) {
		model.addAttribute("registration", new UserRegistrationData(new User()));		
		return "registration";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String register(@Validated @ModelAttribute("registration") UserRegistrationData registration, BindingResult bindingResult) throws Exception {
		logger.info("Regestering user: " + registration.getUser());		
		if (bindingResult.hasErrors()) {
			LogHelper.logBindingResults(logger, bindingResult);
			return "registration";
		}
		userRegistrationService.register(registration);
		return "registration-success";
	}
	
}
