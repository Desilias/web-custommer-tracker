package com.ddesilias.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ddesilias.springdemo.entity.Customer;
import com.ddesilias.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public String listCustommers(Model model) {

		List<Customer> theCustomers = customerService.getCustomers();

		model.addAttribute("customers", theCustomers);

		return "list-custommers";
	}

}
