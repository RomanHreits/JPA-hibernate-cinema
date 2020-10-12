package com.cinema.service.impl;

import com.cinema.dao.ShoppingCartDao;
import com.cinema.dao.TicketDao;
import com.cinema.lib.Inject;
import com.cinema.lib.Service;
import com.cinema.model.MovieSession;
import com.cinema.model.ShoppingCart;
import com.cinema.model.Ticket;
import com.cinema.model.User;
import com.cinema.service.ShoppingCartService;
import java.util.ArrayList;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao cartDao;

    @Inject
    private TicketDao ticketDao;

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setMovieSession(movieSession);
        ticket.setUser(user);
        ticketDao.add(ticket);
        ShoppingCart userCart = cartDao.getByUser(user);
        userCart.getTickets().add(ticket);
        cartDao.update(userCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return cartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        cartDao.add(cart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.setTickets(new ArrayList<>());
        cartDao.update(shoppingCart);
    }
}
