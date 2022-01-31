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

import com.generation.BlogPessoal.model.Tema;
import com.generation.BlogPessoal.repository.TemaRepository;

@RestController
@RequestMapping("/tema")
@CrossOrigin("*")
public class TemaController {

	@Autowired
	private TemaRepository repository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Tema>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{idTema}")
	public ResponseEntity<Tema> getById(@PathVariable Long idTema) {
		return repository.findById(idTema).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{tema}")
	public ResponseEntity<List<Tema>> getByTema(@PathVariable String tema) {
		return ResponseEntity.ok(repository.findAllByTemaContainingIgnoreCase(tema));
	}
	
	@PostMapping("/novoTema")
	public ResponseEntity<Tema> post(@RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}
	
	@PutMapping("/editarTema")
	public ResponseEntity<Tema> put(@RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(tema));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
