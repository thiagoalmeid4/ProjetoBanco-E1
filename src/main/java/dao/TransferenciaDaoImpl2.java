package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import models.Transferencia;

public class TransferenciaDaoImpl2 implements TransferenciaDao {
	private static final String ARQUIVO_TRANSFERENCIA = "C:\\Users\\lucosta\\Documents\\listaTransferencia.txt";

	@Override
	public void salvar(Transferencia transferencia) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_TRANSFERENCIA, true))) {
			writer.println(transferencia.getIdTransferencia() + "," + transferencia.getIdContaOrigem() + ","
					+ transferencia.getIdContaDestino());
		} catch (IOException e) {
			System.out.println("Erro ao salvar no arquivo: " + e.getMessage());
		}
	}

	@Override
	public List<Transferencia> listarTodos() {
		List<Transferencia> listaTransferencia = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_TRANSFERENCIA))) {
			String linha;

			while ((linha = br.readLine()) != null) {
				String[] dados = linha.split(",");
				long idTransferencia = Long.parseLong(dados[0].trim());
				long idContaOrigem = Long.parseLong(dados[1].trim());
				long idContaDestino = Long.parseLong(dados[2].trim());

				Transferencia transferencia = new Transferencia();
				transferencia.setIdTransferencia(idTransferencia);
				transferencia.setIdContaOrigem(idContaOrigem);
				transferencia.setIdContaDestino(idContaDestino);

				listaTransferencia.add(transferencia);
			}

		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		}

		return listaTransferencia;
	}

	@Override
	public Transferencia retornarPorID(long idTransferencia) {
		try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_TRANSFERENCIA))) {
			String linha;

			while ((linha = br.readLine()) != null) {
				String[] dados = linha.split(",");
				long id = Long.parseLong(dados[0].trim());

				if (id == idTransferencia) {
					long idContaOrigem = Long.parseLong(dados[1].trim());
					long idContaDestino = Long.parseLong(dados[2].trim());

					Transferencia transferencia = new Transferencia();
					transferencia.setIdTransferencia(id);
					transferencia.setIdContaOrigem(idContaOrigem);
					transferencia.setIdContaDestino(idContaDestino);

					return transferencia;
				}
			}

		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		}

		return null;
	}
}
