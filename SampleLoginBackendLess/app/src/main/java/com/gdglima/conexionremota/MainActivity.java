package com.gdglima.conexionremota;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.gdglima.conexionremota.model.entity.UserEntity;
import com.gdglima.conexionremota.presenter.LoginPresenter;
import com.gdglima.conexionremota.ui.activity.BaseActivity;
import com.gdglima.conexionremota.ui.activity.HomeActivity;
import com.gdglima.conexionremota.view.LogInView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Email : emedinaa@gmail.com
 * Password: 123456
 */
public class MainActivity extends BaseActivity implements LogInView {


    private static final String TAG = "MainActivity";
    @Bind(R.id.btnLogIn) View btnLogIn;
    @Bind(R.id.llayLoading) View llayLoading;
    @Bind(R.id.etePassword) EditText etePassword;
    @Bind(R.id.eteEmail) EditText eteEmail;
    @Bind(R.id.vContainer) View vContainer;

    private LoginPresenter loginPresenter;
    private String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectView();

        loginPresenter= new LoginPresenter();
        loginPresenter.attachedView(this);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm())
                {
                    loginPresenter.logIn(email,password);
                }
            }
        });
    }

    private boolean validateForm() {
        this.email= eteEmail.getText().toString().trim();
        this.password= etePassword.getText().toString().trim();

        if(TextUtils.isEmpty(this.email))return false;
        if(TextUtils.isEmpty(this.password))return false;

        return true;
    }

    private void injectView() {
        ButterKnife.bind(this);
    }

    @Override
    public void showLoading() {
        this.llayLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.llayLoading.setVisibility(View.GONE);
    }

    @Override
    public void showConnectionErrorMessage() {

    }

    @Override
    public void showError(String message) {
        Log.v(TAG, " message "+message);

        showMessage(vContainer,message);
    }

    @Override
    public void onLoginSuccess(UserEntity userEntity) {
        Log.v(TAG, "onLoginsuccess "+userEntity.toString());
        Bundle bundle= new Bundle();
        bundle.putSerializable("ENTITY",userEntity);
        next(bundle,HomeActivity.class,true);
    }


    @Override
    public Context getContext() {
        return this;
    }
}
