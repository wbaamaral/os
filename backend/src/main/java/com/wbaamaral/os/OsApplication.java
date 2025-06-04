package com.wbaamaral.os;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wbaamaral.os.domain.Cliente;
import com.wbaamaral.os.domain.Os;
import com.wbaamaral.os.domain.Tecnico;
import com.wbaamaral.os.domain.enuns.Prioridade;
import com.wbaamaral.os.domain.enuns.Status;
import com.wbaamaral.os.repositores.ClienteRepository;
import com.wbaamaral.os.repositores.OsRepository;
import com.wbaamaral.os.repositores.TecnicoRepository;

@SpringBootApplication
public class OsApplication implements CommandLineRunner{

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private OsRepository osRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(OsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Tecnico t1 = new Tecnico(null, "Valdir Cezar", "144.785.300-84", "(88) 98888-8888");
		Tecnico t2 = new Tecnico(null, "Linus Torvalds", "641.760.040-88", "(88) 94545-4545");
		Cliente c1 = new Cliente(null, "Betina Campos", "598.508.200-80", "(88) 98888-7777");
		
		Os os1 = new Os(null, Prioridade.ALTA, "Teste create OD", Status.ANDAMENTO, t1, c1);
		
		t1.getList().add(os1);
		c1.getList().add(os1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
		
		
	}

}
