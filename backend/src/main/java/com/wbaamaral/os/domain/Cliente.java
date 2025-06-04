package com.wbaamaral.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Os> list = new ArrayList<>();
	
	public Cliente() {
		super();
	}

	public Cliente(Long id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);
	}

	public List<Os> getList() {
		return list;
	}

	public void setList(List<Os> list) {
		this.list = list;
	}

}
