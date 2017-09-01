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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bigroi.shop.filters.Page;
import com.bigroi.shop.filters.UserFilter;
import com.bigroi.shop.helpers.LogHelper;
import com.bigroi.shop.model.User;
import com.bigroi.shop.service.UserService;

@Controller
public class UsersController {
	
	private Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(path="/admin/users", method = RequestMethod.GET)
	public String showUsers(ModelMap model, UserFilter filter) throws Exception {
		model.addAttribute("title", "Users");
		Page<UserFilter, User> usersPage = userService.findUsersPageByFilter(filter);
		model.addAttribute("usersPage", usersPage);
		
	    return "admin/users";
	}
	
	@RequestMapping(path="/admin/user", method = RequestMethod.GET)
	public String showUser(@RequestParam("userId") int userId, ModelMap model, Locale locale) throws Exception {
		User user = userService.findUserById(userId);
		String title = messageSource.getMessage("title.userProfile", new Object[0], locale);
		model.addAttribute("title", title);
		model.addAttribute("user", user);
	    return "admin/user";
	}
	
	@RequestMapping(path="/admin/user/edit", method = RequestMethod.GET)
	public String editUser(@RequestParam int userId, ModelMap model, Locale locale) throws Exception {
		User user = userService.findUserById(userId);
		String title = messageSource.getMessage("title.userProfile", new Object[0], locale);
		model.addAttribute("title", title);
		model.addAttribute("user", user);
	    return "admin/userEdit";
	}
	
	@RequestMapping(path="/admin/user/save", method = RequestMethod.POST)
	public String saveUser(@Validated User user, BindingResult bindingResult, ModelMap model) throws Exception {
		logger.debug("Saving user:" + user);
		if (bindingResult.hasErrors()) {
			LogHelper.logBindingResults(logger, bindingResult);
			return "admin/userEdit";
		}
		userService.save(user);
	    return "redirect:/admin/user?userId=" + user.getId();
	}
	
}
