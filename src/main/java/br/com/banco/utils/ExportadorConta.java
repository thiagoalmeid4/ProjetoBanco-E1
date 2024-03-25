package br.com.banco.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import br.com.banco.models.Conta;
public class ExportadorConta {
	public static void exportarParaTxt(List<Conta> contas, String saida) {
	try (BufferedWriter writer = new BufferedWriter(new FileWriter(saida))) {
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
