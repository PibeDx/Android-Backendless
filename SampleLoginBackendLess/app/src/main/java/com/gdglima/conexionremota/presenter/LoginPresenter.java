package com.gdglima.conexionremota.presenter;

import com.gdglima.conexionremota.model.entity.UserEntity;
import com.gdglima.conexionremota.model.interactor.LogInCallback;
import com.gdglima.conexionremota.model.interactor.LogInInteractor;
import com.gdglima.conexionremota.request.entity.BaseResponse;
import com.gdglima.conexionremota.request.entity.LoginRaw;
import com.gdglima.conexionremota.request.entity.LoginResponse;
import com.gdglima.conexionremota.view.LogInView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by em on 9/03/16.
 */
public class LoginPresenter implements Presenter<LogInView> ,LogInCallback{

    private LogInView logInView;
    private LogInInteractor logInInteractor;
    private String email,password;


    public void logIn(String email,String password) {
        this.email = email;
        this.password = password;
        LoginRaw logInRaw= new LoginRaw();
        logInRaw.setLogin(this.email);
        logInRaw.setPassword(this.password);

        logInView.showLoading();
        logInInteractor.logIn(logInRaw, this);
    }

    @Override
    public void attachedView(LogInView view) {
        logInView= view;
        logInInteractor= new LogInInteractor();
    }

    @Override
    public void detachView() {
        logInView = null;
    }

    @Override
    public void onResponse(LoginResponse loginResponse, Response response) {
        logInView.hideLoading();

        if(loginResponse.isSuccess())
        {
            UserEntity userEntity= new UserEntity();
            userEntity.setEmail(loginResponse.getEmail());
            userEntity.setName(loginResponse.getName());
            userEntity.setObjectId(loginResponse.getObjectId());
            userEntity.setToken(loginResponse.getToken());
            logInView.onLoginSuccess(userEntity);
        }else
        {
            logInView.showError(loginResponse.getMessage());
        }

    }

    @Override
    public void onError(RetrofitError retrofitError) {
        logInView.hideLoading();
    }

    @Override
    public void onError(RetrofitError retrofitError, String json) {
        logInView.hideLoading();
        Gson gson = new GsonBuilder().create();
        BaseResponse baseResponse=gson.fromJson(json, BaseResponse.class);
        if(baseResponse!=null)
        {
            String message=baseResponse.getMessage();
            logInView.showError(message);
        }else
        {
            logInView.showError("Ocurrió un error");
        }

    }

    @Override
    public void onNetworkConnectionError() {
        logInView.hideLoading();
    }

    @Override
    public void onServerError(String message) {
        logInView.hideLoading();
    }
}
