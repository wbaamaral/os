package com.wbaamaral.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wbaamaral.os.domain.Tecnico;
import com.wbaamaral.os.dtos.ClienteDTO;
import com.wbaamaral.os.dtos.TecnicoDTO;
import com.wbaamaral.os.services.TecnicoService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

	@Autowired
	private TecnicoService service;

	/*
	 * Busca pelo tecnico pelo ID
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Long id) {
		TecnicoDTO obj = new TecnicoDTO(service.findById(id));

		return ResponseEntity.ok().body(obj);
	}

	/*
	 * Busca uma lista contendo todos os tecnicos da base de dados
	 */
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {

		List<TecnicoDTO> tecnicoDTO = service.findAll().stream().map(obj -> new TecnicoDTO(obj))
				.collect((Collectors.toList()));

		return ResponseEntity.ok().body(tecnicoDTO);

	}

	/*
	 * Cria um novo Tecnico
	 */
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody ClienteDTO tecnicoDTO) {

		Tecnico newObj = service.create(tecnicoDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	/*
	 * Atualizar dados de técnicos
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO tecnico) {
		TecnicoDTO tecnicoDTO = new TecnicoDTO(service.update(id, tecnico));

		return ResponseEntity.ok().body(tecnicoDTO);
	}

	/*
	 * Deletar técnico
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

}
