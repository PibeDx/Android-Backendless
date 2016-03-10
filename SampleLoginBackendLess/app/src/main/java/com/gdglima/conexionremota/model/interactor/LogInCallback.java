package com.gdglima.conexionremota.model.interactor;


import com.gdglima.conexionremota.request.entity.LoginResponse;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by emedinaa on 11/12/15.
 */
public interface LogInCallback
{

    void onResponse(LoginResponse loginResponse, Response response);

    void onError(RetrofitError retrofitError);
    void onError(RetrofitError retrofitError,String json);

    void onNetworkConnectionError();

    void onServerError(String message);
}
