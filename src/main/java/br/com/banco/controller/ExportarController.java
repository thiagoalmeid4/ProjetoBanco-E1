package br.com.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.banco.dao.ContaDaoImpl4;
import br.com.banco.dao.UsuarioDaoImpl4;
import br.com.banco.utils.ExportadorConta;
import br.com.banco.utils.ExportadorUsuario;

@Controller
@RequestMapping("/Exportar")
public class ExportarController {
	@Autowired
	ExportadorConta ec;
	@Autowired
	ExportadorUsuario eu;
	@Autowired
	ContaDaoImpl4 cdi;
	@Autowired
	UsuarioDaoImpl4 udi;
	
	@PostMapping("/Conta")
	public ResponseEntity<String> exportarConta() {
		ec.exportarParaTxt(cdi.listarTodos());
		return new ResponseEntity<>("Exportação completa",HttpStatus.OK);
	}
	@PostMapping("/Usuario")
	public ResponseEntity<String> exportarUsuario() {
		eu.exportarParaTxt(udi.listarTodos());
		return new ResponseEntity<>("Exportação completa",HttpStatus.OK);
	}
}
