package com.generation.BlogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.BlogPessoal.model.Usuario;
import com.generation.BlogPessoal.model.UsuarioLogin;
import com.generation.BlogPessoal.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//Cadastrar Usuário
	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (usuarioRepository.findByEmailUsuario(usuario.getEmailUsuario()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
		usuario.setSenhaUsuario(criptografarSenhaUsuario(usuario.getSenhaUsuario()));

		return Optional.of(usuarioRepository.save(usuario));
	}
	
	//Atualizar Usuário
	public Optional<Usuario> atualizarUsuario(Usuario usuario) {
        if (usuarioRepository.findById(usuario.getIdUsuario()).isPresent()) {
            Optional<Usuario> buscaUsuario = usuarioRepository.findByEmailUsuario(usuario.getEmailUsuario());
            if (buscaUsuario.isPresent()) {
                if (buscaUsuario.get().getIdUsuario() != usuario.getIdUsuario())
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
            }
            usuario.setSenhaUsuario(criptografarSenhaUsuario(usuario.getSenhaUsuario()));
            return Optional.of(usuarioRepository.save(usuario));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);
    }
	
	//Logar Usuário
    public Optional<UsuarioLogin> logarUsuario(Optional<UsuarioLogin> usuarioLogin) {
        Optional<Usuario> usuario = usuarioRepository.findByEmailUsuario(usuarioLogin.get().getEmailUsuario());

        if (usuario.isPresent()) {
            if (compararSenhasUsuario(usuarioLogin.get().getSenhaUsuario(), usuario.get().getSenhaUsuario())) {

                usuarioLogin.get().setIdUsuario(usuario.get().getIdUsuario());
                usuarioLogin.get().setNomeUsuario(usuario.get().getNomeUsuario());
                usuarioLogin.get().setFotoUsuario(usuario.get().getFotoUsuario());
                usuarioLogin.get().setTokenUsuario(generatorBasicTokenUsuario(usuarioLogin.get().getEmailUsuario(), usuarioLogin.get().getSenhaUsuario()));
                usuarioLogin.get().setSenhaUsuario(usuario.get().getSenhaUsuario());
                usuarioLogin.get().setTipoUsuario(usuario.get().getTipoUsuario());

                return usuarioLogin;
            }
        }
        throw new ResponseStatusException(
            HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null);
    }
    
    //Criptografar Senha
    private String criptografarSenhaUsuario(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaEncoder = encoder.encode(senha);
        return senhaEncoder;
    }
    
    //Comparar Senhas
    private boolean compararSenhasUsuario(String senhaDigitada, String senhaBanco) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(senhaDigitada, senhaBanco);
    }
    
    //Gerador de token
    private String generatorBasicTokenUsuario(String usuario, String password) {
        String structure = usuario + ":" + password;
        byte[] structureBase64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(structureBase64);
        
    }

	
}
