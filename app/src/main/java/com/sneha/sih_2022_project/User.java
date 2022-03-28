package com.sneha.sih_2022_project;

public class User {
    private String password;
    private String email;

    public User(){

    }

    public User(String email, String password) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
