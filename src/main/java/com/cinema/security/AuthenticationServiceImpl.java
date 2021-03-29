package com.cinema.security;

import com.cinema.model.Role;
import com.cinema.model.User;
import com.cinema.service.RoleService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService cartService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService cartService,
                                     RoleService roleService, PasswordEncoder encoder) {
        this.userService = userService;
        this.cartService = cartService;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(encoder.encode(password));
        Role userRole = roleService.getRoleByName("USER");
        user.setUserRole(Set.of(userRole));
        userService.add(user);
        cartService.registerNewShoppingCart(user);
        return user;
    }
}
