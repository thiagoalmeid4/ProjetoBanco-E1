package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import models.Conta;

public class ContaDaoImpl2 implements ContaDao {
	public static final String ARQUIVO_CONTA = "C:\\Users\\lualmeida\\Documents\\conta.txt";
	

	@Override
	public void salvar(Conta conta) {
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;

		try {
			bufferedWriter = new BufferedWriter(new FileWriter(ARQUIVO_CONTA));
			bufferedWriter.newLine();
			String contaTexto = conta.toString();
			bufferedWriter.write(contaTexto);
			bufferedWriter.write(listarTodos().size());
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<Conta> listarTodos() {
		FileReader fileReader;
		BufferedReader bufferedReader;
		List <Conta> contaFonte= new ArrayList<>();
		try {
			
			fileReader = new FileReader(ARQUIVO_CONTA);
			bufferedReader = new BufferedReader(fileReader);
			bufferedReader.readLine();
			while (bufferedReader.ready()) {
				long idConta;
				long idUsuario;
				BigDecimal saldo;
				long agencia;
				long numeroConta;
				BigDecimal limiteCredito;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return contaFonte;
	}

	@Override
	public Conta retornarPorID(long idTransferencia) {
		try (BufferedReader bufferedReader = new BufferedReader( new FileReader(ARQUIVO_CONTA))) {
			String linha;

			while ((linha = bufferedReader.readLine()) != null) {
				String[] dados = linha.split(",");
				long id = Long.parseLong(dados[0].trim());

				if (id == idTransferencia) {
					long idContaOrigem = Long.parseLong(dados[1].trim());
					long idContaDestino = Long.parseLong(dados[2].trim());

					return new Conta();
				}
			}

		} catch (Exception e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		}

		return null;
	}
}