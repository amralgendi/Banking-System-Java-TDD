package com.egybank.controllers.onboarding.signup;

import com.egybank.helpers.db.dao.UserDataAccess;
import com.egybank.models.User;
import org.junit.jupiter.api.*;

class SignUpControllerTest {
    private SignUpController signUpController;

    private String name = "Test Name";
    private String email = "testemail@test.com";
    private String password = "TestPassword@200";
    private String wrongEmail = "amc";
    private String wrongPassword = "wTestP";
    private static Integer id = -1;

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

        id = user.getId();
    }

    @Test
    void signupWrongVerifiedPassword() {
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> signUpController.signup(name, email, password, ""));
    }

    @Test
    void signupWrongEmail() {
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> signUpController.signup(name, wrongEmail, password, password));
    }

    @Test
    public void signupWrongPassword() {
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> signUpController.signup(name, email, wrongPassword, wrongPassword));
    }

    @AfterAll
    public static void tearDown(){
        UserDataAccess.deleteUser(id);
    }
}