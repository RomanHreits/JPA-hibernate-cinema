package com.cinema.service.impl;

import com.cinema.dao.OrderDao;
import com.cinema.model.Order;
import com.cinema.model.User;
import com.cinema.service.OrderService;
import com.cinema.service.ShoppingCartService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final ShoppingCartService cartService;
    private final OrderDao orderDao;

    public OrderServiceImpl(ShoppingCartService cartService, OrderDao orderDao) {
        this.cartService = cartService;
        this.orderDao = orderDao;
    }

    @Override
    public Order completeOrder(Order order) {
        cartService.clear(cartService.getByUser(order.getUser()));
        return orderDao.create(order);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
