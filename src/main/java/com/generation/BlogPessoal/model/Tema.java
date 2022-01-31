package com.generation.BlogPessoal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_tema")
public class Tema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTema;
	
	@NotNull
	@Size(min = 5, max = 1000)
	private String tema;

	public Long getIdTema() {
		return idTema;
	}

	public void setIdTema(Long idTema) {
		this.idTema = idTema;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}
}
