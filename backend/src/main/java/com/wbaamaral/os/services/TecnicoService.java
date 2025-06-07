package com.wbaamaral.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbaamaral.os.domain.Tecnico;
import com.wbaamaral.os.dtos.TecnicoDTO;
import com.wbaamaral.os.repositores.TecnicoRepository;
import com.wbaamaral.os.services.exceptions.DataIntegratyViolationException;
import com.wbaamaral.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	private Tecnico findByCPF(TecnicoDTO objDTO) {

		Tecnico obj = tecnicoRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}

		return null;
	}

	public Tecnico findById(Long id) {

		Optional<Tecnico> obj = tecnicoRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Técnico não encontrado! ID: " + id + ", Tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {

		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDTO obj) {

		if (findByCPF(obj) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		return tecnicoRepository.save(new Tecnico(obj.getNome(), obj.getCpf(), obj.getTelefone()));

	}

	public Tecnico update(Long id, TecnicoDTO updObj) {
		Tecnico oldObj = findById(id);
		Tecnico objCPF = findByCPF(updObj);

		if (objCPF != null && !(objCPF.getCpf().equals(oldObj.getCpf()))) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setNome(updObj.getNome());
		oldObj.setCpf(updObj.getCpf());
		oldObj.setTelefone(updObj.getTelefone());

		return tecnicoRepository.save(oldObj);
	}

	public void delete(Long id) {

		Tecnico obj = findById(id);
		if (obj.getList().size() > 0 ) {
			throw new DataIntegratyViolationException("Técnico possui ordens de serviço, não pode ser deletado!");
		}
		
		tecnicoRepository.delete(obj);

	}
}
