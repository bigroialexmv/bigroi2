package com.bigroi.shop.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bigroi.shop.model.User;
import com.bigroi.shop.model.validation.UserValidator;
import com.bigroi.shop.service.UserService;

@Controller
//@RequestMapping("/users")
public class UsersController {
	
	private Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	private UserService userService;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		Validator userValidator = new UserValidator();
		logger.debug("initBinder: " + userValidator);
		binder.setValidator(userValidator);
	}

	@RequestMapping(path="/users", method = RequestMethod.GET)
	public String showUsers(ModelMap model) throws Exception {
		model.addAttribute("title", "Users");
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
	    return "users";
	}
	
	@RequestMapping(path="/user", method = RequestMethod.GET)
	public String showUser(@RequestParam("userId") int userId, ModelMap model) throws Exception {
		User user = userService.findUserById(userId);
		model.addAttribute("title", "User details");
		model.addAttribute("user", user);
	    return "user";
	}
	
	@RequestMapping(path="/user/edit", method = RequestMethod.GET)
	public String editUser(@RequestParam int userId, ModelMap model) throws Exception {
		User user = userService.findUserById(userId);
		model.addAttribute("title", "Edit user details");
		model.addAttribute("user", user);
	    return "userEdit";
	}
	
	@RequestMapping(path="/user/save", method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult bindingResult) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Saving user:" + user);
			logger.debug("User has errors: " + bindingResult.hasErrors());
			for(ObjectError error : bindingResult.getAllErrors()) {
				logger.debug("User validation error: " + error.getCode() + ", " + error.getDefaultMessage());
			}
		}
		if (bindingResult.hasErrors()) {
			return "userEdit";
		}
		userService.save(user);
	    return "redirect:/user?userId=" + user.getId();
	}
	
}
