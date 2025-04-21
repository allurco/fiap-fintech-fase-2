package br.com.vidarica.model;

import java.util.UUID;

public class ContaBancaria {
    private String id;
    private String nome;
    private String tipo;
    private Usuario usuario;
    private String conta;
    private int digitoConta;
    private String agencia;
    private int digitoAgencia;
    private Banco banco;

    public ContaBancaria(String nome, String tipo, Usuario usuario, String conta, int digitoConta, String agencia, int digitoAgencia, Banco banco) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.tipo = tipo;
        this.usuario = usuario;
        this.conta = conta;
        this.digitoConta = digitoConta;
        this.agencia = agencia;
        this.digitoAgencia = digitoAgencia;
        this.banco = banco;
    }

    public ContaBancaria(String id, String nome, String tipo, Usuario usuario, String conta, int digitoConta, String agencia, int digitoAgencia, Banco banco) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.usuario = usuario;
        this.conta = conta;
        this.digitoConta = digitoConta;
        this.agencia = agencia;
        this.digitoAgencia = digitoAgencia;
        this.banco = banco;
    }



    public void alterarNome(String nome) {
        this.nome = nome;
        System.out.println("Alterou nome: " + nome);
    }

    public void tipo(String tipo) {
        this.tipo = tipo;
        System.out.println("Alterou tipo: " + tipo);
    }
    public void alterarAgencia(String agencia) {
        this.agencia = agencia;
        System.out.println("Alterou agencia: " + agencia);
    }

    public String toString(){
        return "Email: " + usuario.getEmail() + ", Nome: " + this.nome + ", Banco: " + banco.getNome() + ", Agencia: " + this.agencia + ", Digito Agência: " + this.digitoAgencia + ", Conta: " + this.conta;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getConta() {
        return conta;
    }

    public int getDigitoConta() {
        return digitoConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public int getDigitoAgencia() {
        return digitoAgencia;
    }

    public Banco getBanco() {
        return banco;
    }
}
