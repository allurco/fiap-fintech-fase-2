package br.com.vidarica.services;

import br.com.vidarica.dao.DespesasDao;
import br.com.vidarica.exceptions.UserNotFoundException;
import br.com.vidarica.model.GastoFixo;
import br.com.vidarica.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public class DespesasService {

    public static void registrarDespesa(String nome, Double valor, Usuario usuario) throws SQLException {
        GastoFixo gastoFixo = new GastoFixo(nome, valor, usuario);
        DespesasDao despesasDao = new DespesasDao();
        despesasDao.cadastrarDespesa(gastoFixo);



        System.out.println("Despesa cadastrada com sucesso!");
    }

    public static GastoFixo buscarDespesaPorId(String id) throws SQLException {
        DespesasDao despesasDao = new DespesasDao();
        GastoFixo despesa = despesasDao.buscarDespesaPorId(id);
        if (despesa != null) {
            System.out.println("=== Detalhes da despesa ===");
            System.out.println("ID: " + despesa.getId());
            System.out.println("Nome: " + despesa.getNome());
            System.out.println("valor: " + despesa.getValor());
            despesasDao.close();
        } else {
            System.out.println("Despesa não encontrada.");
        }
        return despesa;
    }

    public static void listarDespesas(Usuario usuario) throws SQLException {
        DespesasDao despesasDao = new DespesasDao();
        List<GastoFixo> despesas = despesasDao.listarDespesas(usuario);
        System.out.println("=== Lista de despesas ===");
        for (GastoFixo despesa : despesas) {
            System.out.println("ID: " + despesa.getId());
            System.out.println("Nome: " + despesa.getNome());
            System.out.println("Valor: " + despesa.getValor());
            System.out.println("-------------------------");
        }
        despesasDao.close();
    }

    public static void totalDespesas(Usuario usuario) throws SQLException {
        DespesasDao despesasDao = new DespesasDao();
        double total = despesasDao.totalDespesas(usuario);
        System.out.println("=== Total de despesas ===");
        System.out.println("Total: " + total);
        despesasDao.close();
    }
}
