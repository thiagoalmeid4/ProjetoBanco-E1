import models.Conta;
import models.Usuario;
import service.ContaServiceImpl;

public class App {
	
    public static void main(String[] args) {
   
        ContaServiceImpl contaService = new ContaServiceImpl();

        
        Usuario usuario = new Usuario();
        Usuario usuario2 = new Usuario();
        
        Conta novaConta = contaService.gerarConta(usuario);
        Conta novaConta2 = contaService.gerarConta(usuario2);
            
            System.out.println("Conta: " + novaConta.getNumeroConta() +
                    "\nAgência: " + novaConta.getAgencia() +
                    "\nSaldo:" + novaConta.getSaldo() +
                    "\nLimite de Crédito: " + novaConta.getLimiteCredito() + 
                    "\nSaldo Atual: " + contaService.retornarSaldo(novaConta));
            
            System.out.println("Conta: " + novaConta2.getNumeroConta() +
                    "\nAgência: " + novaConta2.getAgencia() +
                    "\nSaldo:" + novaConta2.getSaldo() +
                    "\nLimite de Crédito: " + novaConta2.getLimiteCredito() + 
                    "\nSaldo Atual: " + contaService.retornarSaldo(novaConta2));
            
    }
    }

