package com.bigroi.shop.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bigroi.shop.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	private Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	private UserService userService1;

	@RequestMapping(method = RequestMethod.GET)
	public String showUsers(ModelMap model) throws Exception {
		logger.debug("show users: " + userService1.findAll());
		model.addAttribute("message", "Hello Spring MVC Framework!");
	    return "users";
	}
	
}
