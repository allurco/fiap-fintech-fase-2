package br.com.vidarica.services;

import br.com.vidarica.dao.AporteDao;
import br.com.vidarica.dao.InvestimentoDao;
import br.com.vidarica.exceptions.*;
import br.com.vidarica.model.*;

import java.util.List;

public class InvestimentoService {

    public static void criarObjetivo(String nome, String descricao, Double valorFinal, Double valor, Usuario usuario, ContaBancaria contaBancaria) {
        try {
            Objetivo objetivo = new Objetivo(nome, descricao, valorFinal, valor, usuario, contaBancaria);
            InvestimentoDao investimentoDao = new InvestimentoDao();
            investimentoDao.criarObjetivo(objetivo);
            System.out.println("Objetivo criado com sucesso!");
        } catch (InvestimentoDaoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void criarInvestimentoLongoPrazo(String nome, String descricao, Double valor, Usuario usuario, ContaBancaria contaBancaria) {
        try {
            LongoPrazo longoPrazo = new LongoPrazo(nome, descricao, valor, usuario, contaBancaria);
            InvestimentoDao investimentoDao = new InvestimentoDao();
            investimentoDao.criarLongoPrazo(longoPrazo);
            System.out.println("Investimento de longo prazo criado com sucesso!");
        } catch (InvestimentoDaoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void adicionarAporteObjetivo(Double valor, Objetivo objetivo) {
        try {
            AporteDao aporteDao = new AporteDao();
            Aporte aporte = new Aporte(valor);
            aporte.setObjetivo(objetivo);
            aporteDao.criarAporte(aporte);

            InvestimentoDao investimentoDao = new InvestimentoDao();
            investimentoDao.adicionarAporteAoInvestimento(aporte, objetivo.getId());

            System.out.println("Aporte adicionado ao objetivo com sucesso!");
        } catch (AporteDaoException | InvestimentoDaoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void adicionarAporteLongoPrazo(Double valor, LongoPrazo longoPrazo) {
        try {
            AporteDao aporteDao = new AporteDao();
            Aporte aporte = new Aporte(valor);
            aporte.setLongoPrazo(longoPrazo);
            aporteDao.criarAporte(aporte);

            InvestimentoDao investimentoDao = new InvestimentoDao();
            investimentoDao.adicionarAporteAoInvestimento(aporte, longoPrazo.getId());

            System.out.println("Aporte adicionado ao investimento de longo prazo com sucesso!");
        } catch (AporteDaoException | InvestimentoDaoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Objetivo> listarObjetivos(Usuario usuario) {
        try {
            InvestimentoDao investimentoDao = new InvestimentoDao();
            return investimentoDao.listarObjetivos(usuario);
        } catch (InvestimentoDaoException | ContaBancariaDaoException | BancoDaoException | UsuarioDaoException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static List<LongoPrazo> listarLongoPrazo(Usuario usuario) {
        try {
            InvestimentoDao investimentoDao = new InvestimentoDao();
            return investimentoDao.listarLongoPrazo(usuario);
        } catch (InvestimentoDaoException | ContaBancariaDaoException | BancoDaoException | UsuarioDaoException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
