package com.wbaamaral.os.services;

import java.util.List;
import java.time.LocalDateTime;
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

	OsService(DBService DBService) {
	}

	private Os fromDTO(@Valid OsDTO obj) {
		Os ordemServico = new Os();
		ordemServico.setId(obj.getId());
		ordemServico.setObservacoes(obj.getObservacoes());
		ordemServico.setDataAbertura(obj.getDataAbertura());
		
		ordemServico.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		ordemServico.setStatus(Status.toEnum(obj.getStatus()));

		Tecnico tec = tecnicoSrv.findById(obj.getTecnico());
		Cliente cli = clienteSrv.findById(obj.getCliente());

		ordemServico.setCliente(cli);
		ordemServico.setTecnico(tec);

		if (ordemServico.getStatus().getCod().equals(2)) {
			
			ordemServico.setDataFechamento(LocalDateTime.now());
		
			System.out.println("\nStatus OS: " + ordemServico.getStatus() +"\n"+ ordemServico.getDataFechamento());
		}
		
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

	public Os update(@Valid OsDTO obj) {
		findById(obj.getId());

		Os ordemdeServico = fromDTO(obj);
		
		return repository.save(ordemdeServico);
	}

}
