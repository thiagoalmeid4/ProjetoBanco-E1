package br.com.banco.utils;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import br.com.banco.models.Usuario;


public class ExportadorUsuario {

	    public static void exportarParaTxt(List<Usuario> usuarios, String saida) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(saida))) {
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


