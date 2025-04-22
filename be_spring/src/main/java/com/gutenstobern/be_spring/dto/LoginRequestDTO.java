package com.gutenstobern.be_spring.dto;

public class LoginRequestDTO {
    String username;
    String password;

    public LoginRequestDTO() {
    }

    public LoginRequestDTO(String email, String password) {
        this.username = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
