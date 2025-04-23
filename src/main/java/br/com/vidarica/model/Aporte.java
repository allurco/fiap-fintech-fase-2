package br.com.vidarica.model;

import java.time.LocalDate;
import java.util.UUID;

public class Aporte {
    private String id;
    private Double valor;
    private LocalDate dataDoAporte;
    private Objetivo objetivo;
    private LongoPrazo longoPrazo;

    public Aporte(double valor) {
        this.id = UUID.randomUUID().toString();
        this.dataDoAporte = LocalDate.now();
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

    public LocalDate getDataDoAporte() {
        return dataDoAporte;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public LongoPrazo getLongoPrazo() {
        return longoPrazo;
    }
}
