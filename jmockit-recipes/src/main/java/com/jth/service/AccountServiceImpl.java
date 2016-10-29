package com.jth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jth.domain.Account;
import com.jth.domain.Order;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	protected OrderService orderService;
	
	@Override
	public int getBalance(Account account) {
		List<Order> orders = orderService.findOrders(account.getId());
		Integer total = orders.stream().map(Order::getAmount).reduce(0, (a, b) -> a + b);
		return total;
	}

}
