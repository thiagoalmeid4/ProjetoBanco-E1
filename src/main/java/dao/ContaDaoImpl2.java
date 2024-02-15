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
	private List<Conta> contaFonte;

	public ContaDaoImpl2(List<Conta> contaFonte) {

		this.contaFonte = contaFonte;
	}

	@Override
	public void salvar(Conta conta) {
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;

		try {
			fileWriter = new FileWriter("testeConta.txt", true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.newLine();
			bufferedWriter.write(contaFonte.add(conta));
			bufferedWriter.write(contaFonte.size());
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
		try {
			this.contaFonte = new ArrayList<>();
			fileReader = new FileReader("testeConta.txt");
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
		return this.contaFonte;
	}

	@Override
	public Conta retornarPorID(long idTransferencia) {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(testeConta.txt))) {
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