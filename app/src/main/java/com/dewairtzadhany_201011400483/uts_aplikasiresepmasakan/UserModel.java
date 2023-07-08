package com.dewairtzadhany_201011400483.uts_aplikasiresepmasakan;

public class UserModel {
    private String username;
    private String password;

    public UserModel() {
        // Default constructor
    }

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
