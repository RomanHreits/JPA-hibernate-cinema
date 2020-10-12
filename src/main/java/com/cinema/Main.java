package com.cinema;

import com.cinema.exceptions.AuthenticationException;
import com.cinema.lib.Injector;
import com.cinema.model.CinemaHall;
import com.cinema.model.Movie;
import com.cinema.model.MovieSession;
import com.cinema.model.ShoppingCart;
import com.cinema.model.User;
import com.cinema.security.AuthenticationService;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MovieService;
import com.cinema.service.MovieSessionService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {
    private static Injector injector = Injector.getInstance("com.cinema");

    public static void main(String[] args) throws AuthenticationException {
        Movie fastAndFurious = new Movie();
        Movie theLordOfRings = new Movie();
        Movie matrix = new Movie();
        fastAndFurious.setTitle("Fast and Furious");
        theLordOfRings.setTitle("The Lord of the Rings: The Return of the King");
        matrix.setTitle("The Matrix Reloaded");
        MovieService movieService = (MovieService) injector
                .getInstance(MovieService.class);
        movieService.add(fastAndFurious);
        movieService.add(matrix);
        movieService.add(theLordOfRings);
        movieService.getAll().forEach(System.out::println);
        //------------------------------------------------------------------
        CinemaHallService cinemaHallService = (CinemaHallService) injector
                .getInstance(CinemaHallService.class);
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
        MovieSessionService sessionService = (MovieSessionService) injector
                .getInstance(MovieSessionService.class);
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

        AuthenticationService authenticationService = (AuthenticationService) injector
                .getInstance(AuthenticationService.class);
        authenticationService.register("roman@mail.ru", "roman");
        log.info("Login user :" + authenticationService.login("roman@mail.ru", "roman"));

        authenticationService.register("pavlo@gmail.com", "pavlo");

        ShoppingCartService cartService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);
        UserService userService = (UserService) injector.getInstance(UserService.class);
        User roman = userService.findByEmail("roman@mail.ru").get();
        User pavlo = userService.findByEmail("pavlo@gmail.com").get();
        cartService.addSession(matrixRed, roman);
        cartService.addSession(matrixRed, roman);
        cartService.addSession(lordOfRingsBlue, pavlo);
        cartService.addSession(lordOfRingsBlue, pavlo);
        log.info("Roman's shoppingCart :" + cartService.getByUser(roman).toString());
        log.info("Pavlo's shoppingCart :" + cartService.getByUser(pavlo).toString());
        ShoppingCart romamSC = cartService.getByUser(roman);
        cartService.clear(romamSC);
        log.info("Roman's shoppingCart :" + cartService.getByUser(roman));
    }
}
