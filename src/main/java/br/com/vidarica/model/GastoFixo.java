package br.com.vidarica.model;

public class GastoFixo {
    private String id;
    private String nome;
    private double valor;
    private Usuario usuario;

    public GastoFixo(String id, String nome, double valor, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.usuario = usuario;
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

    public void setValor(double valor) {
        if (valor <= 0) {
            System.out.println("Valor não pode ser negativo");
        }

        this.valor = valor;
        System.out.println("Valor alterado Para:" + valor);
    }



    public String toString() {
        return "ID: " + id + "\nNome: " + nome + "\nValor: " + valor;
    }
}
