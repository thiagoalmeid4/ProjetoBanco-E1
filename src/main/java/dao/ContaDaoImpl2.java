package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import models.Conta;

public class ContaDaoImpl2 implements ContaDao {
	public static final String ARQUIVO_CONTA = "C:\\Users\\lualmeida\\Documents\\conta.txt";

	@Override
	public void salvar(Conta conta) {
	    try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_CONTA, true))) {
	        conta.setIdConta(listarTodos().size() + 1);
	        writer.println(conta.getIdConta() + "," + conta.getIdUsuario() + ","
	                + conta.getNumeroConta() + "," + conta.getAgencia() + "," + conta.getSaldo() + "," + conta.getLimiteCredito());
	    } catch (IOException e) {
	        System.out.println("Erro ao salvar no arquivo: " + e.getMessage());
	    }
	}


	@Override
	public List<Conta> listarTodos() {
		List<Conta> listaConta = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CONTA))) {
			String linha;

			while ((linha = br.readLine()) != null) {
				String[] dados = linha.split(",");
				
				long idConta = Long.parseLong(dados[0].trim());
				long numeroConta = Long.parseLong(dados[2].trim());
				long idUsuario = Long.parseLong(dados[2].trim());
				BigDecimal saldo = new BigDecimal(dados[3].trim());
				long agencia = Long.parseLong(dados[4].trim());
				BigDecimal limiteCredito = new BigDecimal(dados[5].trim());

				Conta conta = new Conta();
				conta.setIdConta(idConta);
				conta.setNumeroConta(numeroConta);
				conta.setIdUsuario(idUsuario);
				conta.setSaldo(saldo);
				conta.setAgencia(agencia);
				conta.setLimiteCredito(limiteCredito);

				listaConta.add(conta);
			}

		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		}

		return listaConta;
	}

	@Override
	public Conta retornarPorID(long idConta) {
	    try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CONTA))) {
	        String linha;

	        while ((linha = br.readLine()) != null) {
	            String[] dados = linha.split(",");
	            long idDaConta = Long.parseLong(dados[0].trim()); 
	            long idDoUsuario = Long.parseLong(dados[1].trim());
	            long numeroConta = Long.parseLong(dados[2].trim());
	            BigDecimal saldo = new BigDecimal(dados[3].trim());
	            long agencia = Long.parseLong(dados[4].trim());
	            BigDecimal limiteCredito = new BigDecimal(dados[5].trim());

	            if (idDaConta == idConta) {
	                Conta conta = new Conta();
	                conta.setIdUsuario(idDoUsuario);
	                conta.setNumeroConta(numeroConta);
	                conta.setSaldo(saldo);
	                conta.setAgencia(agencia);
	                conta.setLimiteCredito(limiteCredito);

	                return conta;
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Erro ao ler o arquivo: " + e.getMessage());
	    }

	    return null;
	}

}
