package com.bigroi.shop.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bigroi.shop.filters.Page;
import com.bigroi.shop.filters.PageableFilter;
import com.bigroi.shop.model.PurchaseOrder;
import com.bigroi.shop.model.PurchaseOrderProduct;
import com.bigroi.shop.model.User;
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
	public String showPurchaseOrders(@RequestParam Integer userId, ModelMap model, PageableFilter filter) throws Exception {
		User user = userService.findUserById(userId);
		Page<PurchaseOrder> ordersPage = purchaseOrderService.findByFilter(userId, filter);
		model.addAttribute("title", "My orders");
		model.addAttribute("user", user);
		model.addAttribute("ordersPage", ordersPage);
		return "userOrders";
	}
	
	@RequestMapping(path = "/order", method = RequestMethod.GET)
	public String showOrderDetails(@RequestParam Integer orderId, ModelMap model, PageableFilter filter) throws Exception {
		Page<PurchaseOrderProduct> orderPage = purchaseOrderProductService.findByFilter(orderId, filter);
		PurchaseOrder po = purchaseOrderService.findById(orderId);
		model.addAttribute("title", "Order # " + orderId);
		model.addAttribute("order", po);
		model.addAttribute("orderPage", orderPage);
		return "orderDetails";
	}	
	
}
