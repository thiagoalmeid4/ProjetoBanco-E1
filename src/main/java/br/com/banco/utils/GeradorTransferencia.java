package br.com.banco.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import br.com.banco.connection.ConnectionJDBC;
import br.com.banco.dao.ContaDaoImpl4;
import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;

@Component
public class GeradorTransferencia {
	private Random ran = new Random();
	private ContaDaoImpl4 cdi4 = new ContaDaoImpl4();
	private List<Transferencia> lista = new ArrayList<>();
	private int indexOrigem;
	private int indexDestino;
	private String cpf;
	String query = "SELECT NM_CPF,C.NR_AGENCIA,C.NR_NUMERO_CONTA FROM TB_CONTA C INNER JOIN TB_USUARIO U ON U.PK_ID_USUARIO = C.FK_ID_USUARIO;";
	private List<Conta> listaConta=new ArrayList<>();
	private List<Short> listaAgencia = new ArrayList<>();
	private List<Integer> listaNumeroConta=new ArrayList<>();
	private char tipo;
	
	public void salvarTransferencias() {
		for (Transferencia trans : lista) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(
					"C:\\Users\\ebabetto\\Documents\\Projetos\\BancoEquipe1\\TransferenciasGeradas.txt", true))) {
				writer.write(cpf + "" + listaAgencia.get(indexOrigem) + ""
						+ listaNumeroConta.get(indexOrigem)+ "" + listaAgencia.get(indexDestino)
						+ "" + listaNumeroConta.get(indexDestino) + "" + trans.getValor() + ""
						+ trans.getData() + "" + tipo);
				writer.flush();
				writer.newLine();
			} catch (IOException e) {
				e.getMessage();
			}
		}
	}

	public void geradorTransferencias(int i1) {
		for(Conta conta :cdi4.listarTodos()) {
			listaConta.add(conta);
		}
		for (int i = 0; i < i1; i++) {
			Transferencia trans = new Transferencia();
			acessarDados();
			indexDestino = ran.nextInt(listaConta.size());
			indexOrigem = ran.nextInt(listaConta.size());
			tipo = ran.nextBoolean() ? 'E' : 'S';
			while (indexDestino == indexOrigem) {
				indexOrigem = ran.nextInt(listaConta.size());
			}
			trans.setValor(new BigDecimal(ran.nextDouble(10, 10000)).setScale(2, BigDecimal.ROUND_HALF_UP));
			trans.setData(geradorData());
			lista.add(trans);
		}
		salvarTransferencias();
	}

	public LocalDateTime geradorData() {
		LocalDateTime date = null;
		int mes = ran.nextInt(12) + 1;
		int maxdia = LocalDate.of(2023, mes, 1).lengthOfMonth();
		int dia = ran.nextInt(maxdia) + 1;
		date = LocalDateTime.of(2023, mes, dia, ran.nextInt(24), ran.nextInt(60), ran.nextInt(60),
				ran.nextInt(1000) * 1000);
		return date;
	}

	public void acessarDados() {
		try (Connection con = ConnectionJDBC.abrir(); Statement declaracao = con.createStatement()) {
			ResultSet resultado = declaracao.executeQuery(query);
			while (resultado.next()) {
				listaAgencia.add(resultado.getShort("NR_AGENCIA"));
				listaNumeroConta.add(resultado.getInt("NR_NUMERO_CONTA"));
				cpf = resultado.getString("NM_CPF");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
