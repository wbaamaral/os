package com.wbaamaral.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbaamaral.os.domain.Cliente;
import com.wbaamaral.os.domain.Os;
import com.wbaamaral.os.domain.Tecnico;
import com.wbaamaral.os.domain.enuns.Prioridade;
import com.wbaamaral.os.domain.enuns.Status;
import com.wbaamaral.os.dtos.OsDTO;
import com.wbaamaral.os.repositores.OsRepository;
import com.wbaamaral.os.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class OsService {

	@Autowired
	private OsRepository repository;

	@Autowired
	private ClienteService clienteSrv;

	@Autowired
	private TecnicoService tecnicoSrv;

	private Os fromDTO(@Valid OsDTO obj) {
		Os ordemServico = new Os();
		ordemServico.setObservacoes(obj.getObservacoes());
		ordemServico.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		ordemServico.setStatus(Status.toEnum(obj.getStatus()));

		Tecnico tec = tecnicoSrv.findById(obj.getTecnico());
		Cliente cli = clienteSrv.findById(obj.getCliente());

		ordemServico.setCliente(cli);
		ordemServico.setTecnico(tec);

		return ordemServico;
	}

	public Os findById(Long id) {
		Optional<Os> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("OS não encontrada! ID: " + id + Os.class.getName()));
	}

	public List<Os> findAll() {
		return repository.findAll();
	}

	public Os create(@Valid OsDTO obj) {
		Os saveOS = fromDTO(obj);

		return repository.save(saveOS);
	}

}
