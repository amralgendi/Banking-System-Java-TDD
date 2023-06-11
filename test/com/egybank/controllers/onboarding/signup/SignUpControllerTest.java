package com.egybank.controllers.onboarding.signup;

import com.egybank.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SignUpControllerTest {
    private SignUpController signUpController;

    private String name = "Test Name";
    private String email = "testemail@test.com";
    private String password = "TestPassword@200";
    private String wrongEmail = "amc";
    private String wrongPassword = "wTestP";

    @BeforeEach
    void setUp(){
        signUpController = new SignUpController();
    }

    @Test
    void signupSuccess() {
        User user = signUpController.signup(name, email, password, password);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(password, user.getPassword());
    }

    @Test
    void signupWrongVerifiedPassword() {
        User user = signUpController.signup("", "")
    }
}