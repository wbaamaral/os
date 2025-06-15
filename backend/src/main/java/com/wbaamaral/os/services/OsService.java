package com.wbaamaral.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbaamaral.os.domain.Os;
import com.wbaamaral.os.repositores.OsRepository;
import com.wbaamaral.os.services.exceptions.ObjectNotFoundException;

@Service
public class OsService {

	@Autowired
	private OsRepository repository;

	public Os findById(Long id) {
		Optional<Os> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("OS não encontrada! ID: " + id + Os.class.getName()));
	}
	
	public List<Os> findAll(){
		return repository.findAll();
	}
}
