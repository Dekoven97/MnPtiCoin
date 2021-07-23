package com.example.mnpti.model;

public class Users {

    private String nom,password,prenom,username;

    public Users() {

    }

    public Users(String nom, String password, String prenom, String username) {
        this.nom = nom;
        this.password = password;
        this.prenom = prenom;
        this.username = username;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
