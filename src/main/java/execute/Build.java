package execute;

import java.util.ArrayList;

import dao.ContaDao;
import dao.ContaDaoImpl;
import dao.ContaDaoImpl2;
import dao.TransferenciaDao;
import dao.TransferenciaDaoImpl;
import dao.TransferenciaDaoImpl2;
import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import dao.UsuarioDaoImpl2;
import models.Conta;
import models.Transferencia;
import models.Usuario;
import service.ContaService;
import service.ContaServiceImpl;
import service.TransferenciaService;
import service.TransferenciaServiceImpl;
import service.UsuarioService;
import service.UsuarioServiceImpl;
import view.MenuInicial;

public class Build {
    
    private UsuarioDao usuarioDao;
    private ContaDao contaDao;
    private TransferenciaDao transferenciaDao;

    private UsuarioService usuarioService;
    private ContaService contaService;
    private TransferenciaService transferenciaService;

    private void initService(){
        usuarioService = new UsuarioServiceImpl(usuarioDao);
        contaService = new ContaServiceImpl(contaDao);
        transferenciaService = new TransferenciaServiceImpl(transferenciaDao, contaDao, usuarioDao);
    }
   
    public Build (){
        exe1();
        initService();
    }

    public Build (boolean persistirEmArquivo){
        if(persistirEmArquivo){
            exe2();
        }else{
            exe1();
        }
        initService();
    }

    public void execute(){
        new MenuInicial(usuarioService, contaService, transferenciaService).executar();
    }

    private void exe1(){
        ArrayList<Usuario> dataUsuarios = new ArrayList<>();
        ArrayList<Conta> dataContas = new ArrayList<>();
        ArrayList<Transferencia> dataTransferencias = new ArrayList<>();

        usuarioDao = new UsuarioDaoImpl(dataUsuarios);
        contaDao = new ContaDaoImpl(dataContas);
        transferenciaDao = new TransferenciaDaoImpl(dataTransferencias);
    }

    private void exe2(){
        usuarioDao = new UsuarioDaoImpl2();
        contaDao = new ContaDaoImpl2();
        transferenciaDao = new TransferenciaDaoImpl2();
    }

}
