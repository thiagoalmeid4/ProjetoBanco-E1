package br.com.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.dtos.LoginDtos;
import br.com.banco.dtos.UsuarioDto;
import br.com.banco.models.Usuario;
import br.com.banco.service.ContaServiceImpl;
import br.com.banco.service.UsuarioServiceImpl;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	UsuarioServiceImpl usuarioService;
	@Autowired
	ContaServiceImpl contaService;

	@PostMapping("/salvar")
	public ResponseEntity salvarUsuario(@RequestBody Usuario usuario) {
		usuarioService.salvarUsuario(usuario);
		contaService.gerarConta(usuario);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public Usuario login(@RequestBody LoginDtos login) {
		return usuarioService.login(login.getEmail(), login.getSenha());
	}

	@GetMapping("/retornarPorId/{idUsuario}")
	public UsuarioDto retornarPorId(@PathVariable long idUsuario) {
		var dto = new UsuarioDto();
		var u = usuarioService.retornarPorId(idUsuario);
		dto.setCpf(u.getCpf());
		dto.setDataNascimento(u.getDataNascimento());
		dto.setEmail(u.getEmail());
		dto.setIdUsuario(idUsuario);
		dto.setNome(u.getNome());
		return dto;

	}

}
