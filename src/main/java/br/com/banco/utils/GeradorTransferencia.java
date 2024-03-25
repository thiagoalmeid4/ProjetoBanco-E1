package br.com.banco.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import br.com.banco.dao.ContaDaoImpl4;
import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;

@Component
public class GeradorTransferencia {
	private Random ran = new Random();
	private ContaDaoImpl4 cdi4 = new ContaDaoImpl4();
	private List<Conta> listaConta = new ArrayList<>();
	private List<Transferencia> lista = new ArrayList<>();
	private int index;

	public void salvarTransferencias() {
		for(Transferencia trans : lista) {
		try (	BufferedWriter writer = new BufferedWriter(new FileWriter(
					"C:\\Users\\ebabetto\\Documents\\Projetos\\BancoEquipe1\\TransferenciasGeradas.txt", true))){
			writer.write(trans.getIdContaDestino() + "" + trans.getIdContaOrigem()
					+ "" + trans.getValor() + "" + trans.getData()+""+trans.getTipo());
			writer.flush();
			writer.newLine();
		} catch (IOException e) {
			e.getMessage();
		}
		}
	}

	public void geradorTransferencias(int i1) {
		for (Conta c : cdi4.listarTodos()) {
			listaConta.add(c);
		}
		for (int i = 0; i < i1; i++) {
			Transferencia trans = new Transferencia();
			index = ran.nextInt(listaConta.size());
			trans.setIdContaDestino(listaConta.get(index).getIdConta());
			index = ran.nextInt(listaConta.size());
			trans.setIdContaOrigem(listaConta.get(index).getIdConta());
			trans.setValor(new BigDecimal(ran.nextDouble(10,10000)).setScale(2,BigDecimal.ROUND_HALF_UP));
			trans.setData(geradorData());
			trans.setTipo(geradorTipo());
			while(trans.getIdContaDestino()==trans.getIdContaOrigem()) {
				index = ran.nextInt(listaConta.size());
				trans.setIdContaDestino(listaConta.get(ran.nextInt(listaConta.size())).getIdConta());
			}
			lista.add(trans);
		}
		salvarTransferencias();
	}
	public LocalDateTime geradorData() {
		LocalDateTime date=null;
		int mes = ran.nextInt(12)+1;
		int maxdia= LocalDate.of(2023, mes, 1).lengthOfMonth();
		int dia = ran.nextInt(maxdia)+1;
		date = LocalDateTime.of(2023,mes,dia,ran.nextInt(24),ran.nextInt(60),ran.nextInt(60),ran.nextInt(1000)*1000);
		return date;
	}
	public String geradorTipo() {
		String tipo=null;
		int o = ran.nextInt(1,2);
		if(o==1) {
			tipo = "E";
		}
		else {
			tipo = "S";
		}
		return tipo;
	}
}
