package br.com.vidarica.model;

import com.password4j.Password;

import java.util.UUID;

public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String password;

    public Usuario(String nome, String email, String password) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.email = email;
        this.password = this.encriptaPassword(password);
    }

    public Usuario(String id, String nome, String email, String password) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

    public String encriptaPassword(String password) {

        String passwordHash = Password.hash(password)
                .withBcrypt()
                .getResult();
        System.out.println("Encriptou a senha do usuario: " + nome);
        return passwordHash;
    }

    public void resetPassword(String password) {
        System.out.println("Resetou a senha");
    }

    public void alteraEmail(String email) {
        System.out.println("Alterou email: " + email);
    }

    public String getEmail() {
        return this.email;
    }

    public void editarPerfil(String email, String password){
        this.alteraEmail(email);
        this.resetPassword(password);

        System.out.println("Editar Perfil: " + this.getEmail());
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return "ID: "+ this.id + "\nNome: " + nome + "\nEmail: " + email + "\nSenha: " + password;
    }
}
