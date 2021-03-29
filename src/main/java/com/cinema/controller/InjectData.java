package com.cinema.controller;

import com.cinema.model.Role;
import com.cinema.model.User;
import com.cinema.service.RoleService;
import com.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InjectData {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    public InjectData(UserService userService, RoleService roleService, PasswordEncoder encoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @PostConstruct
    public void inject() {
        setData();
    }

    private void setData() {
        Role admin = new Role();
        admin.setRoleName(Role.RoleType.ADMIN);
        roleService.add(admin);
        Role user = new Role();
        user.setRoleName(Role.RoleType.USER);
        roleService.add(user);
        Role role = roleService.getRoleByName("ADMIN");
        userService.add(new User("roman@in.ua", encoder.encode("roman"), Set.of(role)));
    }
}
