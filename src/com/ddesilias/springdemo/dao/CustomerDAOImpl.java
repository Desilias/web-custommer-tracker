package com.ddesilias.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddesilias.springdemo.entity.Customer;
import com.ddesilias.springdemo.util.SortUtils;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Customer> getCustomers(int theSortField) {

		Session session = sessionFactory.getCurrentSession();
		// determine sort field
		String theFieldName = null;

		switch (theSortField) {
		case SortUtils.FIRST_NAME:
			theFieldName = "firstName";
			break;
		case SortUtils.LAST_NAME:
			theFieldName = "lastName";
			break;
		case SortUtils.EMAIL:
			theFieldName = "email";
			break;
		default:
			// if nothing matches the default to sort by lastName
			theFieldName = "lastName";
		}
		Query<Customer> theQuery = session.createQuery("from Customer order by " + theFieldName, Customer.class);

		List<Customer> customers = theQuery.getResultList();

		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		Session session = sessionFactory.getCurrentSession();

		Customer thecustomer = session.get(Customer.class, theId);

		return thecustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		Session session = sessionFactory.getCurrentSession();

		Query theQuery = session.createQuery("delete from Customer where id = :theCustomerId");

		theQuery.setParameter("theCustomerId", theId);

		theQuery.executeUpdate();

	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		Session session = sessionFactory.getCurrentSession();

		Query<Customer> theQuery = null;

		if (theSearchName != null && theSearchName.trim().length() > 0) {
			theQuery = session.createQuery(
					"from Customer where lower(firstName) like :theName or lower(lastName) like :theName",
					Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		} else {
			theQuery = session.createQuery("from Customer", Customer.class);
		}

		List<Customer> customers = theQuery.getResultList();

		return customers;
	}

}
