package com.generation.BlogPessoal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generation.BlogPessoal.model.Usuario;
import com.generation.BlogPessoal.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String emailUsuario) throws UsernameNotFoundException {

		Optional<Usuario> usuario = usuarioRepository.findByEmailUsuario(emailUsuario);
		usuario.orElseThrow(() -> new UsernameNotFoundException(emailUsuario + " not found."));

			return usuario.map(UserDetailsImpl::new).get();
	}


}
