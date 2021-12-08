package com.ddesilias.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddesilias.springdemo.dao.CustomerDAO;
import com.ddesilias.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDAO customerDAO;

	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

}
