package com.generation.BlogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.BlogPessoal.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public List<Usuario> findAllByNomeUsuarioContainingIgnoreCase (String nomeUsuario);
	public Optional<Usuario> findByEmailUsuario(String emailUsuario);

}
