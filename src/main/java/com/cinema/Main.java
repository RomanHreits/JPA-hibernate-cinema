package com.cinema;

import com.cinema.config.AppConfig;
import com.cinema.exceptions.AuthenticationException;
import com.cinema.model.CinemaHall;
import com.cinema.model.Movie;
import com.cinema.model.MovieSession;
import com.cinema.model.Ticket;
import com.cinema.model.User;
import com.cinema.security.AuthenticationService;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MovieService;
import com.cinema.service.MovieSessionService;
import com.cinema.service.OrderService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final AnnotationConfigApplicationContext context
            = new AnnotationConfigApplicationContext(AppConfig.class);

    public static void main(String[] args) throws AuthenticationException {

        Movie fastAndFurious = new Movie();
        Movie theLordOfRings = new Movie();
        Movie matrix = new Movie();
        fastAndFurious.setTitle("Fast and Furious");
        theLordOfRings.setTitle("The Lord of the Rings: The Return of the King");
        matrix.setTitle("The Matrix Reloaded");
        MovieService movieService = context.getBean(MovieService.class);
        movieService.add(fastAndFurious);
        movieService.add(matrix);
        movieService.add(theLordOfRings);
        movieService.getAll().forEach(System.out::println);
        //------------------------------------------------------------------
        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
        CinemaHall red = new CinemaHall();
        red.setCapacity(100);
        cinemaHallService.add(red);
        CinemaHall blue = new CinemaHall();
        blue.setCapacity(75);
        cinemaHallService.add(blue);
        CinemaHall green = new CinemaHall();
        green.setCapacity(50);
        cinemaHallService.add(green);

        cinemaHallService.getAll().forEach(System.out::println);
        //----------------------------------------------------------------------------
        MovieSession matrixGreen = new MovieSession();
        matrixGreen.setCinemaHall(green);
        matrixGreen.setMovie(matrix);
        matrixGreen.setShowTime(LocalDateTime.now().minusHours(2L));
        MovieSessionService sessionService = context.getBean(MovieSessionService.class);
        sessionService.add(matrixGreen);

        MovieSession lordOfRingsGreen = new MovieSession();
        lordOfRingsGreen.setCinemaHall(green);
        lordOfRingsGreen.setMovie(theLordOfRings);
        lordOfRingsGreen.setShowTime(LocalDateTime.now().plusDays(1L));
        sessionService.add(lordOfRingsGreen);

        MovieSession fastAndFuriousGreen = new MovieSession();
        fastAndFuriousGreen.setCinemaHall(green);
        fastAndFuriousGreen.setMovie(fastAndFurious);
        fastAndFuriousGreen.setShowTime(LocalDateTime.now().plusHours(2L));
        sessionService.add(fastAndFuriousGreen);
        //------------------------------------------------------------------------------
        MovieSession matrixBlue = new MovieSession();
        matrixBlue.setMovie(matrix);
        matrixBlue.setCinemaHall(blue);
        matrixBlue.setShowTime(LocalDateTime.now().plusDays(1L));
        sessionService.add(matrixBlue);

        MovieSession lordOfRingsBlue = new MovieSession();
        lordOfRingsBlue.setMovie(theLordOfRings);
        lordOfRingsBlue.setCinemaHall(blue);
        lordOfRingsBlue.setShowTime(LocalDateTime.now());
        sessionService.add(lordOfRingsBlue);

        MovieSession fastAndFuriousBlue = new MovieSession();
        fastAndFuriousBlue.setMovie(fastAndFurious);
        fastAndFuriousBlue.setCinemaHall(blue);
        fastAndFuriousBlue.setShowTime(LocalDateTime.now().plusHours(2L));
        sessionService.add(fastAndFuriousBlue);
        //--------------------------------------------------------------------------------
        MovieSession fastAndFuriousRed = new MovieSession();
        fastAndFuriousRed.setMovie(fastAndFurious);
        fastAndFuriousRed.setCinemaHall(red);
        fastAndFuriousRed.setShowTime(LocalDateTime.now());
        sessionService.add(fastAndFuriousRed);

        MovieSession lordOfRingsRed = new MovieSession();
        lordOfRingsRed.setMovie(theLordOfRings);
        lordOfRingsRed.setCinemaHall(red);
        lordOfRingsRed.setShowTime(LocalDateTime.now().plusDays(1L));
        sessionService.add(lordOfRingsRed);

        MovieSession matrixRed = new MovieSession();
        matrixRed.setMovie(matrix);
        matrixRed.setCinemaHall(red);
        matrixRed.setShowTime(LocalDateTime.now().plusDays(1L));
        sessionService.add(matrixRed);
        //---------------------------------------------------------------------

        sessionService.findAvailableSessions(1L, LocalDate.now())
                .forEach(System.out::println);
        System.out.println("----><----");
        sessionService.findAvailableSessions(2L, LocalDate.now()
                .plusDays(1L)).forEach(System.out::println);
        //--------------------------------------------------------------------

        AuthenticationService authenticationService = context.getBean(AuthenticationService.class);
        authenticationService.register("roman@mail.ru", "roman");
        try {
            authenticationService.login("roman@mail.ru", "roman");
        } catch (AuthenticationException e) {
            logger.warn("Login error. Incorrect userEmail or password!", e);
        }
        authenticationService.register("pavlo@gmail.com", "pavlo");

        ShoppingCartService cartService = context.getBean(ShoppingCartService.class);
        UserService userService = context.getBean(UserService.class);
        User roman = userService.findByEmail("roman@mail.ru").get();
        User pavlo = userService.findByEmail("pavlo@gmail.com").get();
        cartService.addSession(matrixRed, roman);
        cartService.addSession(matrixRed, roman);
        cartService.addSession(lordOfRingsBlue, pavlo);
        cartService.addSession(lordOfRingsBlue, pavlo);
        //-----------------------------------------------------------------------------------
        OrderService orderService = context.getBean(OrderService.class);
        List<Ticket> romanTickets = cartService.getByUser(roman).getTickets();
        logger.info("Shopping cart: " + cartService.getByUser(roman));
        logger.info("Roman's order: " + orderService.completeOrder(romanTickets, roman));
        logger.info("Shopping cart: " + cartService.getByUser(roman));
        List<Ticket> pavloTickets = cartService.getByUser(pavlo).getTickets();
        logger.info("Pavlo's order: " + orderService.completeOrder(pavloTickets, pavlo));
        logger.info("Roman's order history: " + orderService.getOrderHistory(roman));
        logger.info("cartUser :" + cartService.getByUser(roman));
    }
}
