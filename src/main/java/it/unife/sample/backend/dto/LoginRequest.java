package it.unife.sample.backend.dto;

public class LoginRequest {
    private String mail;
    private String password;

    public LoginRequest() {}

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password;}
}
