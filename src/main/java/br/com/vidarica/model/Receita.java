package br.com.vidarica.model;

public class Receita {
    private String id;
    private String nome;
    private double valor;
    private double valorImposto;
    private Usuario usuario;

    public Receita(String id, String nome, double valor, double valorImposto, Usuario usuario){
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.valorImposto = valorImposto;
        this.usuario = usuario;
    }

    public void alterarValor (double valor){
        this.valor = valor;
        System.out.println("Valor Alterado Para:" + valor);
    }

    public String toString (){
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

    public double getValorImposto() {
        return valorImposto;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
