package br.com.vidarica.model;

public class Patrimonio {
    private String id;
    private String nome;
    private double valor;
    private Usuario usuario;
    private String tipo;

    public Patrimonio(String id, String nome, double valor, Usuario usuario, String tipo) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.usuario = usuario;
        this.tipo = tipo;

    }
    public void alterarValor(double valor) {
        this.valor = valor;
        System.out.println("Alterando valor: " + valor);
    }

    public String toString() {
        return "ID: " + id + "\nNome: " + nome + "\nValor: " + valor;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getTipo() {
        return tipo;
    }
}
