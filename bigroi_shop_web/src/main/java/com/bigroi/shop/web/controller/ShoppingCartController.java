package com.bigroi.shop.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bigroi.shop.model.PurchaseOrder;
import com.bigroi.shop.model.ShoppingCart;
import com.bigroi.shop.model.ShoppingCartItem;
import com.bigroi.shop.service.PurchaseOrderService;

@Controller
public class ShoppingCartController {
	
	private Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);
		
	@Autowired
	private ShoppingCart shoppingCart;
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@RequestMapping(path="/add-item", method = RequestMethod.GET)
	public String addItem(@RequestParam("pcode") Integer productCode, ModelMap model) {
		logger.debug("addItem: " + productCode);
		ShoppingCartItem item = new ShoppingCartItem(productCode);
		shoppingCart.addShoppingItem(item);
		return "redirect:products";
	}
	
	@RequestMapping(path="/shopping-cart", method = RequestMethod.GET)
	public String showShoppingCart(ModelMap model) {
		model.addAttribute("shoppingCart", shoppingCart);
		model.addAttribute("title", "Shopping cart");
		return "shoppingCart";
	}
	
	@RequestMapping(path="/makeOrder", method = RequestMethod.POST)
	public String makeOrder() {
		PurchaseOrder po = new PurchaseOrder();		
		return "shoppingCart";
	}

}
