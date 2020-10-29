package com.cinema.model.dto.user;

import com.cinema.lib.EmailConstraint;
import com.cinema.lib.FieldsValueMatch;
import javax.validation.constraints.NotNull;

@FieldsValueMatch(field = "password",
        fieldMatch = "repeatPassword")
public class UserRegistrationDto {
    @NotNull(message = "Email could not be null")
    @EmailConstraint(message = "Incorrect email format!")
    private String email;
    @NotNull(message = "Password could not be null")
    private String password;
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
