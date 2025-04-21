package br.com.vidarica.model;

import java.util.UUID;

public class LongoPrazo extends Investimento {

    public LongoPrazo(String nome, double aporte, Usuario usuario, ContaBancaria contasBancarias) {
        super(UUID.randomUUID().toString(), nome, aporte, usuario, contasBancarias);
    }

}
