package com.jth.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.jth.domain.Account;
import com.jth.domain.Order;

import mockit.Expectations;
import mockit.Mocked;

public class AccountServiceImplTest {

	AccountServiceImpl accountService;
	
	@Mocked
	OrderService orderService;
	
	@Before
	public void setUp(){
		accountService = new AccountServiceImpl();
		accountService.orderService = orderService;
	}
	
	@Test
	public void testGetBalance_mockTheOrderService(){
		//arrange
		Account account = new Account();
		account.setId("1234");

		ArrayList<Order> orders = new ArrayList<Order>();
		{
			Order order = new Order();
			order.setAmount(30);
			orders.add(order);
		}
		{
			Order order = new Order();
			order.setAmount(50);
			orders.add(order);
		}
		{
			Order order = new Order();
			order.setAmount(20);
			orders.add(order);
		}

		new Expectations(){
			{
				orderService.findOrders(account.getId());
				returns(orders);
			}
		};
		
		//act
		int balance = accountService.getBalance(account);

		//assert
		int expectedBalance = 100;
		assertEquals(expectedBalance, balance);
	}
}
