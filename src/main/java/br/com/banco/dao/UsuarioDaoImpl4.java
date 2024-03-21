package br.com.banco.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.banco.connection.ConnectionJDBC;
import br.com.banco.models.Usuario;
@Repository
public class UsuarioDaoImpl4 implements UsuarioDao{

	
	
	@Override
	public void salvar(Usuario usuario) {
		String sql= "INSERT INTO TB_USUARIO (NM_NOME, NM_SENHA, NM_CPF, NM_EMAIL, DT_DATA_NASCIMENTO) VALUES (?,?,?,?,?)";
		try (Connection c = ConnectionJDBC.abrir();
				PreparedStatement ps= c.prepareStatement(sql)){
			
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSenha());
			ps.setString(3, usuario.getCpf());
			ps.setString(4, usuario.getEmail());
			ps.setDate(5, Date.valueOf(usuario.getDataNascimento()));
			ps.execute();
		
		
		
		
		
		} catch (SQLException e) {
			
			e.getMessage();
		}
		
	}

	@Override
	public List<Usuario> listarTodos() {
		String sql="SELECT * FROM TB_USUARIO";
		List<Usuario> usuarios= new ArrayList<>();
		
		try (Connection c = ConnectionJDBC.abrir();
				Statement s= c.createStatement()){
			
		
			ResultSet rs= s.executeQuery(sql);
		
		while(rs.next()) {
			Usuario user = new Usuario();
			user.setNome(rs.getString("NM_NOME"));
			user.setSenha(rs.getString("NM_SENHA"));
			user.setCpf(rs.getString("NM_CPF"));
			user.setEmail(rs.getString("NM_EMAIL"));
			user.setDataNascimento(rs.getDate("DT_DATA_NASCIMENTO").toLocalDate());
			usuarios.add(user);
		}
		
			
			} catch (SQLException e) {
			
			e.getMessage();
		}
		
		return usuarios;
	}

	@Override
	public Usuario retornarPorID(long idUsuario) {
		String sql= "SELECT * FROM TB_USUARIO WHERE PK_ID_USUARIO=?";
		
		try (Connection c = ConnectionJDBC.abrir();
				PreparedStatement ps= c.prepareStatement(sql)){
			
			
			ps.setLong(1, idUsuario);
			ResultSet rs= ps.executeQuery();
			
			 while (rs.next()) {
		            Usuario user = new Usuario();
		            user.setIdUsuario(rs.getLong("PK_ID_USUARIO"));
		            user.setNome(rs.getString("NM_NOME"));
		            user.setSenha(rs.getString("NM_SENHA"));
		            user.setCpf(rs.getString("NM_CPF"));
		            user.setEmail(rs.getString("NM_EMAIL"));
		            user.setDataNascimento(rs.getDate("DT_DATA_NASCIMENTO").toLocalDate());
		            return user;
		        }
			
			
			
			
		}catch(SQLException e) {
			e.getMessage();
		}
		
		
		
		return null;
	}

	@Override
	public boolean verificacao(String cpf, String email) {
    String sql= "SELECT * FROM TB_USUARIO WHERE NM_CPF=? AND NM_EMAIL=?";
		boolean usuarioExiste=false;
		try (Connection c = ConnectionJDBC.abrir();
				PreparedStatement ps= c.prepareStatement(sql)){
			
			ps.setString(1, cpf);
			ps.setString(2, email);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				usuarioExiste=true;
			}
		}catch(Exception e ) {
			throw new RuntimeException("Usuario nao existente");
		}
		
		
		return usuarioExiste;
	}

	@Override
	public Usuario login(String cpf, String senha) {
		String sql= "SELECT * FROM TB_USUARIO WHERE NM_CPF=? AND NM_SENHA=?";
		
		try (Connection c = ConnectionJDBC.abrir();
				PreparedStatement ps= c.prepareStatement(sql)){
			
			ps.setString(1, cpf);
			ps.setString(2, senha);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				Usuario usuario= new Usuario();
				usuario.setIdUsuario(rs.getLong("PK_ID_USUARIO"));
				usuario.setCpf(rs.getString("NM_CPF"));
				usuario.setSenha(rs.getString("NM_SENHA"));
				return usuario;
			}
		
			
		} catch (SQLException e) {
            e.getMessage();
        }
		
		
		return null;
	}

}
