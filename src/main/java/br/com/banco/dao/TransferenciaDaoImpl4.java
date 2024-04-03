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
import java.time.format.DateTimeFormatter;
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
		String sql = "INSERT INTO TB_TRANSFERENCIA (PK_ID_TRANSFERENCIA, FK_ID_CONTA_ORIGEM, FK_ID_CONTA_DESTINO, NR_VALOR, DT_DATA, TIPO) VALUES (?, ?, ?, ?)";

		try (Connection con = ConnectionJDBC.abrir(); PreparedStatement pst = con.prepareStatement(sql)) {
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
		String sql = "SELECT * FROM tb_transferencia";

		try (Connection con = ConnectionJDBC.abrir(); PreparedStatement pst = con.prepareStatement(sql)) {
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Transferencia transferencia = new Transferencia();
				transferencia.setIdTransferencia(rs.getLong("PK_ID_TRANSFERENCIA"));
				transferencia.setIdContaOrigem(rs.getLong("FK_ID_CONTA_ORIGEM"));
				transferencia.setIdContaDestino(rs.getLong("FK_ID_CONTA_DESTINO"));
				transferencia.setValor(rs.getBigDecimal("NR_VALOR"));
				LocalDateTime local = rs.getTimestamp("DT_DATA").toLocalDateTime();
				transferencia.setData(local);
				transferencia.setTipo(rs.getString("TIPO"));
				transferencias.add(transferencia);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar todas as transferências: " + e.getMessage());
		}
		return transferencias;
	}

	@Override
	public Transferencia retornarPorID(long idTransferencia) {
		String sql = "SELECT * FROM TB_TRANSFERENCIA WHERE PK_ID_TRANSFERENCIA = ?";
		try (Connection con = ConnectionJDBC.abrir(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, idTransferencia);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Transferencia transferencia = new Transferencia();
				transferencia.setIdTransferencia(rs.getLong("PK_ID_TRANSFERENCIA"));
				transferencia.setIdContaOrigem(rs.getLong("FK_ID_CONTA_ORIGEM"));
				transferencia.setIdContaDestino(rs.getLong("FK_ID_CONTA_GIT DESTINO"));
				transferencia.setValor(rs.getBigDecimal("NR_VALOR"));
				LocalDateTime local = (rs.getTimestamp("DT_DATA").toLocalDateTime());
				transferencia.setData(local);
				transferencia.setTipo(rs.getString("TIPO"));
				return transferencia;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao retornar transferência por ID: " + e.getMessage());
		}
		return null;
	}

	@Override
	public void transfMultipla() {

		Conta conta = new Conta();
		try (Connection con = ConnectionJDBC.abrir();) {

			BufferedReader buffReader = new BufferedReader(new FileReader("C:\\Users\\vmontezani\\Documents\\Projetos\\ProjetoEquipe1\\TransferenciasGeradas.txt"));
			// PreparedStatement pst = connection.prepareStatement(sql);

			while (buffReader.ready()) {
				String line = buffReader.readLine();

				String cpf = line.substring(0, 11);
				String agenciaContaOrigem = line.substring(11, 15);
				String numeroContaOrigem = line.substring(15, 23);
				String agenciaContaDestino = line.substring(23, 27);
				String numeroContaDestino = line.substring(27, 35);
				String valor = line.substring(35, 43);
				String data = line.substring(43, 62);
				

				int agenciaContaOrigem2 = Integer.parseInt(agenciaContaOrigem);
				int numeroContaOrigem2 = Integer.parseInt(numeroContaOrigem);
				int agenciaContaDestino2 = Integer.parseInt(agenciaContaDestino);
				int numeroContaDestino2 = Integer.parseInt(numeroContaDestino);
				BigDecimal valor2 = new BigDecimal(valor).divide(BigDecimal.valueOf(100));
				LocalDateTime date = LocalDateTime.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));

				if(!verificacaoSaldo(agenciaContaOrigem2,numeroContaOrigem2,valor2)) {
					continue;
				}
				
				
				
				String tipo = "TED";
				String sql = "INSERT INTO TB_TRANSFERENCIA ( NR_VAOR, FK_ID_CONTA_ORIGEM, FK_ID_CONTA_DESTINO, DT_DATA, TIPO ) VALUES ( ?, ?, ?,?,?);"
						+ "UPDATE TB_CONTA SET NR_SALDO= NR_SALDO - NR_VAOR=? WHERE NR_AGENCIA=?  AND NR_NUMERO_CONTA=?;"
						+ "UPDATE TB_CONTA SET NR_SALDO= NR_SALDO + NR_VAOR=? WHERE NR_AGENCIA=? AND NR_NUMERO_CONTA=?;";

				PreparedStatement pst = con.prepareStatement(sql);

				

				pst.setBigDecimal(1, valor2);
				pst.setLong(2, retornarIdPorAgenciaNumero(agenciaContaOrigem2, numeroContaOrigem2));
				pst.setLong(3, retornarIdPorAgenciaNumero(agenciaContaDestino2, numeroContaDestino2));
				pst.setDate(4, Date.valueOf(date.toLocalDate()));
				pst.setString(5, tipo);

				pst.setBigDecimal(6, valor2);
				pst.setInt(7, agenciaContaOrigem2);
				pst.setInt(8, numeroContaOrigem2);

				pst.setBigDecimal(9, valor2);
				pst.setInt(10, agenciaContaDestino2);
				pst.setInt(11, numeroContaDestino2);

				pst.executeUpdate();

			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {

			throw new RuntimeException(e.getMessage());
		}

	}

	public long retornarIdPorAgenciaNumero(int agencia, int numeroConta) {

		try (Connection con = ConnectionJDBC.abrir();) {
			String sql = "SELECT  PK_ID_CONTA  FROM TB_CONTA WHERE NR_AGENCIA= ? AND NR_NUMERO_CONTA= ?";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs=null;
			if(rs.next()) {
			pst.setInt(1, agencia);
			pst.setInt(2, numeroConta);
			rs = pst.executeQuery();
			long idConta = rs.getLong("PK_ID_CONTA");
			return idConta;
			}
			else {
				throw new RuntimeException("conta nao encontrada");
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}


	private boolean verificacaoSaldo(int agencia, int numero, BigDecimal valor) {
		String sql= "SELECT NR_SALDO FROM TB_CONTA WHERE NR_AGENCIA=? AND NR_NUMERO_CONTA=?";
		
		try(Connection con = ConnectionJDBC.abrir(); 
				PreparedStatement pst = con.prepareStatement(sql)){
			
			pst.setInt(1, agencia);
			pst.setInt(2, numero);
			ResultSet rs= pst.executeQuery();
			
			if(rs.next()) {
				return rs.getBigDecimal("NR_SALDO").doubleValue()>valor.doubleValue();
			} else {
				throw new RuntimeException("saldo indisponivel");
			}
		}catch (SQLException e) {
			 throw new RuntimeException(e.getMessage());
		}
			
		
	}







}