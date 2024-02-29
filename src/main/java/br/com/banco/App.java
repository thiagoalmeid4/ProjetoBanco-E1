package br.com.banco;
import br.com.banco.execute.Build;

public class App {

	public static void main(String[] args) {

		Build build = new Build(true);
		build.execute();
		
	}
}