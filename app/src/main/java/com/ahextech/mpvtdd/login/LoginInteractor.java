package com.ahextech.mpvtdd.login;

/**
 * Created by ahextech on 28/2/18.
 */

public interface LoginInteractor {

    interface onLogInFinishedListener {
        void onUserNameError();

        void onPasswordError();

        void onLoginSuccess();

        void onLoginFailure(String status);
    }

    void login(String username, String password, onLogInFinishedListener listener);
}
