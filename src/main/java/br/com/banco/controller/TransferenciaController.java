package br.com.banco.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;
import br.com.banco.service.TransferenciaServiceImpl;

@RestController
@RequestMapping("/Transferencia")
public class TransferenciaController {
	
	@Autowired
	TransferenciaServiceImpl service;

	@GetMapping("/listarPorConta")
	public ResponseEntity<List<Map<String, String>>> listar(@RequestBody Conta conta) {
		if(conta!=null) {
			return new ResponseEntity<>(service.retornarTransferenciasPorConta(conta), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/transferir")
	public ResponseEntity<String> transferir(@RequestBody Transferencia t) {
		if(t!=null) {
			return new ResponseEntity<>("Transferencia Realizada", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Transferencia não realizada", HttpStatus.CONFLICT);
		}
	}
}
