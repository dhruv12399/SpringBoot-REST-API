package com.example.myappDB.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myappDB.dao.CustomerRepository;
import com.example.myappDB.entities.Customer;
import com.example.myappDB.exception.ApiRequestException;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	

	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	
	public Customer getCustomerById(Integer id) {
		if(!customerRepository.findById(id).isPresent()) 
			return null;
		return customerRepository.findById(id).get();
	}

	public List<Customer> getAllCustomers() {
		return (List<Customer>)customerRepository.findAll();
	}
	
	public List<Customer> getCustomerByBranch(String branch) {
		if(customerRepository.findByBranch(branch).isEmpty())
			throw new ApiRequestException("No Records found for this branch!!");
		return (List<Customer>)customerRepository.findByBranch(branch);
	}
	
	public void delete(Integer id) {
		customerRepository.deleteById(id);
	}
	
	public void update(Integer id, Customer updatedCustomer) {
		Customer customerToBeUpdated = customerRepository.findById(id).get();
		customerToBeUpdated.setFirstName(updatedCustomer.getFirstName());
		customerToBeUpdated.setLastName(updatedCustomer.getLastName());
		customerToBeUpdated.setEmail(updatedCustomer.getEmail());
		customerToBeUpdated.setBranch(updatedCustomer.getBranch());
		customerRepository.save(customerToBeUpdated);		
	}
	
	
//	public Customer getCustomerById(Integer id) {
//	Optional<Customer> customer = customerRepository.findById(id);
//	if (customer.isPresent()) {
//        return customer.get();
//    } else {
//    	throw new ApiRequestException("No customer found with id " + id);
//    }
//}
	
//	public void delete(Integer id) {
//	Optional<Customer> customerToDelete = customerRepository.findById(id);
//	if (!customerToDelete.isPresent()) {
//		throw new ApiRequestException("No customer found with id " + id);
//    } 
//	customerRepository.deleteById(id);
//	return "Customer with id " + id + " is deleted";
//}
	
//	public void update(Integer id, Customer updatedCustomer) {
//	Optional<Customer> customerToUpdate = customerRepository.findById(id);
//	if (!customerToUpdate.isPresent()) {
//		customerRepository.save(customerDetails);
//		return "New Customer with id " + customerDetails.getId() + " is added";
//    } 
//	
//	customerToUpdate.get().setFirstName(customerDetails.getFirstName());
//	customerToUpdate.get().setLastName(customerDetails.getLastName());
//	
//	customerRepository.save(customerToUpdate.get());
//	return "Customer with id " + id + " is updated";
//}
	
	
}
