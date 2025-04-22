package br.com.vidarica.services;

import br.com.vidarica.dao.BancoDao;
import br.com.vidarica.dao.ContaBancariaDao;
import br.com.vidarica.model.Banco;
import br.com.vidarica.model.ContaBancaria;
import br.com.vidarica.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public class BancosService {

    public static void adicionarBanco(String nome, String codigo) {

        Banco banco = new Banco(nome, codigo);

        try {
            BancoDao bancoDao = new BancoDao();
            bancoDao.criarBanco(banco.getId(), banco.getNome(), banco.getCodigo());

            bancoDao.buscarBanco("id", banco.getId());

            System.out.println("Banco cadastrado com sucesso!");
            System.out.println("Nome: " + banco.getNome());
            System.out.println("Código: " + banco.getCodigo());
            bancoDao.close();
        } catch (Exception $e) {
            System.out.println("Erro ao cadastrar banco: " + $e.getMessage());
        }

    }

    public static List<Banco> listarBancos() {
        try {
            BancoDao bancoDao = new BancoDao();
            return bancoDao.listarBancos();
        } catch (SQLException e) {
            System.out.println("Erro ao listar bancos: " + e.getMessage());
        }
        return null;
    }

    public static void procurarBanco(String termo) {

        try {
            BancoDao bancoDao = new BancoDao();
            Banco banco = bancoDao.buscarBancoPorNome(termo);
            if (banco != null) {
                System.out.println("Banco encontrado:");
                System.out.println("ID: " + banco.getId());
                System.out.println("Nome: " + banco.getNome());
                System.out.println("Código: " + banco.getCodigo());
            } else {
                System.out.println("Banco não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Banco não encontrado: "+e.getMessage());
        }

    }

    public static void criarContaBancaria(Usuario usuario, Banco banco, String nome, String Tipo, String agencia, Integer DigitoAgencia, String conta, int DigitoConta) throws SQLException {

        ContaBancaria contaBancaria = new ContaBancaria(nome, Tipo, usuario, conta, DigitoConta, agencia, DigitoAgencia, banco);
        ContaBancariaDao contaBancariaDao = new ContaBancariaDao();
        contaBancariaDao.criarContaBancaria(contaBancaria);
        System.out.println("Conta bancária criada com sucesso!");

    }

    public static List<ContaBancaria> listarContasBancarias(Usuario usuario) throws SQLException {
        ContaBancariaDao contaBancariaDao = new ContaBancariaDao();
        List<ContaBancaria> contas = contaBancariaDao.listarContas(usuario);
        if (contas != null) {
            System.out.println("=== Lista de Contas Bancárias ===");
            for (ContaBancaria conta : contas) {
                System.out.println("ID: " + conta.getId());
                System.out.println("Nome: " + conta.getNome());
                System.out.println("Banco: " + conta.getBanco().getNome());
                System.out.println("Agência: " + conta.getAgencia());
                System.out.println("Conta: " + conta.getConta());
                System.out.println("-------------------------");
            }
        } else {
            System.out.println("Nenhuma conta bancária encontrada.");
        }
        return contas;
    }
}
