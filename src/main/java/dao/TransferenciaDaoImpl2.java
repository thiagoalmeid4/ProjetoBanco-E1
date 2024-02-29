package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import models.Transferencia;

public class TransferenciaDaoImpl2 implements TransferenciaDao {
	private static final String ARQUIVO_TRANSFERENCIA = "Transferencia.txt";

	@Override
	public void salvar(Transferencia transferencia) {
		transferencia.setIdTransferencia(listarTodos().size()+1);
		try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_TRANSFERENCIA, true))) {
			writer.println(transferencia.getIdTransferencia() + "," + transferencia.getIdContaOrigem() + ","
					+ transferencia.getIdContaDestino()+","+transferencia.getValor()+","+transferencia.getData()+","+transferencia.getTipo());
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
				BigDecimal valor = new BigDecimal(dados[3].trim());
				LocalDateTime data = LocalDateTime.parse(dados[4].trim());
				String tipo = dados[5];

				Transferencia transferencia = new Transferencia();
				transferencia.setIdTransferencia(idTransferencia);
				transferencia.setIdContaOrigem(idContaOrigem);
				transferencia.setIdContaDestino(idContaDestino);
				transferencia.setValor(valor);
				transferencia.setData(data);
				transferencia.setTipo(tipo);

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
					BigDecimal valor = new BigDecimal(dados[3].trim());
					LocalDateTime data = LocalDateTime.parse(dados[4].trim());
					String tipo = dados[5];

					Transferencia transferencia = new Transferencia();
					transferencia.setIdTransferencia(id);
					transferencia.setIdContaOrigem(idContaOrigem);
					transferencia.setIdContaDestino(idContaDestino);
					transferencia.setValor(valor);
					transferencia.setData(data);
					transferencia.setTipo(tipo);

					return transferencia;
				}
			}

		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		}

		return null;
	}
}
