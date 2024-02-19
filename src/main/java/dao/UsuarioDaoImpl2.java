package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.Usuario;

public class UsuarioDaoImpl2 implements UsuarioDao{
	
	List<Usuario> usuarios = new ArrayList<>();
	public static final String ARQUIVO_CONTAS = "C:\\Users\\jcarvalho\\eclipse-workspace\\ProjetoBanco\\TextUsuario.txt";

	@Override
	public void salvar(Usuario usuario)  {
		try {
			FileWriter file = new FileWriter(ARQUIVO_CONTAS, true);
			BufferedWriter writer = new BufferedWriter(file);
			usuario.setIdUsuario(usuarios.size()+1);
			
			writer.write(usuario.getNome()+","+usuario.getIdUsuario()+","+usuario.getCpf()+","+usuario.getDataNascimento()+","+usuario.getEmail());
			writer.newLine();
			writer.flush();
			writer.close();
			
		} catch (Exception e) {
			
			throw new RuntimeException();
		}
		
	}

	@Override
	public List<Usuario> listarTodos() {
		try {
			FileReader file = new FileReader(ARQUIVO_CONTAS);
			BufferedReader reader = new BufferedReader(file);
			usuarios = new ArrayList<>();
			while(reader.ready()) {
				Usuario u = new Usuario();
				String line = reader.readLine();
				String[] s = line.split(",");
				u.setNome(s[0]);
				u.setIdUsuario(Long.parseLong(s[1]));
				u.setCpf(s[2]);
				u.setDataNascimento(LocalDate.parse(s[3]));
				u.setEmail(s[4]);
				usuarios.add(u);
			}
			reader.close();
			
		} catch (Exception e) {
			
			throw new RuntimeException();  
		}
		return usuarios;
	}

	@Override
	public Usuario retornarPorID(long idUsuario) {
		for(Usuario u: listarTodos()) {
			if(u.getIdUsuario()==idUsuario) {
				return u;
			}
		}
		return null;
	}

}
