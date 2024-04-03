package br.com.banco.utils;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.banco.models.Usuario;

@Component
public class ExportadorUsuario {

	    public void exportarParaTxt(List<Usuario> usuarios) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\hnantes\\Documents\\BancoDigitalEquipe1\\Usuarios.txt", true))) {
			for (Usuario usuario : usuarios) {
			writer.write(usuario.getIdUsuario() 
			+ ";" + usuario.getNome()
			+ ";" + usuario.getSenha()
			+ ";" + usuario.getCpf()
			+ ";" + usuario.getDataNascimento());
			writer.newLine();
			}
			} catch (IOException e) {
			e.printStackTrace();
			}
			}
}


