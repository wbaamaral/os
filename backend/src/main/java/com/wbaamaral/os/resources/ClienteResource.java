package com.wbaamaral.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wbaamaral.os.domain.Cliente;
import com.wbaamaral.os.dtos.ClienteDTO;
import com.wbaamaral.os.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	/*
	 * Busca pelo tecnico pelo ID
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
		ClienteDTO obj = new ClienteDTO(service.findById(id));

		return ResponseEntity.ok().body(obj);
	}

	/*
	 * Busca uma lista contendo todos os clientes da base de dados
	 */
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {

		List<ClienteDTO> clienteDTO = service.findAll().stream().map(obj -> new ClienteDTO(obj))
				.collect((Collectors.toList()));

		return ResponseEntity.ok().body(clienteDTO);

	}

	/*
	 * Cria um novo Cliente
	 */
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO) {

		Cliente newObj = service.create(clienteDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	/*
	 * Atualizar dados de cliente
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO tecnico) {
		ClienteDTO tecnicoDTO = new ClienteDTO(service.update(id, tecnico));

		return ResponseEntity.ok().body(tecnicoDTO);
	}

	/*
	 * Deletar técnico
	 */
	@DeleteMapping(value =  "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

}
