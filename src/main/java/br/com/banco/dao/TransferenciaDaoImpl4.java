package br.com.banco.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
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
import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;

@Repository
public class TransferenciaDaoImpl4 implements TransferenciaDao {

@Override
public void salvar(Transferencia transferencia) {
    String sql = "INSERT INTO transferencia (idTransferencia, contaOrigem, contaDestino, valor, data, tipo) VALUES (?, ?, ?, ?)";
    
    try (Connection con = ConnectionJDBC.abrir();
    	 PreparedStatement pst = con.prepareStatement(sql)) {
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
    	 PreparedStatement pst = con.prepareStatement(sql)) {
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
    	 PreparedStatement pst = con.prepareStatement(sql)) {
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


@Override
public void transfMultipla(Transferencia transferencia) {
	
	Conta conta = new Conta();
	try (Connection con = ConnectionJDBC.abrir();) {
		String tipo = "TED";
		String sql = "INSERT INTO TB_TRANSFERENCIA ( NR_VAOR, FK_ID_CONTA_ORIGEM, FK_ID_CONTA_DESTINO, DT_DATA, TIPO ) VALUES ( ?, ?, ?,?,?);"
				+ "UPDATE TB_CONTA SET NR_SALDO=? WHERE NR_AGENCIA=?  AND NR_NUMERO_CONTA=?;"
				+ "UPDATE TB_CONTA SET NR_SALDO=? WHERE NR_AGENCIA=? AND NR_NUMERO_CONTA=?;";

		PreparedStatement pst = con.prepareStatement(sql);
		pst.setBigDecimal(1, transferencia.getValor());
		pst.setLong(2, transferencia.getIdContaOrigem());
		pst.setLong(3, transferencia.getIdContaDestino());

		pst.setBigDecimal(4, transferencia.getValor());
		pst.setInt(5, conta.getAgencia());
		pst.setInt(6, conta.getNumeroConta());

		pst.setBigDecimal(7, transferencia.getValor());
		pst.setInt(8, conta.getAgencia());
		pst.setInt(9, conta.getNumeroConta());

		pst.setDate(10, Date.valueOf(transferencia.getData().toLocalDate()));
		pst.setString(11, tipo);

		pst.executeUpdate();

		BufferedReader buffReader = new BufferedReader(new FileReader(""));
		// PreparedStatement pst = connection.prepareStatement(sql);

		while (buffReader.ready()) {
			String line = buffReader.readLine();

			String cpf = line.substring(0, 10);
			String AgenciaContaOrigem = line.substring(11, 14);
			String NumeroContaOrigem = line.substring(15, 22);
			String AgenciaContaDestino = line.substring(23, 26);
			String NumeroContaDestino = line.substring(27, 34);
			String valor = line.substring(35, 42);
			String data = line.substring(43, 68);
			String tipoTransferencia = line.substring(69, 69);

			long AgenciaContaOrigem2 = Long.parseLong(AgenciaContaOrigem);
			long NumeroContaOrigem2 = Long.parseLong(NumeroContaOrigem);
			long AgenciaContaDestino2 = Long.parseLong(AgenciaContaDestino);
			long NumeroContaDestino2 = Long.parseLong(NumeroContaDestino);
			BigDecimal valor2 = new BigDecimal(valor);

			if (line.substring(69).equals("D")) {
				pst.setLong(5, AgenciaContaOrigem2);
				pst.setLong(6, NumeroContaOrigem2);
			}

			if (line.substring(69).equals("C")) {
				pst.setLong(8, AgenciaContaDestino2);
				pst.setLong(9, NumeroContaDestino2);
			}

		}
	} catch (SQLException e) {
		throw new RuntimeException(e.getMessage());
	} catch (IOException e) {

		throw new RuntimeException(e.getMessage());
	}

}







}