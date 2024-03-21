package br.com.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.banco.connection.ConnectionJDBC;
import br.com.banco.dao.repository.ContaRepository;
import br.com.banco.models.Conta;
@Repository
public class ContaDaoImpl4 implements ContaDao {
	
	ContaRepository repository;
	PreparedStatement desc;
	Statement declaracao;
	ResultSet resultado;
	@Override
	public void salvar(Conta conta) {
		try {
		Connection con = ConnectionJDBC.abrir();
		desc=con.prepareStatement("INSERT INTO tb_conta VALUES (DEFAULT,?,?,?,?,?)");
		desc.setLong(1, conta.getIdUsuario());
		desc.setBigDecimal(2,conta.getSaldo());
		desc.setLong(3,conta.getAgencia());
		desc.setLong(4, conta.getNumeroConta());
		desc.setBigDecimal(5, conta.getLimiteCredito());
		desc.execute();
		desc.close();
		con.close();
		}
		catch(SQLException e) {
			e.getMessage();
		}
		
	}

	@Override
	public List<Conta> listarTodos() {
		List<Conta> contas = new ArrayList<>();
		try {
		Connection con = ConnectionJDBC.abrir();
		declaracao =con.createStatement();
		resultado = declaracao.executeQuery("SELECT * FROM tb_conta ");
		while(resultado.next()) {
			Conta conta = new Conta();
			conta.setIdUsuario(resultado.getLong("fk_id_usuario"));
			conta.setSaldo(resultado.getBigDecimal("NR_Saldo"));
			conta.setAgencia(resultado.getLong("NR_agencia"));
			conta.setNumeroConta(resultado.getLong("NR_numero_conta"));
			conta.setLimiteCredito(resultado.getBigDecimal("NR_limite_credito"));
			contas.add(conta);
			declaracao.close();
			con.close();
		}
		}
		catch(SQLException e) {
			e.getMessage();
		}
		return contas;
	}

	@Override
	public Conta retornarPorID(long idConta) {
		Conta conta = new Conta();
		try {
		Connection con = ConnectionJDBC.abrir();
		desc = con.prepareStatement("SELECT * FROM tb_conta WHERE id_conta = ?");
		desc.setLong(1,idConta);
		resultado = desc.executeQuery();
		resultado.next();
		conta.setIdUsuario(resultado.getLong("fk_id_usuario"));
		conta.setSaldo(resultado.getBigDecimal("NR_saldo"));
		conta.setAgencia(resultado.getLong("NR_Agencia"));
		conta.setNumeroConta(resultado.getLong("NR_Numero_conta"));
		conta.setLimiteCredito(resultado.getBigDecimal("NR_Limite_credito"));
		desc.close();
		con.close();
		}
		catch(SQLException e) {
			e.getMessage();
		}
		return conta;
	}

	@Override
	public void atualizar(Conta conta) {
		
		try {
			Connection con = ConnectionJDBC.abrir();
			desc = con.prepareStatement("UPDATE tb_conta SET nr_saldo=? ,nr_limite_credito=? WHERE id_conta = ?");
			desc.setBigDecimal(1, conta.getSaldo());
			desc.setBigDecimal(2, conta.getLimiteCredito());
			desc.setLong(3, conta.getIdConta());
			desc.execute();
			con.close();;
		}
		catch(SQLException e) {
			e.getMessage();
		}
	}
	@Override
	public Conta retornarPorAgenciaNum(long agencia,long numero) {
		Conta conta = new Conta();
		try {
		Connection con = ConnectionJDBC.abrir();
		desc = con.prepareStatement("SELECT * FROM tb_conta WHERE NR_AGENCIA = ? AND NR_NUMERO_CONTA = ?");
		desc.setLong(1,agencia);
		desc.setLong(2, numero);		
		resultado = desc.executeQuery();
		resultado.next();
		conta.setIdUsuario(resultado.getLong("fk_id_usuario"));
		conta.setSaldo(resultado.getBigDecimal("NR_saldo"));
		conta.setAgencia(resultado.getLong("NR_Agencia"));
		conta.setNumeroConta(resultado.getLong("NR_Numero_conta"));
		conta.setLimiteCredito(resultado.getBigDecimal("NR_Limite_credito"));
		desc.close();
		con.close();
		}
		catch(SQLException e) {
			e.getMessage();
		}
		return conta;
	}
	
	@Override
	public Conta retornarPorIdUsuario(long idUsuario) {
		Conta conta = new Conta();
		try {
		Connection con = ConnectionJDBC.abrir();
		desc = con.prepareStatement("SELECT * FROM tb_conta WHERE fk_id_usuario = ?");
		desc.setLong(1,idUsuario);
		resultado = desc.executeQuery();
		resultado.next();
		conta.setIdUsuario(resultado.getLong("fk_id_usuario"));
		conta.setSaldo(resultado.getBigDecimal("NR_saldo"));
		conta.setAgencia(resultado.getLong("NR_Agencia"));
		conta.setNumeroConta(resultado.getLong("NR_Numero_conta"));
		conta.setLimiteCredito(resultado.getBigDecimal("NR_Limite_credito"));
		desc.close();
		con.close();
		}
		catch(SQLException e) {
			e.getMessage();
		}
		return conta;
	}

}
