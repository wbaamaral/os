package com.wbaamaral.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Tecnico extends Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")
	private List<Os> list = new ArrayList<>();
	
	public Tecnico() {
		super();
	}

	public Tecnico(Long id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);
	}

	public List<Os> getList() {
		return list;
	}

	public void setList(List<Os> list) {
		this.list = list;
	}

	
}
