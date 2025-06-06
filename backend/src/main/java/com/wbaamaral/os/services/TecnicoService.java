package com.wbaamaral.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbaamaral.os.domain.Pessoa;
import com.wbaamaral.os.domain.Tecnico;
import com.wbaamaral.os.dtos.ClienteDTO;
import com.wbaamaral.os.repositores.PessoaRepository;
import com.wbaamaral.os.repositores.TecnicoRepository;
import com.wbaamaral.os.services.exceptions.DataIntegratyViolationException;
import com.wbaamaral.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	private Pessoa findByCPF(ClienteDTO objDTO) {

		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
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

	public Tecnico create(ClienteDTO obj) {

		if (findByCPF(obj) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		return tecnicoRepository.save(new Tecnico(obj.getNome(), obj.getCpf(), obj.getTelefone()));

	}

	public Tecnico update(Long id, ClienteDTO updObj) {
		Tecnico oldObj = findById(id);
		Pessoa objCPF = findByCPF(updObj);

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
