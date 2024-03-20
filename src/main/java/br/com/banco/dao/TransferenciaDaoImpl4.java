package br.com.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.banco.connection.ConnectionJDBC;
import br.com.banco.models.Transferencia;


public class TransferenciaDaoImpl4 implements TransferenciaDao {

private Connection connection;

public TransferenciaDaoImpl4(Connection connection) {
    this.connection = connection;
}

@Override
public void salvar(Transferencia transferencia) {
    String sql = "INSERT INTO transferencia (idTransferencia, valor, contaOrigem, contaDestino) VALUES (?, ?, ?, ?)";
    
    try (Connection con = ConnectionJDBC.abrir();
    	 PreparedStatement pst = connection.prepareStatement(sql)) {
        pst.setLong(1, transferencia.getIdTransferencia());
        pst.setBigDecimal(2, transferencia.getValor());
        pst.setLong(3, transferencia.getIdContaOrigem());
        pst.setLong(4, transferencia.getIdContaDestino());
        pst.executeUpdate();
        pst.close();
    } catch (SQLException e) {
        e.printStackTrace();
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
            transferencia.setIdTransferencia(rs.getLong("idTransferencia"));
            transferencia.setValor(rs.getBigDecimal("valor"));
            transferencia.setIdContaOrigem(rs.getLong("contaOrigem"));
            transferencia.setIdContaDestino(rs.getLong("contaDestino"));
            transferencias.add(transferencia);
        }
    } catch (SQLException e) {
        e.printStackTrace();
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
            transferencia.setIdTransferencia(rs.getLong("idTransferencia"));
            transferencia.setValor(rs.getBigDecimal("valor"));
            transferencia.setIdContaOrigem(rs.getLong("contaOrigem"));
            transferencia.setIdContaDestino(rs.getLong("contaDestino"));
            return transferencia;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

}