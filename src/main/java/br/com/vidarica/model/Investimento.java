package br.com.vidarica.model;

public abstract class Investimento {
    private String id;
    private String nome;
    private double valor;
    private Usuario usuario;
    private ContaBancaria contaBancaria;

    public Investimento(String id, String nome, double valor, Usuario usuario, ContaBancaria contaBancaria) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.usuario = usuario;
        this.contaBancaria = contaBancaria;

    }

    public void alterarValor(double valor) {
        this.valor = valor;
        System.out.println("Alterou valor: " + valor );
    }
    public void alterarNome(String nome) {
        this.nome = nome;
        System.out.println("Alterou nome: " + nome );
    }

    public String toString(){
        return "Investimento para: " + this.nome + " para: " + this.usuario.getEmail() + " com o valor mensal de: R$" + this.valor;
    }

    public double getValor() {
        return this.valor;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }
}
