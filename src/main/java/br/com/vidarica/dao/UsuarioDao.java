package br.com.vidarica.dao;

import br.com.vidarica.exceptions.UsuarioDaoException;
import br.com.vidarica.factories.ConnectionFactory;
import br.com.vidarica.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private final Connection connection;

    public UsuarioDao() throws UsuarioDaoException {
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            throw new UsuarioDaoException(
                    "Erro ao conectar ao banco de dados: " + e.getMessage()
            );
        }
    }

    public void cadastrarUsuario(Usuario usuario) throws UsuarioDaoException {
        String sql = "INSERT INTO usuarios (id, nome, email, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getId());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UsuarioDaoException("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    public void editarUsuario(Usuario usuario) throws UsuarioDaoException {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, password = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getPassword());
            stmt.setString(4, usuario.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UsuarioDaoException("Erro ao editar usuário: " + e.getMessage());
        }
    }

    public void deletarUsuario(Usuario usuario) throws UsuarioDaoException {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UsuarioDaoException("Erro ao deletar usuário: " + e.getMessage());
        }
    }

    public Usuario getUsuario(String field, String value) throws UsuarioDaoException {
        String coluna = sanitizeField(field);
        String sql = "SELECT * FROM usuarios WHERE " + coluna + " = ? FETCH NEXT 1 ROWS ONLY";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getString("id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            throw new UsuarioDaoException("Erro ao buscar usuário: " + e.getMessage());
        }
    }

    public int getTotalUsuarios() throws UsuarioDaoException {
        String sql = "SELECT COUNT(*) AS total FROM usuarios";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            return rs.next() ? rs.getInt("total") : 0;
        } catch (SQLException e) {
            throw new UsuarioDaoException("Erro ao contar usuários: " + e.getMessage());
        }
    }

    public List<Usuario> getAllUsuarios(int pageSize, int offset) throws UsuarioDaoException {
        String sql = "SELECT * FROM usuarios ORDER BY nome OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, offset);
            stmt.setInt(2, pageSize);
            try (ResultSet rs = stmt.executeQuery()) {
                List<Usuario> usuarios = new ArrayList<>();
                while (rs.next()) {
                    usuarios.add(new Usuario(
                            rs.getString("id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("password")
                    ));
                }
                return usuarios;
            }
        } catch (SQLException e) {
            throw new UsuarioDaoException("Erro ao listar usuários: " + e.getMessage());
        }
    }

    public void close() throws UsuarioDaoException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new UsuarioDaoException("Erro ao fechar conexão: " + e.getMessage());
        }
    }

    private String sanitizeField(String field) throws UsuarioDaoException {
        List<String> allowed = List.of("id", "nome", "email", "password");
        if (!allowed.contains(field)) {
            throw new UsuarioDaoException("Campo inválido para busca: " + field);
        }
        return field;
    }
}