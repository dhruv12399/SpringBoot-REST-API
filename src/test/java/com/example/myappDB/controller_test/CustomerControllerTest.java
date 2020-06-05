package com.example.myappDB.controller_test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.myappDB.controller.CustomerController;
import com.example.myappDB.entities.Customer;
import com.example.myappDB.services.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerControllerTest {
	
	@InjectMocks
	private CustomerController customerController;
	
	@Mock
	private CustomerService customerService;
	
	@Mock
	private static Customer customer;
	
	public static final Integer id = 100;
	public static final String foo = String.valueOf(id);
	public static final String branch = "Pune";
	
	@BeforeClass
	public static void setUp() {
		customer = new Customer(id, "anshu", "gaur", "anshu.gaur@db.com", "Pune");
	}
	
	@AfterClass
	public static void tearDown() {
		customer = null;
	}
	
	@Test
	public void findCustomerByIdTest() {
		when(customerService.getCustomerById(id)).thenReturn(customer);
		assertEquals(customerController.findCustomerById(foo).getBody(), customer);
	}
	
	@Test
	public void findAllCustomersTest() {
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customer);
		when(customerService.getAllCustomers()).thenReturn(customers);
		assertEquals(customerController.findAllCustomers().getBody().size(), customers.size());
	}
	
	@Test
	public void findCustomerByBranchTest() {
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customer);
		when(customerService.getCustomerByBranch(branch)).thenReturn(customers);
		assertEquals(customerController.findCustomerByBranch(branch).getBody().size(), customers.size());
	}
	
	@Test
	public void addNewCustomerTest() {
		assertEquals(customerController.addNewCustomer(customer).getStatusCode(), HttpStatus.CREATED);
	}
	
	
	@Test
	public void updateCustomerTestWhenCustomerIsNotPresent() {
		when(customerService.getCustomerById(id)).thenReturn(null);
		assertEquals(customerController.updateCustomer(foo, customer).getStatusCode(), HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void updateCustomerTestWhenCustomerIsPresent() {
		when(customerService.getCustomerById(id)).thenReturn(customer);
		assertEquals(customerController.updateCustomer(foo, customer).getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void deleteCustomerTestWhenCustomerIsNotPresent() {
		when(customerService.getCustomerById(id)).thenReturn(null);
		assertEquals(customerController.deleteCustomer(foo).getStatusCode(), HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void deleteCustomerTestWhenCustomerIsPresent() {
		when(customerService.getCustomerById(id)).thenReturn(customer);
		assertEquals(customerController.deleteCustomer(foo).getStatusCode(), HttpStatus.OK);
	}
}
