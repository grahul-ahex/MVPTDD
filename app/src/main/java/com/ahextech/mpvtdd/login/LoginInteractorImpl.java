package com.ahextech.mpvtdd.login;

import android.text.TextUtils;

import com.ahextech.mpvtdd.network.APIClient;
import com.ahextech.mpvtdd.network.APIService;
import com.ahextech.mpvtdd.network.POJO.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahextech on 28/2/18.
 */

public class LoginInteractorImpl implements LoginInteractor, Callback<LoginModel> {
    private onLogInFinishedListener listener;

    @Override
    public void login(String username, String password, onLogInFinishedListener onLogInFinishedListener) {
        listener = onLogInFinishedListener;
        if (TextUtils.isEmpty(username)) {
            listener.onUserNameError();
        } else if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
        } else {
            makeAPICall(username, password);
        }
    }

    private void makeAPICall(String username, String password) {
        APIService apiService = APIClient.getClient().create(APIService.class);
        LoginModel loginModel = new LoginModel();
        loginModel.setEmail(username);
        loginModel.setPassword(password);
        Call<LoginModel> call = apiService.authenticate(loginModel);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
        if (call.isExecuted()) {
            switch (response.code()) {
                case 200:
                    listener.onLoginSuccess();
                    break;
                case 404:
                    listener.onLoginFailure("Status: 404");
                    break;
                case 401:
                    listener.onLoginFailure("Status: 401");
                    break;
                case 500:
                    listener.onLoginFailure("Status: 500");
                    break;
                case 403:
                    listener.onLoginFailure("Status: 403");
                    break;
                case 504:
                    listener.onLoginFailure("Status: 504");
                    break;
                default:
                    break;
            }

        }
    }

    @Override
    public void onFailure(Call<LoginModel> call, Throwable t) {

        listener.onLoginFailure("Check your internet connection");
    }
}
