package com.gdglima.conexionremota.view;

import com.gdglima.conexionremota.model.entity.UserEntity;

/**
 * Created by emedinaa on 11/12/15.
 */
public interface LogInView extends BaseView{

    void showLoading();
    void hideLoading();
    void showConnectionErrorMessage();
    void showError(String message);
    void onLoginSuccess(UserEntity userEntity);
}
