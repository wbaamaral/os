package com.wbaamaral.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbaamaral.os.domain.Tecnico;
import com.wbaamaral.os.dtos.TecnicoDTO;
import com.wbaamaral.os.repositores.TecnicoRepository;
import com.wbaamaral.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	public Tecnico findById(Long id) {

		Optional<Tecnico> obj = tecnicoRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Técnico não encontrado! ID: " + id + ", Tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {

		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDTO obj) {

		return tecnicoRepository.save(new Tecnico(obj.getNome(), obj.getCpf(), obj.getTelefone()));

	}
}
