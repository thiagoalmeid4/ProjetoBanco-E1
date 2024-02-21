import java.util.List;

import models.Conta;
import models.Transferencia;
import models.Usuario;
import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import dao.UsuarioDaoImpl2;
import service.UsuarioService;
import service.UsuarioServiceImpl;
import view.MenuInicial;
import dao.ContaDao;
import dao.ContaDaoImpl;
import dao.ContaDaoImpl2;
import dao.TransferenciaDaoImpl2;
import service.ContaService;
import service.ContaServiceImpl;
import service.TransferenciaService;
import service.TransferenciaServiceImpl;
import dao.TransferenciaDao;

import java.util.ArrayList;

public class App {

	public static void main(String[] args) {
		
		List<Conta> lista2 = new ArrayList<>();
		ContaDao dao2 = new ContaDaoImpl2();
		ContaService contaService = new ContaServiceImpl(dao2);		
		
		List<Usuario> lista = new ArrayList<>();
    	UsuarioDao dao = new UsuarioDaoImpl2();
    	UsuarioService usuarioService = new UsuarioServiceImpl(dao);
    	var transferencia = new Transferencia();
    	TransferenciaDao transferenciaDao = new TransferenciaDaoImpl2();
    	TransferenciaService transferenciaService = new TransferenciaServiceImpl(transferenciaDao, dao2, dao);
    	MenuInicial menu = new MenuInicial(usuarioService, contaService, transferenciaService );
 
    	menu.executar();

		
	}
}