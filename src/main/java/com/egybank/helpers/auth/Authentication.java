//
//  Authentication.java
//  Studyico
//
//  Created by Mohamed Salah on 4/23/2022.
//

package com.egybank.helpers.auth;


import com.egybank.controllers.home.UserObjectUpdated;
import com.egybank.helpers.db.dao.UserDataAccess;
import com.egybank.models.User;

public class Authentication {
    private static User currentUser;
    private static UserObjectUpdated userObjectUpdated;

    public static void login(String email, String password, LoginCallback callback) {
        User user = UserDataAccess.getUser(email, password);

        if (user == null) {
            callback.onLoginError();
        }else {
            callback.onLoginSuccess(user);
            setCurrentUser(user);
        }
    }

    public static void logout() {
        currentUser = null;
    }

    public static void signUpAndLogin(User user, SignUpCallback callback) {
        Integer id = UserDataAccess.createNewUser(user);
        if (id == null) {
            callback.onSignUpError();
        }else {
            setCurrentUser(UserDataAccess.getUser(id));
            callback.onSignUpSuccess(currentUser);
        }
    }

    public static void setCurrentUser(User currentUser) {
        Authentication.currentUser = currentUser;
        if (userObjectUpdated != null) {
            userObjectUpdated.onUserUpdated(currentUser);
        }
    }

    public static User getCachedCurrentUser() {
        return currentUser;
    }

    public static User getCurrentUser() {
        currentUser = UserDataAccess.getUser(currentUser.getId());
        return currentUser;
    }

    public static void setUserObjectUpdated(UserObjectUpdated userObjectUpdated) {
        Authentication.userObjectUpdated = userObjectUpdated;
    }


}
