package com.generation.BlogPessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.BlogPessoal.model.Usuario;
import com.generation.BlogPessoal.repository.UsuarioRepository;



@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{idUsuario}")
	public ResponseEntity<Usuario> getById(@PathVariable Long idUsuario) {
		return repository.findById(idUsuario).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{nomeUsuario}")
	public ResponseEntity<List<Usuario>> getByNomeusuario(@PathVariable String nomeusuario) {
		return ResponseEntity.ok(repository.findAllByNomeUsuarioContainingIgnoreCase(nomeusuario));
	}
	
	@PostMapping ("/novoUsuario")
	public ResponseEntity<Usuario> post(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}
	
	@PutMapping("/editar")
	public ResponseEntity<Usuario> put(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuario));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
