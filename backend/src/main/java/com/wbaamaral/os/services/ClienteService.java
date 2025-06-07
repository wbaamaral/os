package com.wbaamaral.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbaamaral.os.domain.Cliente;
import com.wbaamaral.os.domain.Pessoa;
import com.wbaamaral.os.dtos.ClienteDTO;
import com.wbaamaral.os.repositores.ClienteRepository;
import com.wbaamaral.os.repositores.PessoaRepository;
import com.wbaamaral.os.services.exceptions.DataIntegratyViolationException;
import com.wbaamaral.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	private Pessoa findByCPF(ClienteDTO objDTO) {

		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}

		return null;
	}

	public Cliente findById(Long id) {

		Optional<Cliente> obj = clienteRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado! ID: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {

		return clienteRepository.findAll();
	}

	public Cliente create(ClienteDTO obj) {

		if (findByCPF(obj) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		return clienteRepository.save(new Cliente(obj.getNome(), obj.getCpf(), obj.getTelefone()));

	}

	public Cliente update(Long id, ClienteDTO updObj) {
		Cliente oldObj = findById(id);
		Pessoa objCPF = findByCPF(updObj);

		if (objCPF != null && !(objCPF.getCpf().equals(oldObj.getCpf()))) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setNome(updObj.getNome());
		oldObj.setCpf(updObj.getCpf());
		oldObj.setTelefone(updObj.getTelefone());

		return clienteRepository.save(oldObj);
	}

	public void delete(Long id) {

		Cliente obj = findById(id);
		if (obj.getList().size() > 0 ) {
			throw new DataIntegratyViolationException("Cliente possui ordens de serviço, não pode ser deletado!");
		}
		
		clienteRepository.delete(obj);

	}
}
