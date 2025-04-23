package br.com.vidarica.services;

import br.com.vidarica.dao.DespesasDao;
import br.com.vidarica.exceptions.DespesaDaoException;
import br.com.vidarica.exceptions.UsuarioDaoException;
import br.com.vidarica.model.GastoFixo;
import br.com.vidarica.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public class DespesasService {

    public static void registrarDespesa(String nome, Double valor, Usuario usuario) {
        try {
            GastoFixo gastoFixo = new GastoFixo(nome, valor, usuario);
            DespesasDao despesasDao = new DespesasDao();
            despesasDao.cadastrarDespesa(gastoFixo);


            System.out.println("Despesa cadastrada com sucesso!");
        } catch (DespesaDaoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static GastoFixo buscarDespesaPorId(String id) {
        try {
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
        } catch (DespesaDaoException | UsuarioDaoException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static void listarDespesas(Usuario usuario) {
        try {
            DespesasDao despesasDao = new DespesasDao();
            List<GastoFixo> despesas = despesasDao.listarDespesas(usuario);
            System.out.println("=== Lista de despesas ===");
            for (GastoFixo despesa : despesas) {
                System.out.println("ID: " + despesa.getId());
                System.out.println("Nome: " + despesa.getNome());
                System.out.println("Valor: " + despesa.getValor());
                System.out.println("-------------------------");
            }
        } catch (DespesaDaoException e) {
            System.out.println("Usuário não encontrado.");
        }
    }

    public static void totalDespesas(Usuario usuario) {
        try {
            DespesasDao despesasDao = new DespesasDao();
            double total = despesasDao.totalDespesas(usuario);
            System.out.println("=== Total de despesas ===");
            System.out.println("Total: " + total);

        } catch (DespesaDaoException e) {
            System.out.println("Usuário não encontrado.");
        }

    }
}
