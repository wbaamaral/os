package com.wbaamaral.os.dtos;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.validator.constraints.br.CPF;

import com.wbaamaral.os.domain.Tecnico;

import jakarta.validation.constraints.NotEmpty;

public class TecnicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotEmpty(message = "O campo NOME é requerido")
	private String nome;
	@CPF
	@NotEmpty(message = "O campo CPF é requerido")
	private String cpf;
	@NotEmpty(message = "O campo TELEFONE é requerido")
	private String telefone;

	public TecnicoDTO() {
		super();
	}

	public TecnicoDTO(Tecnico obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.telefone = obj.getTelefone();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TecnicoDTO other = (TecnicoDTO) obj;
		return Objects.equals(id, other.id);
	}


}
