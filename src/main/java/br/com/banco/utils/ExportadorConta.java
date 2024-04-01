package br.com.banco.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.banco.models.Conta;
@Component
public class ExportadorConta {
	public static void exportarParaTxt(List<Conta> contas) {
	try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\ypiovarczik\\Downloads\\teste\\teste.txt", true))) {
		for (Conta conta : contas) {
		
		String idConta=String.format("%05d", conta.getIdConta());
		String idUsuario=String.format("%05d", conta.getIdUsuario());
		String numeroConta=String.format("%08d", conta.getNumeroConta());
		String agencia=String.format("%04d", conta.getAgencia());
		String saldo= String.format("%09.2f", (double) conta.getSaldo());
		String limiteCredito= String.format("%09.2f" (double), conta.getLimiteCredito());
		
			
			
			
			
		writer.write(conta.getIdConta() 
		+ "-" + idUsuario
		+ "-" + numeroConta
		+ "-" + agencia
		+ "-" + saldo
		+ "-" + limiteCredito);
		writer.newLine();
		}
		} catch (IOException e) {
		e.printStackTrace();
		}
		}
}
