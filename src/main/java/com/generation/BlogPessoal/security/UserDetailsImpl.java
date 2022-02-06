package com.generation.BlogPessoal.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.generation.BlogPessoal.model.Usuario;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private String emailUsuario;
	private String senhaUsuario;
	
	public UserDetailsImpl(Usuario usuario) {
	this.emailUsuario = usuario.getEmailUsuario();
	this.senhaUsuario = usuario.getSenhaUsuario();
	}
	
	public UserDetailsImpl() {
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	@Override
	public String getPassword() {
		return senhaUsuario;
	}
	
	@Override
	public String getUsername() {
		return emailUsuario;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
