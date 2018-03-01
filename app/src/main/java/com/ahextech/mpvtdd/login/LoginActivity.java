package com.ahextech.mpvtdd.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ahextech.mpvtdd.home.HomeActivity;
import com.ahextech.mvptdd.R;

/**
 * Created by ahextech on 28/2/18.
 */

public class LoginActivity extends AppCompatActivity implements LoginViewContracts, View.OnClickListener {
    private EditText etUserName, etPassword;
    private Button btnSignIn;
    private String userName, userPassword;
    private LoginPresenterImpl loginPresenter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserName = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnSignIn = findViewById(R.id.btn_sign_in);
        loginPresenter = new LoginPresenterImpl(this);
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void setUserNameError(String message) {
        etUserName.setError(message);
    }

    @Override
    public void setPasswordError(String message) {
        etPassword.setError(message);
    }

    @Override
    public void navigateToNextActivity() {
        dialog.dismiss();
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void showAlert(String msg) {
        dialog.dismiss();
        Snackbar.make(etUserName, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {
        dialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
        dialog.setMessage("Authenticating.....");
        dialog.setCanceledOnTouchOutside(true);
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void hideProgressDialog() {
        dialog.hide();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_in:
                userName = etUserName.getText().toString();
                userPassword = etPassword.getText().toString();
                loginPresenter.validateCredentials(userName, userPassword);

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
