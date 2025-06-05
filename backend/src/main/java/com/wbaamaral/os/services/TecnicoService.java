package com.wbaamaral.os.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbaamaral.os.domain.Tecnico;
import com.wbaamaral.os.repositores.TecnicoRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	public Tecnico findById(Long id) {
		
	  Optional<Tecnico>	obj =  tecnicoRepository.findById(id);
	  
	  return obj.orElse(null);
	}
}
