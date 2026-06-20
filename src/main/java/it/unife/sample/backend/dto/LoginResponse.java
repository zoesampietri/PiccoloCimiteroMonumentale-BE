package it.unife.sample.backend.dto;

public class LoginResponse {
    private String mail;
    private String nome;
    private String ruolo;
    private String codiceFiscale;

    public LoginResponse(String mail, String nome, String ruolo, String codiceFiscale) {
        this.mail = mail;
        this.nome = nome;
        this.ruolo = ruolo;
        this.codiceFiscale = codiceFiscale;
    }

    // Getter e Setter
    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getRuolo() { return ruolo; }
    public void setRuolo(String ruolo) { this.ruolo = ruolo; }
    public String getCodiceFiscale() { return codiceFiscale; }
    public void setCodiceFiscale(String codiceFiscale) { this.codiceFiscale = codiceFiscale; }
}
