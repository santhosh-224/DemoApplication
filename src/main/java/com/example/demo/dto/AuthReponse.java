package com.example.demo.dto;

public class AuthReponse {

    public AuthReponse(String type, String token, String username) {
        this.type = type;
        this.token = token;
        this.username = username;
    }

    private String token;
    private String type;    //Bearer
    private String username;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
