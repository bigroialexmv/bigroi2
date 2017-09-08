package com.bigroi.shop.web.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bigroi.shop.filters.Page;
import com.bigroi.shop.filters.UserFilter;
import com.bigroi.shop.model.User;
import com.bigroi.shop.service.UserService;

@RestController("users")
@RequestMapping(path="shop/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(path="")
	public ResponseEntity<Page<User>> getUsers() throws Exception {
		Page<User> usersPage = userService.findByUserFilter(new UserFilter());
		ResponseEntity.ok(usersPage);
		Thread.sleep(3000);
		return ResponseEntity.ok(usersPage);
	}

}
