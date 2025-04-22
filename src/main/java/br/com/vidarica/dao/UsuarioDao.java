package br.com.vidarica.dao;

import br.com.vidarica.exceptions.UsuarioDaoException;
import br.com.vidarica.factories.ConnectionFactory;
import br.com.vidarica.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    public Connection connection;
    public PreparedStatement statement;

    public UsuarioDao() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void cadastrarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (id, nome, email, password) VALUES (?, ?, ?, ?)";

        this.statement = connection.prepareStatement(sql);
        this.statement.setString(1, usuario.getId());
        this.statement.setString(2, usuario.getNome());
        this.statement.setString(3, usuario.getEmail());
        this.statement.setString(4, usuario.getPassword());

        this.statement.executeUpdate();

    }

    public void editarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, password = ? WHERE id = ?";

        this.statement = connection.prepareStatement(sql);
        this.statement.setString(1, usuario.getNome());
        this.statement.setString(2, usuario.getEmail());
        this.statement.setString(3, usuario.getPassword());
        this.statement.setString(4, usuario.getId());

        this.statement.executeUpdate();

    }

    public void deletarUsuario(Usuario usuario) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        this.statement = connection.prepareStatement(sql);
        this.statement.setString(1, usuario.getId());

        this.statement.executeUpdate();

    }

    public Usuario getUsuario(String field, String value) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE " + sanitizeField(field) + " = ? FETCH NEXT 1 ROWS ONLY";

        this.statement = connection.prepareStatement(sql);
        this.statement.setString(1, value);

        ResultSet rs = this.statement.executeQuery();

        if (rs.next()) {
            String idUsuario = rs.getString("id");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String password = rs.getString("password");

            return new Usuario(idUsuario, nome, email, password);
        }

        return null;
    }

    public int getTotalUsuarios() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM usuarios";
        this.statement = connection.prepareStatement(sql);
        ResultSet rs = this.statement.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        }
        return 0;
    }

    public List<Usuario> getAllUsuarios(int pageSize, int offset) throws UsuarioDaoException {
        try {
            String sql = "SELECT * FROM usuarios ORDER BY nome OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            this.statement = connection.prepareStatement(sql);
            this.statement.setInt(1, offset);
            this.statement.setInt(2, pageSize);
            List<Usuario> usuarios = new ArrayList<>();

            ResultSet rs = this.statement.executeQuery();

            while (rs.next()) {
                String idUsuario = rs.getString("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String password = rs.getString("password");

                usuarios.add(new Usuario(idUsuario, nome, email, password));
            }

            return usuarios;
        } catch (SQLException e) {
            throw new UsuarioDaoException(e.getMessage());
        }
    }

    public void close() throws SQLException {
        if (this.statement != null) {
            this.statement.close();
        }
        if (this.connection != null) {
            this.connection.close();
        }
    }

    private String sanitizeField(String field) throws IllegalArgumentException {
        // List of allowed fields to prevent SQL injection
        List<String> allowedFields = List.of("id", "nome", "email", "password");
        if (allowedFields.contains(field)) {
            return field;
        } else {
            throw new IllegalArgumentException("Invalid field: " + field);
        }
    }
}
