package com.ahextech.mpvtdd.login;

/**
 * Created by ahextech on 28/2/18.
 */

public interface LoginViewContracts {
    void setUserNameError(String msg);

    void setPasswordError(String msg);

    void navigateToNextActivity();

    void showAlert(String msg);

    void showProgressDialog();

    void hideProgressDialog();

}
