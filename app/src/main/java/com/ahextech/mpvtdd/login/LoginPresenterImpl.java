package com.ahextech.mpvtdd.login;

/**
 * Created by ahextech on 28/2/18.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.onLogInFinishedListener {
    private LoginViewContracts viewContracts;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginViewContracts viewContracts) {
        this.viewContracts = viewContracts;
        interactor = new LoginInteractorImpl();
    }

    @Override
    public void validateCredentials(String userName, String userPassword) {
        viewContracts.showProgressDialog();
        interactor.login(userName, userPassword, this);
    }


    @Override
    public void onUserNameError() {
        viewContracts.hideProgressDialog();
        viewContracts.setUserNameError("Username empty");
    }

    @Override
    public void onPasswordError() {
        viewContracts.hideProgressDialog();
        viewContracts.setPasswordError("Password empty");
    }

    @Override
    public void onLoginSuccess() {
        viewContracts.hideProgressDialog();
        viewContracts.navigateToNextActivity();
    }

    @Override
    public void onLoginFailure(String status) {
        viewContracts.hideProgressDialog();
        viewContracts.showAlert(status);
    }

}
