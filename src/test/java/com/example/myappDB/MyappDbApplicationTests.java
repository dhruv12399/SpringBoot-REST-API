package com.example.myappDB;

import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
//import org.junit.runner.RunWith;

//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.example.myappDB.dao.CustomerRepository;
//import com.example.myappDB.entities.Customer;
//import com.example.myappDB.services.CustomerService;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class MyappDbApplicationTests {
	
	@Test
	public void contextLoads() {
	}
	
//	@Autowired
//	private CustomerService customerService;
//	
//	@MockBean
//	private CustomerRepository customerRepository;
//	
//	@Test
//	public void getAllCustomersTest() {
//		when(customerRepository.findAll()).thenReturn(Stream
//				.of(new Customer(101, "anshu", "gaur", "anshu.gaur@db.com", "Pune"), new Customer(102, "prabhas", "bhardwaj", "prabhas.bharwaj@db.com", "Mumbai")).collect(Collectors.toList()));
//	
//		assertEquals(2, customerService.getAllCustomers().size());
//	}
	
//	@Test
//	public void addCustomerTest() {
//		Customer customer = new Customer(101, "Anshu", "Gaur");
//		when(customerRepository.save(customer)).thenReturn(customer);
//		
//		String expected = "New Customer with id 101 is added";
//		assertEquals(expected, customerService.addCustomer(customer));
//	}
}
