package br.com.vidarica.dao;

import br.com.vidarica.exceptions.BancoDaoException;
import br.com.vidarica.exceptions.ContaBancariaDaoException;
import br.com.vidarica.exceptions.InvestimentoDaoException;
import br.com.vidarica.exceptions.UsuarioDaoException;
import br.com.vidarica.factories.ConnectionFactory;
import br.com.vidarica.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvestimentoDao {

    public Connection connection;
    public PreparedStatement statement;

    public InvestimentoDao() throws InvestimentoDaoException {
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            throw new InvestimentoDaoException(
                    "Erro ao conectar ao banco de dados: " + e.getMessage()
            );
        }
    }

    public void criarObjetivo(Objetivo objetivo) throws InvestimentoDaoException {

        String sqlInvestimentos = "INSERT INTO investimento (id, nome, valor, usuarios_id, contas_bancarias_id, created_at) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

        String sql = "INSERT INTO objetivos (descricao, valor_final, investimento_id) VALUES (?, ?, ?)";

        try {

            this.statement = connection.prepareStatement(sqlInvestimentos);
            this.statement.setString(1, objetivo.getId());
            this.statement.setString(2, objetivo.getNome());
            this.statement.setDouble(3, objetivo.getValor());
            this.statement.setString(4, objetivo.getUsuario().getId());
            this.statement.setString(5, objetivo.getContaBancaria().getId());
            this.statement.executeUpdate();

            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, objetivo.getDescricao());
            this.statement.setDouble(2, objetivo.getValorFinal());
            this.statement.setString(3, objetivo.getId());
            this.statement.executeUpdate();
        } catch (SQLException e) {
            throw new InvestimentoDaoException("Erro ao criar objetivo: " + e.getMessage());
        }
    }

    public void criarLongoPrazo(LongoPrazo longoPrazo) throws InvestimentoDaoException {

        String sqlInvestimentos = "INSERT INTO investimento (id, nome, valor, usuarios_id, contas_bancarias_id, created_at) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

        String sql = "INSERT INTO longo_prazo (descricao, investimento_id) VALUES (?, ?)";

        try {

            this.statement = connection.prepareStatement(sqlInvestimentos);
            this.statement.setString(1, longoPrazo.getId());
            this.statement.setString(2, longoPrazo.getNome());
            this.statement.setDouble(3, longoPrazo.getValor());
            this.statement.setString(4, longoPrazo.getUsuario().getId());
            this.statement.setString(5, longoPrazo.getContaBancaria().getId());
            this.statement.executeUpdate();

            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, longoPrazo.getDescricao());
            this.statement.setString(2, longoPrazo.getId());
            this.statement.executeUpdate();
        } catch (SQLException e) {
            throw new InvestimentoDaoException("Erro ao criar objetivo: " + e.getMessage());
        }
    }

    public void adicionarAporteAoInvestimento(Aporte aporte, String investimentoId) throws InvestimentoDaoException {
        String selectInvestimento = "SELECT valor FROM investimento WHERE id = ?";

        try {
            this.statement = connection.prepareStatement(selectInvestimento);
            this.statement.setString(1, investimentoId);
            this.statement.executeQuery();

            ResultSet rs = this.statement.getResultSet();

            while (rs.next()) {
                double valorAtual = rs.getDouble("valor");
                double novoValor = valorAtual + aporte.getValor();

                String updateInvestimento = "UPDATE investimento SET valor = ? WHERE id = ?";
                this.statement = connection.prepareStatement(updateInvestimento);
                this.statement.setDouble(1, novoValor);
                this.statement.setString(2, investimentoId);
                this.statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new InvestimentoDaoException("Erro ao adicionar aporte ao investimento: " + e.getMessage());
        } finally {
            this.close();
        }
    }

    public List<Objetivo> listarObjetivos(Usuario usuario) throws InvestimentoDaoException, ContaBancariaDaoException, BancoDaoException, UsuarioDaoException {
        String sql = "SELECT investimento.id, investimento.nome, investimento.valor, objetivos.descricao, objetivos.valor_final, investimento.contas_bancarias_id FROM objetivos LEFT JOIN investimento ON objetivos.investimento_id = investimento.id WHERE investimento.usuarios_id = ?";
        List<Objetivo> objetivos = new ArrayList<>();

        try {
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, usuario.getId());
            ResultSet rs = this.statement.executeQuery();

            while (rs.next()) {
                ContaBancariaDao contaBancariaDao = new ContaBancariaDao();
                ContaBancaria contaBancaria = contaBancariaDao.buscarContaBancaria("id", rs.getString("contas_bancarias_id"));
                Objetivo objetivo = new Objetivo(
                        rs.getString("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("valor_final"),
                        rs.getDouble("valor"),
                        usuario,
                        contaBancaria

                );
                objetivos.add(objetivo);
            }
        } catch (SQLException e) {
            throw new InvestimentoDaoException("Erro ao listar objetivos: " + e.getMessage());
        }
        return objetivos;
    }

    public List<LongoPrazo> listarLongoPrazo(Usuario usuario) throws InvestimentoDaoException, ContaBancariaDaoException, BancoDaoException, UsuarioDaoException {
        String sql = "SELECT investimento.nome FROM longo_prazo LEFT JOIN investimento ON longo_prazo.INVESTIMENTO_ID = investimento.ID WHERE investimento.usuarios_id = ?";
        List<LongoPrazo> longoPrazos = new ArrayList<>();

        try {
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, usuario.getId());
            ResultSet rs = this.statement.executeQuery();

            while (rs.next()) {
                ContaBancariaDao contaBancariaDao = new ContaBancariaDao();
                ContaBancaria contaBancaria = contaBancariaDao.buscarContaBancaria("id", rs.getString("contas_bancarias_id"));
                LongoPrazo LongoPrazo = new LongoPrazo(
                        rs.getString("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        usuario,
                        contaBancaria

                );
                longoPrazos.add(LongoPrazo);
            }
        } catch (SQLException e) {
            throw new InvestimentoDaoException("Erro ao listar objetivos: " + e.getMessage());
        }
        return longoPrazos;
    }

    public void close() throws InvestimentoDaoException {
        try {
            if (this.statement != null) {
                this.statement.close();

                if (this.connection != null) {
                    this.connection.close();
                }
            }

        } catch (SQLException e) {
            throw new InvestimentoDaoException("Erro ao fechar conexão: " + e.getMessage());
        }
    }

}
