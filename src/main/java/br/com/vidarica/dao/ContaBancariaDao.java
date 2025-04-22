package br.com.vidarica.dao;

import br.com.vidarica.factories.ConnectionFactory;
import br.com.vidarica.model.Banco;
import br.com.vidarica.model.ContaBancaria;
import br.com.vidarica.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaBancariaDao {
    public Connection connection;
    public PreparedStatement statement;

    public ContaBancariaDao() throws SQLException {
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

    public void criarContaBancaria(ContaBancaria contaBancaria) throws SQLException {
        String sql = "INSERT INTO contas_bancarias (id, nome, tipo, usuarios_id, agencia, conta, digito_conta, created_at, updated_at, bancos_id) VALUES (?, ?, ?, ?, ?, ?, ?,  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";
        this.statement = connection.prepareStatement(sql);
        this.statement.setString(1, contaBancaria.getId());
        this.statement.setString(2, contaBancaria.getNome());
        this.statement.setString(3, contaBancaria.getTipo());
        this.statement.setString(4, contaBancaria.getUsuario().getId());
        this.statement.setString(5, contaBancaria.getAgencia());
        this.statement.setString(6, contaBancaria.getConta());
        this.statement.setInt(7, contaBancaria.getDigitoConta());
        this.statement.setString(8, contaBancaria.getBanco().getId());

        this.statement.executeUpdate();

    }

    public List<ContaBancaria> listarContas(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM contas_bancarias WHERE usuarios_id = ?";
        this.statement = connection.prepareStatement(sql);
        this.statement.setString(1, usuario.getId());
        ResultSet rs = this.statement.executeQuery();
        if (!rs.isBeforeFirst()) {
            System.out.println("Nenhuma conta bancária encontrada para o usuário: " + usuario.getNome());
            return null;
        }

        List<ContaBancaria> contas = new ArrayList<>();

        while (rs.next()) {
            BancoDao bancoDao = new BancoDao();
            Banco banco = bancoDao.buscarBanco("id", rs.getString("bancos_id"));
            ContaBancaria conta = new ContaBancaria(
                    rs.getString("id"),
                    rs.getString("nome"),
                    rs.getString("tipo"),
                    usuario,
                    rs.getString("conta"),
                    rs.getInt("digito_conta"),
                    rs.getString("agencia"),
                    rs.getInt("digito_agencia"),
                    banco
            );

            contas.add(conta);
        }

        return contas;


    }
}
