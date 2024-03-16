package org.example;

public class Credentials {
    String email;
    private String password;
    Credentials(){}
    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Credentials setEmail(String email) {
        this.email = email;
        return this;
    }

    public Credentials setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
