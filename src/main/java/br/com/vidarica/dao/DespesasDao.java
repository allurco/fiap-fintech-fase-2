package br.com.vidarica.dao;

import br.com.vidarica.factories.ConnectionFactory;
import br.com.vidarica.model.Banco;
import br.com.vidarica.model.GastoFixo;
import br.com.vidarica.model.Usuario;
import br.com.vidarica.services.UsuarioService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DespesasDao {
    public Connection connection;
    public PreparedStatement statement;

    public DespesasDao() throws SQLException {
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

    public void cadastrarDespesa(GastoFixo gastoFixo) throws SQLException {
        String sql = "INSERT INTO gastos_fixos (id, nome, valor, usuarios_id, created_at) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
        this.statement = connection.prepareStatement(sql);
        this.statement.setString(1, gastoFixo.getId());
        this.statement.setString(2, gastoFixo.getNome());
        this.statement.setDouble(3, gastoFixo.getValor());
        this.statement.setString(4, gastoFixo.getUsuario().getId());
        this.statement.executeUpdate();
    }

    public GastoFixo buscarDespesaPorId(String value) throws SQLException {
        String sql = "SELECT * FROM GASTOS_FIXOS WHERE id = ?";
        this.statement = connection.prepareStatement(sql);
        this.statement.setString(1, value);
        ResultSet rs = this.statement.executeQuery();

        if (rs.next()) {
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

    public List<GastoFixo> listarDespesas(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM gastos_fixos WHERE usuarios_id = ?";
        this.statement = connection.prepareStatement(sql);
        this.statement.setString(1, usuario.getId());

        ResultSet rs = this.statement.executeQuery();
        if (!rs.isBeforeFirst()) {
            System.out.println("Nenhuma despesa encontrada para o usuário: " + usuario.getNome());
            return null;
        }

        List<GastoFixo> despesas = new ArrayList<>();
        while (rs.next()) {
            GastoFixo despesa = new GastoFixo(rs.getString("id"), rs.getString("nome"), rs.getDouble("valor"), usuario);
            despesas.add(despesa);
        }

        return despesas;
    }

    public double totalDespesas(Usuario usuario) throws SQLException {
        String sql = "SELECT SUM(valor) AS total FROM gastos_fixos WHERE usuarios_id = ?";
        this.statement = connection.prepareStatement(sql);
        this.statement.setString(1, usuario.getId());

        ResultSet rs = this.statement.executeQuery();
        if (rs.next()) {
            return rs.getDouble("total");
        } else {
            return 0;
        }
    }
}
