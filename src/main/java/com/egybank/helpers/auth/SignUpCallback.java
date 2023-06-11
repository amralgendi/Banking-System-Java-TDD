//
//  LoginCallback.java
//  Studyico
//
//  Created by Mohamed Salah on 4/23/2022.
//

package com.egybank.helpers.auth;


import com.egybank.models.User;

public interface SignUpCallback {
    void onSignUpSuccess(User user);
    void onSignUpError();
}
