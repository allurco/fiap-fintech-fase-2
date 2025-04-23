package br.com.vidarica.model;

import java.util.UUID;

public class Objetivo extends Investimento {
//Alterei o a escrita do tipo da variavel valorFinal Double -> double/
    private double valorFinal;
    private String descricao;

    public Objetivo(String nome, String descricao, double valorFinal, double aporte, Usuario usuario, ContaBancaria contasBancarias) {
        super(UUID.randomUUID().toString(), nome, aporte, usuario, contasBancarias);

        this.descricao = descricao;
        this.valorFinal = valorFinal;
    }

    public Objetivo(String id, String nome, String descricao, double valorFinal, double aporte, Usuario usuario, ContaBancaria contasBancarias) {
        super(id, nome, aporte, usuario, contasBancarias);

        this.descricao = descricao;
        this.valorFinal = valorFinal;
    }

    public boolean atingiuValor() {
        return (this.valorFinal <= this.getValor());
    }

    public Double getValorFinal() {
        return valorFinal;
    }
    public String getDescricao() {
        return descricao;
    }


}
