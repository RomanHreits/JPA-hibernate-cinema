package com.cinema.security;

import com.cinema.exceptions.AuthenticationException;
import com.cinema.lib.Inject;
import com.cinema.lib.Service;
import com.cinema.model.User;
import com.cinema.service.UserService;
import com.cinema.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> optionalUser = userService.findByEmail(email);
        if (optionalUser.isPresent()) {
            User userDB = optionalUser.get();
            String hashPassword = HashUtil.hashPassword(password, userDB.getSalt());
            if (hashPassword.equals(userDB.getPassword())) {
                return userDB;
            }
        }
        throw new AuthenticationException("Incorrect userEmail or password!");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return userService.add(user);
    }
}
