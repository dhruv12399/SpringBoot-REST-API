package com.example.myappDB.services_test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.myappDB.dao.CustomerRepository;
import com.example.myappDB.entities.Customer;
import com.example.myappDB.exception.ApiRequestException;
import com.example.myappDB.services.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerServiceTest {
	
	@InjectMocks
	private CustomerService customerService;
	
	@Mock
	private CustomerRepository customerRepository;
	
	public static final Integer id = 101;
	public static final String branch = "Pune";
	
	@Test
	public void getCustomerByIdTest() {
		customerService.getCustomerById(id);
		verify(customerRepository).findById(id);
	}
	
	@Test
	public void getAllCustomersTest() {
		customerService.getAllCustomers();
		verify(customerRepository).findAll();
	}
	
	@Test
	public void getCustomerByBranchTest() {
		try{
			customerService.getCustomerByBranch(branch);
			verify(customerRepository).findByBranch(branch);
		}
		catch(ApiRequestException e){
			assertEquals(e.getMessage(), "No Records found for this branch!!");
		}
	}
	
	@Test
	public void addCustomerTest() {
		Customer customer = mock(Customer.class);
		customerService.addCustomer(customer);
		verify(customerRepository).save(customer);
	}
	
	@Test
	public void updateTest() {
		
		Customer customer = mock(Customer.class);
				
		when(customer.getId()).thenReturn(id);
		when(customer.getFirstName()).thenReturn(String.valueOf("aditya"));
		when(customer.getLastName()).thenReturn(String.valueOf("sharma"));
		when(customer.getEmail()).thenReturn(String.valueOf("aditya-a.sharma@db.com"));
		when(customer.getBranch()).thenReturn(String.valueOf("Pune"));
		
		when(customerRepository.findById(id)).thenReturn(Optional.ofNullable(customer));
		
		customerService.update(id, customer);		
		verify(customerRepository).save(customer);		
	}
	
	@Test
	public void deleteTest() {
		customerService.delete(id);
		verify(customerRepository).deleteById(id);
	}
}
