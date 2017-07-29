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
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		if( user.getId() <=0 ) {
			errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.required", "First name value is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.required", "Last name is required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "phone.required", "Phone is required");
		
	}

}
