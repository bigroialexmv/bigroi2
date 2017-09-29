package com.bigroi.shop.model.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bigroi.shop.model.User;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {		
		return User.class.equals(c);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("user validator: " + target);
		User user = (User) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.required", "First name value is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.required", "Last name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "phone.required", "Phone is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required", "Email is required");
		
	}

}
