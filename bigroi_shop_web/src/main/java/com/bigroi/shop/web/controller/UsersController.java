package com.bigroi.shop.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bigroi.shop.model.User;
import com.bigroi.shop.service.UserService;

@Controller
//@RequestMapping("/users")
public class UsersController {
	
	private Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping(path="/users", method = RequestMethod.GET)
	public String showUsers(ModelMap model) throws Exception {
		logger.debug("show users: " + userService.findAll());
		model.addAttribute("message1", "Hello Spring MVC Framework!");
	    return "users";
	}
	
	@RequestMapping(path="/user", method = RequestMethod.GET)
	public String showUser(ModelMap model) throws Exception {
		logger.debug("show users: " + userService.findAll());
		User user = new User("John", "Doe", "john@doe.com", "+375");
		model.addAttribute("title", "User details");
		model.addAttribute("user", user);
	    return "user";
	}
	
}
