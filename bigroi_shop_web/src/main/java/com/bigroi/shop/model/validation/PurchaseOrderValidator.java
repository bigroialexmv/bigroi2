package com.bigroi.shop.model.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bigroi.shop.model.PurchaseOrder;


	public class PurchaseOrderValidator implements Validator {

		@Override
		public boolean supports(Class<?> c) {		
			return PurchaseOrder.class.equals(c);
		}

		@Override
		public void validate(Object obj, Errors errors) {
			PurchaseOrder po = (PurchaseOrder) obj;
			if( po.getId() <=0 ) {
				errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
			}
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deliveryAddressId", "deliveryAddressId.required", "Delivery address value is required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deliveryDate", "deliveryDate.required", "Delivery date is required");
			
			
		}

	}


