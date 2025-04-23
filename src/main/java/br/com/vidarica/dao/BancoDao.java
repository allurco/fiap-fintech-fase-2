package br.com.vidarica.dao;

import br.com.vidarica.exceptions.BancoDaoException;
import br.com.vidarica.factories.ConnectionFactory;
import br.com.vidarica.model.Banco;
import br.com.vidarica.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BancoDao {
    public Connection connection;
    public PreparedStatement statement;

    public BancoDao() throws BancoDaoException {
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            throw new BancoDaoException("Erro ao carregar o driver: " + e.getMessage());
        }

    }

    public void close() throws BancoDaoException {
        try {
            if (this.statement != null) {
                this.statement.close();

                if (this.connection != null) {
                    this.connection.close();
                }
            }

        } catch (SQLException e) {
            throw new BancoDaoException("Erro ao fechar conexão: " + e.getMessage());
        }

    }

    public void criarBanco(String id, String nome, String codigo) throws BancoDaoException {
        try {
            String sql = "INSERT INTO bancos (id, nome, codigo) VALUES (?, ?, ?)";
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, id);
            this.statement.setString(2, nome);
            this.statement.setString(3, codigo);
            this.statement.executeUpdate();

        } catch (SQLException e) {
            throw new BancoDaoException("Erro ao cadastrar banco: " + e.getMessage());
        }

    }

    public void editarBanco(String id, String nome, String tipo) throws BancoDaoException {
        try {
            String sql = "UPDATE bancos SET nome = ?, codigo = ? WHERE id = ?";
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, nome);
            this.statement.setString(2, tipo);
            this.statement.setString(3, id);
            this.statement.executeUpdate();

        } catch (SQLException e) {
            throw new BancoDaoException("Erro ao editar banco: " + e.getMessage());
        } finally {
            this.close();
        }

    }

    public void deletarBanco(String id) throws BancoDaoException {
        try {
            String sql = "DELETE FROM bancos WHERE id = ?";
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, id);
            this.statement.executeUpdate();

        } catch (SQLException e) {
            throw new BancoDaoException("Erro ao deletar banco: " + e.getMessage());
        } finally {
            this.close();
        }
    }

    public List<Banco> listarBancos() throws BancoDaoException {
        try {
            String sql = "SELECT * FROM bancos ORDER BY nome";
            this.statement = connection.prepareStatement(sql);
            List<Banco> bancos = new ArrayList<>();

            ResultSet rs = this.statement.executeQuery();

            while (rs.next()) {
                Banco banco = new Banco(rs.getString("id"), rs.getString("nome"), rs.getString("codigo"));
                bancos.add(banco);
            }


            return bancos;
        } catch (SQLException e) {
            throw new BancoDaoException("Erro ao listar bancos: " + e.getMessage());
        } finally {
            this.close();
        }
    }

    public Banco buscarBanco(String field, String value) throws BancoDaoException {
        try {
            String sql = "SELECT * FROM bancos WHERE " + field + " = ?";
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, value);
            ResultSet rs = this.statement.executeQuery();

            if (rs.next()) {
                return new Banco(rs.getString("nome"), rs.getString("codigo"));
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new BancoDaoException("Erro ao buscar banco: " + e.getMessage());
        } finally {
            this.close();
        }
    }

    public Banco buscarBancoPorNome(String nome) throws BancoDaoException {
        try {
            String sql = "SELECT * FROM bancos WHERE nome LIKE ? FETCH NEXT 1 ROWS ONLY";
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, "%" + nome + "%");
            ResultSet rs = this.statement.executeQuery();

            if (rs.next()) {
                return new Banco(rs.getString("nome"), rs.getString("codigo"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new BancoDaoException("Erro ao buscar banco por nome: " + e.getMessage());
        } finally {
            this.close();
        }

    }
}
