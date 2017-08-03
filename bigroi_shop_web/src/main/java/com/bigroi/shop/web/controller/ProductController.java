package com.bigroi.shop.web.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bigroi.shop.helpers.LogHelper;
import com.bigroi.shop.model.Product;
import com.bigroi.shop.model.validation.ProductValidator;
import com.bigroi.shop.service.ProductService;


@Controller
public class ProductController {

	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		Validator productValidator = new ProductValidator();
		logger.debug("initBinder: " + productValidator);
		binder.setValidator(productValidator);
	}

	@RequestMapping(path="/products", method = RequestMethod.GET)
	public String showProduct(ModelMap model1) throws Exception {
		model1.addAttribute("title", "Products");
		List<Product> products = productService.findAll();
		model1.addAttribute("products", products);
	    return "products";
	}
	
	@RequestMapping(path="/product", method = RequestMethod.GET)
	public String showProduct(@RequestParam("code") int code, ModelMap model1) throws Exception {
		Product product = productService.findById(code);
		model1.addAttribute("title", "Product details");
		model1.addAttribute("product", product);
	    return "product";
	}
	
	@RequestMapping(path="/product/edit", method = RequestMethod.GET)
	public String editProduct(@RequestParam int code, ModelMap model1) throws Exception {
		Product product = productService.findById(code);
		model1.addAttribute("title", "Product details");
		model1.addAttribute("product", product);
	    return "productEdit";
	}
	
	@RequestMapping(path="/product/save", method = RequestMethod.POST)
	public String saveProduct(@Validated Product product, BindingResult bindingResult, ModelMap model1) throws Exception {
		logger.debug("Saving product:" + product);
		if (bindingResult.hasErrors()) {
			LogHelper.logBindingResults(logger, bindingResult);
			model1.addAttribute("title", "Product details");
			return "productEdit";
		}
		productService.save(product);
	    return "redirect:/product?code=" + product.getCode();
	}
	
}
