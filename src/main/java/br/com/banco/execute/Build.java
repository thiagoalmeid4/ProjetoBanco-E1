package br.com.banco.execute;

import java.util.ArrayList;

import br.com.banco.connection.ConnectionJDBC;
import br.com.banco.dao.ContaDao;
import br.com.banco.dao.ContaDaoImpl;
import br.com.banco.dao.ContaDaoImpl4;
import br.com.banco.dao.TransferenciaDao;
import br.com.banco.dao.TransferenciaDaoImpl;
import br.com.banco.dao.TransferenciaDaoImpl4;
import br.com.banco.dao.UsuarioDao;
import br.com.banco.dao.UsuarioDaoImpl;
import br.com.banco.dao.UsuarioDaoImpl4;
import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;
import br.com.banco.models.Usuario;
import br.com.banco.service.ContaService;
import br.com.banco.service.ContaServiceImpl;
import br.com.banco.service.TransferenciaService;
import br.com.banco.service.TransferenciaServiceImpl;
import br.com.banco.service.UsuarioService;
import br.com.banco.service.UsuarioServiceImpl;
import br.com.banco.view.MenuInicial;

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
        usuarioDao = new UsuarioDaoImpl4();
        contaDao = new ContaDaoImpl4();
        transferenciaDao = new TransferenciaDaoImpl4(ConnectionJDBC.abrir());
    }

}
