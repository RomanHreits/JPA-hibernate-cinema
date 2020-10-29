package com.cinema.controller;

import com.cinema.mapper.OrderMapper;
import com.cinema.model.ShoppingCart;
import com.cinema.model.User;
import com.cinema.model.dto.order.OrderResponseDto;
import com.cinema.service.OrderService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper mapper;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public OrderController(OrderService orderService, OrderMapper mapper,
                           UserService service, ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.mapper = mapper;
        this.userService = service;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/complete")
    public void create(Authentication authentication) {
        User user = getUser(authentication);
        ShoppingCart userCart = shoppingCartService.getByUser(user);
        orderService.completeOrder(userCart.getTickets(), user);
    }

    @GetMapping
    public List<OrderResponseDto> getOrdersHistory(Authentication authentication) {
        User user = getUser(authentication);
        return orderService.getOrderHistory(user)
                .stream()
                .map(mapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    private User getUser(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return userService.findByEmail(principal.getUsername()).get();
    }
}
