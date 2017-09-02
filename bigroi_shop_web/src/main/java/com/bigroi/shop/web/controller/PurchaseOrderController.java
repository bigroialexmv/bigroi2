package com.bigroi.shop.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bigroi.shop.model.PurchaseOrder;
import com.bigroi.shop.model.PurchaseOrderProduct;
import com.bigroi.shop.model.User;
import com.bigroi.shop.model.validation.PurchaseOrderValidator;
import com.bigroi.shop.service.PurchaseOrderProductService;
import com.bigroi.shop.service.PurchaseOrderService;
import com.bigroi.shop.service.UserService;


@Controller
public class PurchaseOrderController {

	
	private Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	PurchaseOrderProductService purchaseOrderProductService;
	
//	@InitBinder
//	private void initBinder(WebDataBinder binder) {
//		Validator purchaseOrderValidator = new PurchaseOrderValidator();
//		logger.debug("initBinder: " + purchaseOrderValidator);
//		binder.setValidator(purchaseOrderValidator);
//	}
	
	@RequestMapping(path = "/orders", method = RequestMethod.GET)
	public String showPurchaseOrders(@RequestParam Integer userId, ModelMap model) throws Exception {
		User user = userService.findUserById(userId);
		List<PurchaseOrder> orders = purchaseOrderService.findOrdersByUserId(userId);
		model.addAttribute("title", "My orders");
		model.addAttribute("user", user);
		model.addAttribute("orders", orders);
		return "userOrders";
	}
	
	@RequestMapping(path = "/order", method = RequestMethod.GET)
	public String showOrderDetails(@RequestParam Integer orderId, ModelMap model) throws Exception {
		List<PurchaseOrderProduct> pops = purchaseOrderProductService.findPurchaseOrderPoductByOrderId(orderId);
		PurchaseOrder po = purchaseOrderService.findById(orderId);
		model.addAttribute("title", "Order # " + orderId);
		model.addAttribute("order", po);
		model.addAttribute("products", pops);
		return "orderDetails";
	}	
	
}
