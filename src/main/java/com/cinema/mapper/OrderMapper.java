package com.cinema.mapper;

import com.cinema.model.Order;
import com.cinema.model.ShoppingCart;
import com.cinema.model.User;
import com.cinema.model.dto.order.OrderRequestDto;
import com.cinema.model.dto.order.OrderResponseDto;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final TicketMapper mapper;

    public OrderMapper(UserService userService,
                       ShoppingCartService shoppingCartService, TicketMapper mapper) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.mapper = mapper;
    }

    public Order fromDtoToEntity(OrderRequestDto requestDto) {
        User user = userService.get(requestDto.getUserId());
        ShoppingCart userCart = shoppingCartService.getByUser(user);
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setTickets(new ArrayList<>(userCart.getTickets()));
        order.setUser(user);
        return order;
    }

    public OrderResponseDto fromEntityToDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setLocalDateTime(order.getOrderDate());
        orderResponseDto.setUserId(order.getUser().getId());
        orderResponseDto.setTickets(order.getTickets().stream()
                .map(mapper::fromEntityToDto)
                .collect(Collectors.toList()));
        return orderResponseDto;
    }
}
