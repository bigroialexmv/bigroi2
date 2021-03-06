package com.bigroi.shop.web.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bigroi.shop.filters.Page;
import com.bigroi.shop.filters.UserFilter;
import com.bigroi.shop.helpers.LogHelper;
import com.bigroi.shop.model.User;
import com.bigroi.shop.service.UserService;

@Controller
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(path="/users", method = RequestMethod.GET)
	public String showUsers(ModelMap model, UserFilter filter) throws Exception {
		model.addAttribute("title", "Users");		
		Page<User> usersPage = userService.findUserPageByFilter(filter);
		model.addAttribute("usersPage", usersPage);
	    return "users";
	}
	
	@RequestMapping(path="/user/{userId}", method = RequestMethod.GET)
	public String showUser(@PathVariable("userId") int userId, ModelMap model, Locale locale) throws Exception {
		User user = userService.findUserById(userId);
		String title = messageSource.getMessage("title.userProfile", new Object[0], locale);
		model.addAttribute("title", title);
		model.addAttribute("user", user);
	    return "user";
	}
	
	@RequestMapping(path="/user/{userId}/edit", method = RequestMethod.GET)
	public String editUser(@PathVariable("userId") int userId, ModelMap model, Locale locale) throws Exception {
		User user = userService.findUserById(userId);
		String title = messageSource.getMessage("title.userProfile", new Object[0], locale);
		model.addAttribute("title", title);
		model.addAttribute("user", user);
	    return "userEdit";
	}
	
	@RequestMapping(path="/user/{userId}/save", method = RequestMethod.POST)
	public String saveUser(@Validated User user, BindingResult bindingResult, ModelMap model, @PathVariable("userId") int userId) throws Exception {
		if(user.getId() != null && user.getId() != userId ) {
			throw new SecurityException("User ID violation");
		}
		logger.debug("Saving user:" + user);
		if (bindingResult.hasErrors()) {
			LogHelper.logBindingResults(logger, bindingResult);
			return "userEdit";
		}
		userService.save(user);
	    return "redirect:/user/" + user.getId();
	}
	
}
