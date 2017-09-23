package com.bigroi.shop.web.controller.rest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<User>> getUsers(UserFilter userFilter) throws Exception {
		Page<User> usersPage = userService.findByUserFilter(userFilter);
		return ResponseEntity.ok(usersPage);
	}	
	
	
	@RequestMapping(method=RequestMethod.GET, path="/{userId}")
	public ResponseEntity<User> getUser(@PathVariable int userId) throws Exception {
		User user = userService.findUserById(userId);
		if (user == null) {
			HeadersBuilder<?> headersBuilder = ResponseEntity.notFound();
			ResponseEntity<User> responseEntity = headersBuilder.build();
			return responseEntity; // ResponseEntity.notFound().build();
		}
		ResponseEntity<User> responseEntity = ResponseEntity.ok(user);
		return responseEntity;
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
		userService.save(user);
		URI newUserURI = new URI( "users/" + user.getId() );
		return ResponseEntity.created(newUserURI).build();
	}
	
	@RequestMapping(method=RequestMethod.PUT, path="/{userId}", consumes="application/json")
	public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody User user) throws Exception {
		user.setId(userId);
		userService.save(user);
		return ResponseEntity.ok().build();
	}

}
