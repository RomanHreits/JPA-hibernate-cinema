package com.cinema.service;

import com.cinema.model.Order;
import com.cinema.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(Order order);

    List<Order> getOrderHistory(User user);
}
