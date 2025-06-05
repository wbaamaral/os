package com.wbaamaral.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbaamaral.os.domain.Cliente;
import com.wbaamaral.os.domain.Os;
import com.wbaamaral.os.domain.Tecnico;
import com.wbaamaral.os.domain.enuns.Prioridade;
import com.wbaamaral.os.domain.enuns.Status;
import com.wbaamaral.os.repositores.ClienteRepository;
import com.wbaamaral.os.repositores.OsRepository;
import com.wbaamaral.os.repositores.TecnicoRepository;

@Service
public class DBService {
	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private OsRepository osRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public void instanciaDB() {
		Tecnico t1 = new Tecnico("Valdir Cezar", "144.785.300-84", "(88) 98888-8888");
		Tecnico t2 = new Tecnico("Linus Torvalds", "641.760.040-88", "(88) 94545-4545");
		Cliente c1 = new Cliente("Betina Campos", "598.508.200-80", "(88) 98888-7777");

		Os os1 = new Os(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}

}
