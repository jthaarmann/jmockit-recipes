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
		
		//act

		//assert
		
	}

	@Test
	public void testGetBalance_mockTheClass(@Mocked Account account){
		//arrange
		
		
		//act
		
		//assert
	}
	
	@Test
	public void testSumOfBalances_testingPrivateMethodDirectly(){
		//arrange

		//act

		//assert
	}

	@Test
	public void testGetBalance_stubMethodOnClass_alwaysFalse(){
		//arrange
		
		//act

		//assert
	}
	
	@Test
	public void testGetBalance_stubMethodOnClass_useInvocation(){
		//arrange
		
		//act

		//assert
	}
}
