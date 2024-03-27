package br.com.banco.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.banco.connection.ConnectionJDBC;
import br.com.banco.models.Transferencia;

@Repository
public class TransferenciaDaoImpl4 implements TransferenciaDao {

private Connection connection;

public TransferenciaDaoImpl4(Connection connection) {
    this.connection = connection;
}

@Override
public void salvar(Transferencia transferencia) {
    String sql = "INSERT INTO transferencia (idTransferencia, contaOrigem, contaDestino, valor, data, tipo) VALUES (?, ?, ?, ?)";
    
    try (Connection con = ConnectionJDBC.abrir();
    	 PreparedStatement pst = connection.prepareStatement(sql)) {
        pst.setLong(1, transferencia.getIdTransferencia());
        pst.setLong(3, transferencia.getIdContaOrigem());
        pst.setLong(4, transferencia.getIdContaDestino());
        pst.setBigDecimal(2, transferencia.getValor());
        pst.setDate(5, Date.valueOf(transferencia.getData().toLocalDate()));
        pst.setString(6, transferencia.getTipo());
        pst.executeUpdate();
        pst.close();
    } catch (SQLException e) {
    	throw new RuntimeException("Erro ao salvar a transferência: " + e.getMessage());
    }
}

@Override
public List<Transferencia> listarTodos() {
    List<Transferencia> transferencias = new ArrayList<>();
    String sql = "SELECT * FROM transferencia";
    
    try (Connection con = ConnectionJDBC.abrir();
    	 PreparedStatement pst = connection.prepareStatement(sql)) {
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Transferencia transferencia = new Transferencia();
            transferencia.setIdTransferencia(rs.getLong("pk_id_Transferencia"));
            transferencia.setIdContaOrigem(rs.getLong("fk_id_contaOrigem"));
            transferencia.setIdContaDestino(rs.getLong("fk_id_contaDestino"));
            transferencia.setValor(rs.getBigDecimal("nr_valor"));
            LocalDateTime local = rs.getTimestamp("dt_data").toLocalDateTime();
            transferencia.setData(local);
            transferencia.setTipo(rs.getString("tipo"));
            transferencias.add(transferencia);
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao listar todas as transferências: " + e.getMessage());
    }
    return transferencias;
}

@Override
public Transferencia retornarPorID(long idTransferencia) {
    String sql = "SELECT * FROM transferencia WHERE idTransferencia = ?";
    try (Connection con = ConnectionJDBC.abrir();
    	 PreparedStatement pst = connection.prepareStatement(sql)) {
        pst.setLong(1, idTransferencia);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            Transferencia transferencia = new Transferencia();
            transferencia.setIdTransferencia(rs.getLong("pk_id_Transferencia"));
            transferencia.setIdContaOrigem(rs.getLong("fk_id_contaOrigem"));
            transferencia.setIdContaDestino(rs.getLong("fk_id_contaDestino"));
            transferencia.setValor(rs.getBigDecimal("nr_valor"));
            LocalDateTime local = (rs.getTimestamp("dt_data").toLocalDateTime());
            transferencia.setData(local);
            transferencia.setTipo(rs.getString("tipo"));
            return transferencia;
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao retornar transferência por ID: " + e.getMessage());
    }
    return null;
}

}