package com.gdglima.conexionremota.model.entity;

import java.io.Serializable;

/**
 * Created by emedinaa on 07/03/16.
 */
public class UserEntity implements Serializable {

    private String name;
    private String email;
    private String objectId;
    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
