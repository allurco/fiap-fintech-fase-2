package br.com.vidarica.model;

import java.util.UUID;

public class Banco {

    private String id;
    private String nome;
    private String codigo;

    public Banco(String nome, String codigo) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.codigo = codigo;
    }

    public Banco(String id, String nome, String codigo) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }
}
