package com.gdglima.conexionremota.model.interactor;

import android.util.Log;

import com.gdglima.conexionremota.request.ApiClient;
import com.gdglima.conexionremota.request.entity.LoginRaw;
import com.gdglima.conexionremota.request.entity.LoginResponse;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * Created by emedinaa on 9/12/15.
 */
public class LogInInteractor {

    private static final String TAG = "LogInInteractor";


    public void logIn(LoginRaw logInRaw, final LogInCallback logInCallback)
    {
        ApiClient.getMyApiClient().login(logInRaw, new Callback<LoginResponse>() {
            @Override
            public void success(LoginResponse loginResponse, Response response) {
                logInCallback.onResponse(loginResponse,response);
            }

            @Override
            public void failure(RetrofitError error)
            {
                String json=null;
                try {
                    json= new String(((TypedByteArray)error.getResponse().getBody()).getBytes());
                }catch (NullPointerException e) {}
                Log.v(TAG, "json >>>> " + json);
                if(json!=null)
                {
                    logInCallback.onError(error,json);
                }else
                {
                    logInCallback.onServerError(error.getMessage());
                }
            }
        });
    }


}
