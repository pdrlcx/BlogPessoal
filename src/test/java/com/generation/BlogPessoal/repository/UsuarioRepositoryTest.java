package com.generation.BlogPessoal.repository;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.BlogPessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		usuarioRepository.save(new Usuario(0L, "Karol Silva", "karol@email.com","12345", ""));
		usuarioRepository.save(new Usuario(0L, "Larissa Souza", "larissa@email.com","12345", ""));
		usuarioRepository.save(new Usuario(0L, "Simone Silva", "simone@email.com","12345", ""));
	}
	
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {
		Optional<Usuario> usuario = usuarioRepository.findByEmailUsuario("karol@email.com");
		assertTrue(usuario.get().getEmailUsuario().equals("karol@email.com"));
	}

	private void assertTrue(boolean equals) {
		// TODO Auto-generated method stub
		
	}

}
