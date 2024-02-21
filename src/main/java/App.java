import java.math.BigDecimal;

import dao.ContaDaoImpl2;
import models.Conta;

public class App {

    public static void main(String[] args) {
    	ContaDaoImpl2 contaDaoImpl2 = new ContaDaoImpl2();
    	Conta conta = new Conta(17L, 68L, 90L, new BigDecimal(15), new BigDecimal(30));
    	contaDaoImpl2.retornarPorID(7);
    	

    }
}
