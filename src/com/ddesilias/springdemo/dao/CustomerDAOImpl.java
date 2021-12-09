package com.ddesilias.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddesilias.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Customer> getCustomers() {

		Session session = sessionFactory.getCurrentSession();

		Query<Customer> theQuery = session.createQuery("from Customer order by lastName", Customer.class);

		List<Customer> customers = theQuery.getResultList();

		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		Session session = sessionFactory.getCurrentSession();

		session.save(theCustomer);
	}

}
