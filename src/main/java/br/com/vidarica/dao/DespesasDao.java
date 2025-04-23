package br.com.vidarica.dao;

import br.com.vidarica.exceptions.DespesaDaoException;
import br.com.vidarica.exceptions.UsuarioDaoException;
import br.com.vidarica.factories.ConnectionFactory;
import br.com.vidarica.model.GastoFixo;
import br.com.vidarica.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DespesasDao {

    private final Connection connection;

    public DespesasDao() throws DespesaDaoException {
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            throw new DespesaDaoException(
                    "Erro ao conectar ao banco de dados: " + e.getMessage()
            );
        }
    }

    public void cadastrarDespesa(GastoFixo gastoFixo) throws DespesaDaoException {
        String sql = """
            INSERT INTO gastos_fixos
              (id, nome, valor, usuarios_id, created_at)
            VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)
            """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, gastoFixo.getId());
            stmt.setString(2, gastoFixo.getNome());
            stmt.setDouble(3, gastoFixo.getValor());
            stmt.setString(4, gastoFixo.getUsuario().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DespesaDaoException("Erro ao cadastrar despesa: " + e.getMessage());
        }
    }

    public GastoFixo buscarDespesaPorId(String id) throws DespesaDaoException, UsuarioDaoException {
        String sql = "SELECT * FROM gastos_fixos WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // busca o usuário associado
                    UsuarioDao usuarioDao = new UsuarioDao();
                    Usuario usuario = usuarioDao.getUsuario("id", rs.getString("usuarios_id"));

                    return new GastoFixo(
                            rs.getString("id"),
                            rs.getString("nome"),
                            rs.getDouble("valor"),
                            usuario
                    );
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new DespesaDaoException("Erro ao buscar despesa por ID: " + e.getMessage());
        }
    }

    public List<GastoFixo> listarDespesas(Usuario usuario) throws DespesaDaoException {
        String sql = "SELECT * FROM gastos_fixos WHERE usuarios_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                List<GastoFixo> despesas = new ArrayList<>();
                while (rs.next()) {
                    despesas.add(new GastoFixo(
                            rs.getString("id"),
                            rs.getString("nome"),
                            rs.getDouble("valor"),
                            usuario
                    ));
                }
                return despesas;
            }
        } catch (SQLException e) {
            throw new DespesaDaoException("Erro ao listar despesas: " + e.getMessage());
        }
    }

    public double totalDespesas(Usuario usuario) throws DespesaDaoException {
        String sql = "SELECT SUM(valor) AS total FROM gastos_fixos WHERE usuarios_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getDouble("total") : 0.0;
            }
        } catch (SQLException e) {
            throw new DespesaDaoException("Erro ao calcular total de despesas: " + e.getMessage());
        }
    }

    public void close() throws DespesaDaoException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DespesaDaoException("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}