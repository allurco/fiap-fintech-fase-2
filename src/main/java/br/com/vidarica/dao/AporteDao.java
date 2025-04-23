// File: src/main/java/br/com/vidarica/dao/AporteDao.java
package br.com.vidarica.dao;

import br.com.vidarica.exceptions.AporteDaoException;
import br.com.vidarica.factories.ConnectionFactory;
import br.com.vidarica.model.Aporte;
import br.com.vidarica.model.Objetivo;
import br.com.vidarica.model.LongoPrazo;
import br.com.vidarica.model.Investimento;
import br.com.vidarica.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class AporteDao {
    public Connection connection;
    public PreparedStatement statement;

    public AporteDao() throws AporteDaoException {
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            throw new AporteDaoException("Error loading driver: " + e.getMessage());
        }
    }

    public void close() throws AporteDaoException {
        try {
            if (this.statement != null) {
                this.statement.close();

                if (this.connection != null) {
                    this.connection.close();
                }
            }

        } catch (SQLException e) {
            throw new AporteDaoException("Error closing connection: " + e.getMessage());
        }
    }

    public void criarAporte(Aporte aporte) throws AporteDaoException {
        try {
            String sql = "INSERT INTO aportes (id, valor, investimento_id, data_do_aporte) "
                    + "VALUES (?, ?, ?, ?)";
            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, aporte.getId());
            this.statement.setDouble(2, aporte.getValor());

            if (aporte.getObjetivo() != null) {
                this.statement.setString(3, aporte.getObjetivo().getId());
            } else {
                this.statement.setString(3, aporte.getLongoPrazo().getId());
            }

            this.statement.setDate(4, java.sql.Date.valueOf(aporte.getDataDoAporte()));

            this.statement.executeUpdate();
        } catch (SQLException e) {
            throw new AporteDaoException("Error creating aporte: " + e.getMessage());
        } finally {
            this.close();
        }
    }

    // New method to list aportes by Usuario and Investimento.
    /*public List<Aporte> listarAportesPorUsuarioAndInvestimento(Usuario usuario, Investimento investimento) throws AporteDaoException {
        List<Aporte> aportes = new ArrayList<>();
        try {
            String sql = "";
            boolean isObjetivo = investimento instanceof Objetivo;
            if (isObjetivo) {
                sql = "SELECT a.id, a.valor, a.objetivo_id, a.longo_prazo_id, a.created_at, a.updated_at " +
                        "FROM aportes a " +
                        "JOIN objetivos o ON a.objetivo_id = o.id " +
                        "WHERE o.usuarios_id = ? AND o.id = ?";
            } else {
                sql = "SELECT a.id, a.valor, a.objetivo_id, a.longo_prazo_id, a.created_at, a.updated_at " +
                        "FROM aportes a " +
                        "JOIN longo_prazos lp ON a.longo_prazo_id = lp.id " +
                        "WHERE lp.usuarios_id = ? AND lp.id = ?";
            }
            this.statement = this.connection.prepareStatement(sql);
            this.statement.setString(1, usuario.getId());
            this.statement.setString(2, investimento.getId());
            ResultSet rs = this.statement.executeQuery();
            while (rs.next()) {
                Aporte aporte = new Aporte(rs.getDouble("valor"), investimento);
                if (isObjetivo) {
                    aporte.setObjetivo((Objetivo) investimento);
                } else {
                    aporte.setLongoPrazo((LongoPrazo) investimento);
                }
                aportes.add(aporte);
            }
        } catch (SQLException e) {
            throw new AporteDaoException("Error listing aportes: " + e.getMessage());
        } finally {
            this.close();
        }
        return aportes;
    }*/
}