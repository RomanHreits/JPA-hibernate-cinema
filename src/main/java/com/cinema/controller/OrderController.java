package com.cinema.controller;

import com.cinema.mapper.OrderMapper;
import com.cinema.model.Order;
import com.cinema.model.User;
import com.cinema.model.dto.order.OrderRequestDto;
import com.cinema.model.dto.order.OrderResponseDto;
import com.cinema.service.OrderService;
import com.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper mapper;
    private final UserService userService;

    public OrderController(OrderService orderService, OrderMapper mapper, UserService service) {
        this.orderService = orderService;
        this.mapper = mapper;
        this.userService = service;
    }

    @PostMapping("/complete")
    public void create(@RequestBody OrderRequestDto orderRequestDto) {
        Order order = mapper.fromDtoToEntity(orderRequestDto);
        orderService.completeOrder(order);
    }

    @GetMapping
    public List<OrderResponseDto> getOrdersHistory(@RequestParam Long userId) {
        User user = userService.get(userId);
        return orderService.getOrderHistory(user)
                .stream()
                .map(mapper::fromEntityToDto)
                .collect(Collectors.toList());
    }
}
