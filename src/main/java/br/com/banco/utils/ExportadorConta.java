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
		writer.write(conta.getIdConta() 
		+ "-" + conta.getIdUsuario() 
		+ "-" + conta.getNumeroConta()
		+ "-" + conta.getAgencia()
		+ "-" + conta.getSaldo()
		+ "-" + conta.getLimiteCredito());
		writer.newLine();
		}
		} catch (IOException e) {
		e.printStackTrace();
		}
		}
}
