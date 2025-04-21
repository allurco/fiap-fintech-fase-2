package br.com.vidarica.dao;

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

    public BancoDao() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void close() throws SQLException {
        if (this.statement != null) {
            this.statement.close();
        }
        if (this.connection != null) {
            this.connection.close();
        }
    }

    public void criarBanco(String id, String nome, String tipo) throws SQLException {
        String sql = "INSERT INTO bancos (id, nome, codigo) VALUES (?, ?, ?)";
        this.statement = connection.prepareStatement(sql);
        this.statement.setString(1, id);
        this.statement.setString(2, nome);
        this.statement.setString(3, tipo);
        this.statement.executeUpdate();

    }

    public void editarBanco(String id, String nome, String tipo) throws SQLException {
        String sql = "UPDATE bancos SET nome = ?, codigo = ? WHERE id = ?";
        this.statement = connection.prepareStatement(sql);
        this.statement.setString(1, nome);
        this.statement.setString(2, tipo);
        this.statement.setString(3, id);
        this.statement.executeUpdate();

        this.close();
    }

    public void deletarBanco(String id) throws SQLException {
        String sql = "DELETE FROM bancos WHERE id = ?";
        this.statement = connection.prepareStatement(sql);
        this.statement.setString(1, id);
        this.statement.executeUpdate();

        this.close();
    }

    public List<Banco> listarBancos() throws SQLException {
        String sql = "SELECT * FROM bancos ORDER BY nome";
        this.statement = connection.prepareStatement(sql);
        List<Banco> bacos = new ArrayList<>();

        ResultSet rs = this.statement.executeQuery();

        while (rs.next()) {
            Banco banco = new Banco(rs.getString("id"), rs.getString("nome"), rs.getString("codigo"));
            bacos.add(banco);
        }

        this.close();

        return bacos;
    }

    public Banco buscarBanco(String field, String value) throws SQLException {
        String sql = "SELECT * FROM bancos WHERE " + field + " = ?";
        this.statement = connection.prepareStatement(sql);
        this.statement.setString(1, field);
        this.statement.setString(2, value);
        ResultSet rs = this.statement.executeQuery();

        if (rs.next()) {
            return new Banco(rs.getString("nome"), rs.getString("codigo"));
        } else {
            return null;
        }
    }

    public Banco buscarBancoPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM bancos WHERE nome LIKE ? FETCH NEXT 1 ROWS ONLY";
        this.statement = connection.prepareStatement(sql);
        this.statement.setString(1, "%" + nome + "%");
        ResultSet rs = this.statement.executeQuery();

        if (rs.next()) {
            return new Banco(rs.getString("nome"), rs.getString("codigo"));
        } else {
            return null;
        }
    }
}
