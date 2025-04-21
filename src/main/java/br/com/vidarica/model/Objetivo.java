package br.com.vidarica.model;

import java.util.UUID;

public class Objetivo extends Investimento {
//Alterei o a escrita do tipo da variavel valorFinal Double -> double/
    private double valorFinal;

    public Objetivo(String nome, double valorFinal, double aporte, Usuario usuario, ContaBancaria contasBancarias) {
        super(UUID.randomUUID().toString(), nome, aporte, usuario, contasBancarias);

        this.valorFinal = valorFinal;
    }

    public boolean atingiuValor() {
        return (this.valorFinal <= this.getValor());
    }

    public Double getValorFinal() {
        return valorFinal;
    }


}
