//
//  LoginCallback.java
//  Studyico
//
//  Created by Mohamed Salah on 4/23/2022.
//

package com.egybank.helpers.auth;


import com.egybank.models.User;

public interface LoginCallback {
    void onLoginSuccess(User user);
    void onLoginError();
}
