package br.com.vidarica.model;

import java.util.UUID;

public class Aporte {
    private String id;
    private Double valor;
    private Objetivo objetivo;
    private LongoPrazo longoPrazo;

    public Aporte(double valor, Investimento investimento) {
        this.id = UUID.randomUUID().toString();
        this.valor = valor;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }
    public void setLongoPrazo(LongoPrazo longoPrazo) {
        this.longoPrazo = longoPrazo;
    }

    public String getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public LongoPrazo getLongoPrazo() {
        return longoPrazo;
    }
}
