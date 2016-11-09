package com.jth.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jth.domain.Account;
import com.jth.domain.Order;

import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Invocation;
import mockit.Mock;
import mockit.MockUp;
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
	public void testGetBalance_mockTheOrderServiceInterface(){
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

	@Test
	public void testGetBalance_mockTheClass(@Mocked Account account){
		//arrange
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
				account.getId();
				returns("1234");
			}
		};
		
		new Expectations(){
			{
				orderService.findOrders("1234");
				returns(orders);
			}
		};
		
		//act
		int balance = accountService.getBalance(account);
		
		//assert
		int expectedBalance = 100;
		assertEquals(expectedBalance, balance);
	}
	
	@Test
	public void testSumOfBalances_testingPrivateMethodDirectly(){
		//arrange
		ArrayList<Order> orders = new ArrayList<Order>();
		{
			Order order = new Order();
			order.setAmount(31);
			orders.add(order);
		}
		{
			Order order = new Order();
			order.setAmount(51);
			orders.add(order);
		}
		{
			Order order = new Order();
			order.setAmount(21);
			orders.add(order);
		}
		
		//act
		Integer sum = Deencapsulation.invoke(accountService, "sumOfBalances", orders);

		//assert
		Integer expectedBalance = 103;
		assertEquals(expectedBalance, sum);
	}

	@Test
	public void testGetBalance_stubMethodOnClass_alwaysFalse(){
		//arrange
		Account account = new Account();
		account.setId("1234");

		ArrayList<Order> orders = new ArrayList<Order>();
		{
			Order order = new Order();
			orders.add(order);
		}
		{
			Order order = new Order();
			orders.add(order);
		}
		{
			Order order = new Order();
			orders.add(order);
		}
		
		new MockUp<Order> () {
		    @Mock 
		    public boolean isOverdue() { 
		    	return false;
		    }
		};

		new Expectations(){
			{
				orderService.findOrders(account.getId());
				returns(orders);
			}
		};
		
		//act
		List<Order> overdueOrders = accountService.getOverdueOrders(account);

		//assert
		int expectedNumber = 0;
		int numberOfOverdueOrders = overdueOrders.size();
		assertEquals(expectedNumber, numberOfOverdueOrders);
	}
	
	@Test
	public void testGetBalance_stubMethodOnClass_useInvocation(){
		//arrange
		Account account = new Account();
		account.setId("1234");

		ArrayList<Order> orders = new ArrayList<Order>();
		{
			Order order = new Order();
			order.setId(1);
			orders.add(order);
		}
		{
			Order order = new Order();
			order.setId(2);
			orders.add(order);
		}
		{
			Order order = new Order();
			order.setId(3);
			orders.add(order);
		}
		
		new MockUp<Order> () {
		    @Mock 
		    public boolean isOverdue(Invocation invocation) { 
		    	Order order = invocation.getInvokedInstance(); 
		    	return order.getId() % 2 == 0; //returns true for even numbered Ids
		    }
		};

		new Expectations(){
			{
				orderService.findOrders(account.getId());
				returns(orders);
			}
		};
		
		//act
		List<Order> overdueOrders = accountService.getOverdueOrders(account);

		//assert
		int expectedNumber = 1;
		int numberOfOverdueOrders = overdueOrders.size();
		assertEquals(expectedNumber, numberOfOverdueOrders);
	}
}
