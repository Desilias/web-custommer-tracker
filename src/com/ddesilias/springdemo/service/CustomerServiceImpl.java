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
	public List<Customer> getCustomers(int theSortField) {
		return customerDAO.getCustomers(theSortField);
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		customerDAO.saveCustomer(theCustomer);

	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		// TODO Auto-generated method stub
		return customerDAO.getCustomer(theId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		customerDAO.deleteCustomer(theId);

	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String theSearchName) {
		// TODO Auto-generated method stub
		return customerDAO.searchCustomers(theSearchName);
	}

}
