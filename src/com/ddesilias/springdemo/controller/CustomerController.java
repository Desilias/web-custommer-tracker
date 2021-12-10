package com.ddesilias.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddesilias.springdemo.entity.Customer;
import com.ddesilias.springdemo.service.CustomerService;
import com.ddesilias.springdemo.util.SortUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public String listCustommers(Model model, @RequestParam(required = false) String sort) {

		List<Customer> theCustomers = null;

		if (sort != null) {

			int theSortField = Integer.parseInt(sort);
			theCustomers = customerService.getCustomers(theSortField);

		} else {

			theCustomers = customerService.getCustomers(SortUtils.LAST_NAME);

		}

		model.addAttribute("customers", theCustomers);

		return "list-custommers";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Customer theCustomer = new Customer();

		theModel.addAttribute("customer", theCustomer);

		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

		customerService.saveCustomer(theCustomer);

		return "redirect:/customer/list";

	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {

		Customer theCustomer = customerService.getCustomer(theId);

		theModel.addAttribute("customer", theCustomer);

		return "customer-form";
	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {

		customerService.deleteCustomer(theId);

		return "redirect:/customer/list";
	}

	@GetMapping("/search")
	public String searchCustomer(@RequestParam("theSearchName") String theSearchName, Model theModel) {
		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
		theModel.addAttribute("customers", theCustomers);
		return "list-custommers";
	}

}
