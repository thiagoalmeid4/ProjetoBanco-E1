import dao.UsuarioDao;
import dao.UsuarioDaoImpl2;
import service.UsuarioService;
import service.UsuarioServiceImpl;
import view.MenuInicial;
import dao.ContaDao;
import dao.ContaDaoImpl2;
import dao.TransferenciaDaoImpl2;
import service.ContaService;
import service.ContaServiceImpl;
import service.TransferenciaService;
import service.TransferenciaServiceImpl;
import dao.TransferenciaDao;

public class App {

	public static void main(String[] args) {
		
		ContaDao dao2 = new ContaDaoImpl2();
	
		ContaService contaService = new ContaServiceImpl(dao2);		
	
    	UsuarioDao dao = new UsuarioDaoImpl2();
    	UsuarioService usuarioService = new UsuarioServiceImpl(dao);
    
		TransferenciaDao transferenciaDao = new TransferenciaDaoImpl2();
    	TransferenciaService transferenciaService = new TransferenciaServiceImpl(transferenciaDao, dao2, dao);
    
		MenuInicial menu = new MenuInicial(usuarioService, contaService, transferenciaService );
 
    	menu.executar();

		
	}
}