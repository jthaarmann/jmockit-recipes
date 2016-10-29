package com.jth.service;

import java.util.List;

import com.jth.domain.Order;

public interface OrderService {

	public List<Order> findOrders(String accountId);
}
