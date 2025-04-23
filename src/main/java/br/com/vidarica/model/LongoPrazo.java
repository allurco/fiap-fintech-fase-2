package br.com.vidarica.model;

import java.util.UUID;

public class LongoPrazo extends Investimento {

    private String descricao;

    public LongoPrazo(String nome, String descricao, double aporte, Usuario usuario, ContaBancaria contasBancarias) {
        super(UUID.randomUUID().toString(), nome, aporte, usuario, contasBancarias);

        this.descricao = descricao;
    }

    public LongoPrazo(String id, String nome, String descricao, double aporte, Usuario usuario, ContaBancaria contasBancarias) {
        super(id, nome, aporte, usuario, contasBancarias);

        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
