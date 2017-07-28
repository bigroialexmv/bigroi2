package com.bigroi.shop.model.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bigroi.shop.model.Product;


public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {		
		return Product.class.equals(c);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Product product = (Product) obj;
		if( product.getCode() <=0 ) {
			errors.rejectValue("code", "negativeValue", new Object[]{"'code'"}, "code can't be negative");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required", "Name value is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "price.required", "Price is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.required", "Description is required");
		
	}

}
