package com.jth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jth.domain.Account;
import com.jth.domain.Order;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	protected OrderService orderService;
	
	@Override
	public Integer getBalance(Account account) {
		List<Order> orders = orderService.findOrders(account.getId());
		Integer total = sumOfBalances(orders);
		return total;
	}
	
	@Override
	public List<Order> getOverdueOrders(Account account) {
		List<Order> orders = orderService.findOrders(account.getId());
		List<Order> overdueOrders = orders.stream().filter(o -> o.isOverdue()).collect(Collectors.toList());
		return overdueOrders;
	}
	
	private Integer sumOfBalances(List<Order> orders){
		Integer sum = orders.stream().map(Order::getAmount).reduce(0, (a, b) -> a + b);
		return sum;
	}
}
