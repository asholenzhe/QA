package org.example.data;

public class AccountData {
    private final String email;
    private final String password;

    public AccountData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
