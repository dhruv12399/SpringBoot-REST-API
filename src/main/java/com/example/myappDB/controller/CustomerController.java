package com.example.myappDB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myappDB.entities.Customer;
import com.example.myappDB.exception.ApiRequestException;
import com.example.myappDB.services.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	// List all customers
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> findAllCustomers() {
		return new ResponseEntity<List<Customer>>(customerService.getAllCustomers(), HttpStatus.OK);
	}
	
	// Get customer by ID
	@GetMapping("/customers/{customer_id}")
	public ResponseEntity<Customer> findCustomerById(@PathVariable String customer_id) {
		Integer id;
		try {
		   id = Integer.parseInt(customer_id);
		}
		catch (NumberFormatException e) {
		   throw new ApiRequestException("Id should be numeric!!");
		}
		
		if(customerService.getCustomerById(id) == null) 
			throw new ApiRequestException("Customer Not Found!!");
		
		else 
			return new ResponseEntity<Customer>(customerService.getCustomerById(id), HttpStatus.OK);
	}
	
	// filter customer by branch
	@GetMapping("/customers/branch/{branch}") 
	public ResponseEntity<List<Customer>> findCustomerByBranch(@PathVariable String branch) {
		return new ResponseEntity<List<Customer>>(customerService.getCustomerByBranch(branch), HttpStatus.OK);
	}
	
	// Add new customer
	@PostMapping("/customers")
	public ResponseEntity<String> addNewCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		return new ResponseEntity<String>("Customer Added Successfully!!", HttpStatus.CREATED);
	} 
	
	// Update customer
	@PutMapping("/customers/{customer_id}")
	public ResponseEntity<String> updateCustomer(@PathVariable String customer_id, @RequestBody Customer updatedCustomer) {
		Integer id;
		try {
		   id = Integer.parseInt(customer_id);
		}
		catch (NumberFormatException e) {
		   throw new ApiRequestException("Id should be numeric!!");
		}
		
		if(customerService.getCustomerById(id) == null) 
			return new ResponseEntity<String>("Customer Not Found!!", HttpStatus.NOT_FOUND);

		else {
			customerService.update(id, updatedCustomer);
			return new ResponseEntity<String>("Customer Updated Successfully!!", HttpStatus.OK);
		}
	} 
	// Deleting customer
	@DeleteMapping("/customers/{customer_id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable String customer_id) {
		Integer id;
		try {
		   id = Integer.parseInt(customer_id);
		}
		catch (NumberFormatException e) {
		   throw new ApiRequestException("Id should be numeric!!");
		}
		
		if(customerService.getCustomerById(id) == null) 
			return new ResponseEntity<String>("Customer Not Found!!", HttpStatus.NOT_FOUND);
		
		else {
			customerService.delete(id);
			return new ResponseEntity<String>("Customer Deleted Successfully!!", HttpStatus.OK);
		}
	} 
	
	
//	@PutMapping("/customers/{id}")
//	public String updateCustomer(@PathVariable Integer id, @RequestBody Customer customerDetails) {
//		return customerService.updateCustomer(id, customerDetails);
//	}
//	
//	@DeleteMapping("/customers/{id}")
//	public String deleteCustomer(@PathVariable Integer id) {
//		return customerService.deleteCustomerById(id);
//	}
	
	
}
