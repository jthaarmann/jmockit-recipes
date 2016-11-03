package com.jth.service;

import java.util.List;

import com.jth.domain.Account;
import com.jth.domain.Order;

public interface AccountService {

	Integer getBalance(Account account);

	List<Order> getOverdueOrders(Account account);
	
}
