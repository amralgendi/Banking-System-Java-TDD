package com.egybank.controllers.onboarding.login;

import com.egybank.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginControllerTest {
    private LoginController loginController;

    private String email = "m@m.m";
    private String password = "1";
    private String wrongEmail = "a@m.m";
    private String wrongPassword = "2";
    private Integer id = 1;

    @BeforeEach
    void setUp(){
        loginController = new LoginController();
    }

    @Test
    void loginSucess() {
        User user = loginController.login(email, password);

        Assertions.assertEquals(id, user.getId());
    }

    @Test
    void loginEmpty(){
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> loginController.login("", "")
        );
    }

    @Test
    void loginWrongEmail(){
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> loginController.login(wrongEmail, password)
        );
    }

    @Test
    void loginWrongPassword(){
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> loginController.login(email, wrongPassword)
        );
    }
}